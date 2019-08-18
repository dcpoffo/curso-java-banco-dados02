package br.com.proway.estacionamento.dao;

import br.com.proway.estacionamento.modelo.EntradaSaida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author 65316
 */
//DAO = Data Access Object
public class EntradaSaidaDAO implements EntradaSaidaInterface {

    @Override
    public int inserir(EntradaSaida entradaSaida) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "INSERT INTO entradas_saidas (modelo, placa) values (?,?)");
            ps.setString(1, entradaSaida.getModelo());
            ps.setString(2, entradaSaida.getPlaca());
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
    public boolean alterar(EntradaSaida entradaSaida) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "UPDATE entradas_saidas SET valor = ? WHERE id = ?");
            ps.setDouble(1, entradaSaida.getValor());
            ps.setInt(2, entradaSaida.getId());
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
                    "DELETE FROM entradas_saidas WHERE id = ?");
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
    public ArrayList<EntradaSaida> obterTodos(String pesquisa) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT * FROM entradas_saidas WHERE placa LIKE ? or modelo LIKE ?");
            ps.setString(1, "%" + pesquisa + "%");
            ps.setString(2, "%" + pesquisa + "%");
            ResultSet tabelaEmMemoria = ps.executeQuery();
            ArrayList<EntradaSaida> entradaSaidas = new ArrayList<>();
            while (tabelaEmMemoria.next()) {
                EntradaSaida entradaSaida = new EntradaSaida();
                entradaSaida.setId(tabelaEmMemoria.getInt("id"));
                entradaSaida.setModelo(tabelaEmMemoria.getString("modelo"));
                entradaSaida.setPlaca(tabelaEmMemoria.getString("placa"));
                entradaSaida.setValor(tabelaEmMemoria.getDouble("valor"));
                entradaSaidas.add(entradaSaida);
            }
            return entradaSaidas;
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
    public EntradaSaida obterPeloId(int id) {
        Connection conexao = null;
        try {
            conexao = new Conexao().conectar();
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT * FROM entradas_saidas WHERE id = ?");
            ps.setInt(1, id);
            ResultSet tabelaEmMemoria = ps.executeQuery();

            EntradaSaida entradaSaida = new EntradaSaida();
            entradaSaida.setId(id);
            entradaSaida.setModelo(tabelaEmMemoria.getString("mdelo"));
            entradaSaida.setPlaca(tabelaEmMemoria.getString("placa"));
            entradaSaida.setValor(tabelaEmMemoria.getDouble("valor"));
            return entradaSaida;

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
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS 'quantidade' FROM entradas_saidas");
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
    public double totalFaturado() {
        Connection conexao = null;

        try {
            conexao = new Conexao().conectar();
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT SUM(valor) AS total FROM entradas_saidas");
            if (resultSet.next()) {
                double total = resultSet.getDouble("total");
                return total;
            }
            return 0;
        } catch (Exception e) {
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

}
