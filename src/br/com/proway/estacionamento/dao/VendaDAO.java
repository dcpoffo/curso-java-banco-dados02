/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proway.estacionamento.dao;

import br.com.proway.estacionamento.modelo.Cliente;
import br.com.proway.estacionamento.modelo.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 65316
 */
public class VendaDAO extends BaseDAO implements GenericInterface<Venda> {

    @Override
    public int inserir(Venda entidade) {
        try {
            conectar();
            String sql = "insert into vendas (id_Cliente, valor, registro_ativo) values (?,?,1)";
            PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entidade.getIdCliente());
            ps.setDouble(2, entidade.getValor());
            ps.execute();
            ResultSet tabela = ps.getGeneratedKeys();
            if (tabela.next()) {
                // retorna o id que foi gerado na tabela de vendas
                return tabela.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            desconectar();
        }
    }

    @Override
    public boolean alterar(Venda entidade) {
        try {
            conectar();;
            String sql = "update vendas set valor = ? where id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setDouble(1, entidade.getValor());
            ps.setInt(2, entidade.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public ArrayList<Venda> obterTodosVenda(String busca, int pagina, int quantidade) {
        busca = "%" + busca + "%";
        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            conectar();
            String sql = "select * from vendas inner join cliente on (vendas.id_cliente = cliente.id) "
                    + "\nwhere registro_ativo = 1 and cliente.nome like ? order by vendas.id"
                    + "\nLIMIT ?,?";

            int quantidadeParam = 1;

            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(quantidadeParam++, busca);
            st.setInt(quantidadeParam++, pagina * quantidade);
            st.setInt(quantidadeParam, quantidade);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("vendas.id"));
                venda.setValor(rs.getDouble("vendas.valor"));

                venda.setIdCliente(rs.getInt("vendas.id_cliente"));
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente.id"));
                cliente.setNome(rs.getString("cliente.nome"));
                cliente.setCpf(rs.getString("cliente.cpf"));
                venda.setCliente(cliente);
                vendas.add(venda);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return vendas;
    }

    @Override
    public boolean apagar(int id) {
        try {
            conectar();
            String sql = "update vendas set registro_ativo = 0 where id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    @Override
    public Venda obterTodosId(int id) {

        Venda venda = null;
        try {
            conectar();
            String sql = "Select * from vendas where id = ? and registro_ativo = 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setValor(rs.getDouble("valor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return venda;

    }

    @Override
    public int contabilizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void conectar() throws SQLException {
        conexao = conexaoFactory.conectar();
    }

    @Override
    protected void desconectar() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Venda> obterTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int contabilizarFiltrados(String busca) {
        try {
            conexao = conexaoFactory.conectar();
            String sql = "select count(vendas.id) from vendas"
                    + "\ninner join cliente on(vendas.id_cliente = cliente.id)"
                    + "\nwhere vendas.registro_ativo = 1 and cliente.nome like ?";
            busca = "%" + busca + "%";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, busca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            desconectar();
        }
        return -1;
    }
    
}
