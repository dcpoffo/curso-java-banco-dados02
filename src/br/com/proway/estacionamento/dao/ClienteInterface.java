
package br.com.proway.estacionamento.dao;

import br.com.proway.estacionamento.modelo.Cliente;
import java.util.ArrayList;

/**
 *
 * @author 65316
 */
public interface ClienteInterface {
    
    //Metodo responsavel por inserir no registro no BD
    int inserir(Cliente cliente);
    
    //Metodo responsavel por alterar o registro no BD
    boolean alterar (Cliente cliente);
    
    //Metodo responsavel por apagar o registro pelo ID no BD
    boolean apagar(int id);
           
    ArrayList<Cliente> obterTodos(String nome);
    
    //Obter a entrada Saida pelo ID
    Cliente obterPeloId(int id);
    
    int contabilizar();
    
}
