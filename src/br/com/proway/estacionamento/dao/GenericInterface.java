/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proway.estacionamento.dao;

import java.util.ArrayList;

/**
 *
 * @author 65316
 */
public interface GenericInterface<T> {
    
    int inserir(T entidate);

    boolean alterar(T entidade);
    
    ArrayList<T> obterTodos();
    
    boolean apagar(int id);
    
    T obterTodosId(int id);
    
    int contabilizar();
    
}
