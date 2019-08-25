/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proway.estacionamento.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author 65316
 */
public abstract class BaseDAO {
    
    protected Conexao conexaoFactory = new Conexao();
    protected Connection conexao = null;
    
    protected abstract void conectar() throws SQLException;
    
    protected abstract void desconectar();
    
}
