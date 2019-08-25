/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proway.estacionamento.dao;

import br.com.proway.estacionamento.modelo.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.crypto.spec.PSource;

/**
 *
 * @author 65316
 */
public class ContatoDAO implements GenericInterface<Contato>{

    private Conexao conexaoFactory = new Conexao();
    
    @Override
    public int inserir(Contato entidade) {
        Connection conexao = null;
        
        try {
            conexao = conexaoFactory.conectar();
            String query = 
                    "insert into contatos (id_cliente, tipo, valor, registro_ativo) values (?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entidade.getIdCliente());
            ps.setString(2, entidade.getTipo());
            ps.setString(3, entidade.getValor());
            ps.setBoolean(4, true);
            ps.execute();
            ResultSet tabelaEmMemoria = ps.getGeneratedKeys();
            if (tabelaEmMemoria.next()){
                int id = tabelaEmMemoria.getInt(1);
                return id;
            }
            
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            
        }
        
    }

    @Override
    public boolean alterar(Contato entidade) {
        
        Connection conexao = null;
        try {
            conexao = conexaoFactory.conectar();
            String query = "update contatos set tipo = ?, valor = ? where id = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getTipo());
            ps.setString(2, entidade.getValor());
            ps.setInt(3, entidade.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conexao == null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<Contato> obterTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean apagar(int id) {
        Connection conexao = null;
        try {
            conexao = conexaoFactory.conectar();
            String query = "update contatos set registro_ativo = 0 where id = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } 
        }
    }

    @Override
    public Contato obterTodosId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int contabilizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Contato> obterTodos(int idCliente) {
        
        ArrayList<Contato> contatos = new ArrayList<>();
        
        Connection conexao = null;
        
        try {
            conexao = conexaoFactory.conectar();
            String query = "select * from contatos where id_Cliente = ? and registro_ativo = 1";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setIdCliente(rs.getInt("id_cliente"));
                contato.setTipo(rs.getString("tipo"));
                contato.setValor(rs.getString("valor"));
                contatos.add(contato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conexao == null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return contatos;               
        
    }
    
}
