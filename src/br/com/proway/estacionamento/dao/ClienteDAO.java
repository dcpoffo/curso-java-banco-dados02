/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proway.estacionamento.dao;

import br.com.proway.estacionamento.modelo.Cliente;
import br.com.proway.estacionamento.modelo.EntradaSaida;
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
public class ClienteDAO implements ClienteInterface {

    @Override
    public int inserir(Cliente cliente) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "INSERT INTO cliente (nome, cpf, telefone, unidadefederativa, cidade, cep, logradouro, complemento, numero) values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getUnidadeFederativa());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getCep());
            ps.setString(7, cliente.getLogradouro());
            ps.setString(8, cliente.getComplemento());
            ps.setInt(9, cliente.getNumero());
            ps.execute();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean alterar(Cliente cliente) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, unidadefederativa = ?, cidade = ?, cep = ?, logradouro = ?, complemento = ?, numero = ? WHERE id = ?");

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getUnidadeFederativa());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getCep());
            ps.setString(7, cliente.getLogradouro());
            ps.setString(8, cliente.getComplemento());
            ps.setInt(9, cliente.getNumero());
            ps.setInt(10, cliente.getId());

            int quantidadeAlterada = ps.executeUpdate();
            return quantidadeAlterada == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean apagar(int id) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "DELETE FROM cliente WHERE id = ?");
            ps.setInt(1, id);
            int quantidadeAfetada = ps.executeUpdate();
            return quantidadeAfetada == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Cliente> obterTodos(String nome) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT * FROM cliente WHERE nome LIKE ?");
            ps.setString(1, "%" + nome + "%");
            ResultSet tabelaEmMemoria = ps.executeQuery();
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (tabelaEmMemoria.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(tabelaEmMemoria.getInt("id"));
                cliente.setNome(tabelaEmMemoria.getString("nome"));
                cliente.setCpf(tabelaEmMemoria.getString("cpf"));
                cliente.setTelefone(tabelaEmMemoria.getString("telefone"));
                cliente.setUnidadeFederativa(tabelaEmMemoria.getString("unidadefederativa"));
                cliente.setCidade(tabelaEmMemoria.getString("cidade"));
                cliente.setCep(tabelaEmMemoria.getString("cep"));
                cliente.setLogradouro(tabelaEmMemoria.getString("logradouro"));
                cliente.setComplemento(tabelaEmMemoria.getString("complemento"));
                cliente.setNumero(tabelaEmMemoria.getInt("numero"));

                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int contabilizar() {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS 'quantidade' FROM cliente");
            if (resultSet.next()) {
                int quantidade = resultSet.getInt("quantidade");
                return quantidade;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Cliente obterPeloId(int id) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT * FROM cliente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet tabelaEmMemoria = ps.executeQuery();

            if (tabelaEmMemoria.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(tabelaEmMemoria.getInt("id"));
                cliente.setNome(tabelaEmMemoria.getString("nome"));
                cliente.setCpf(tabelaEmMemoria.getString("cpf"));
                cliente.setTelefone(tabelaEmMemoria.getString("telefone"));
                cliente.setUnidadeFederativa(tabelaEmMemoria.getString("unidadefederativa"));
                cliente.setCidade(tabelaEmMemoria.getString("cidade"));
                cliente.setCep(tabelaEmMemoria.getString("cep"));
                cliente.setLogradouro(tabelaEmMemoria.getString("logradouro"));
                cliente.setComplemento(tabelaEmMemoria.getString("complemento"));
                cliente.setNumero(tabelaEmMemoria.getInt("numero"));

                return cliente;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
