package br.com.proway.estacionamento.dao;

import br.com.proway.estacionamento.modelo.EntradaSaida;
import java.util.ArrayList;

/**
 *
 * @author 65316
 */
public interface EntradaSaidaInterface {
    
    //Metodo responsavel por inserir no registro no BD
    int inserir(EntradaSaida entradaSaida);
    
    //Metodo responsavel por alterar o registro no BD
    boolean alterar (EntradaSaida entradaSaida);
    
    //Metodo responsavel por apagar o registro pelo ID no BD
    boolean apagar(int id);
    
    //Obter todos do BD filtrando pela placa
    ArrayList<EntradaSaida> obterTodos(String placa);
    
    //Obter a entrada Saida pelo ID
    EntradaSaida obterPeloId(int id);
    
    int contabilizar();
    
    double totalFaturado();
    
}
