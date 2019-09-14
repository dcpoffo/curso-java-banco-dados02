
package br.com.proway.estacionamento.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 65316
 */
public class Conexao {
    
    // Constante, NAO pode ser modificada
    private final String HOST = "jdbc:mysql://localhost:3306/exemplo_01?useTimezone=true&serverTimezone=UTC";
//    private final String HOST = "jdbc:mysql://192.168.0.40:3306/exemplo_01?useTimezone=true&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root";
    
    public Connection conectar() throws SQLException{
        Connection conexao = DriverManager.getConnection(HOST, USER, PASSWORD);
        return conexao;
    }
    
    
    
}
