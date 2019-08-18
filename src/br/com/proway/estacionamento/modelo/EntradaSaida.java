package br.com.proway.estacionamento.modelo;

/**
 *
 * @author 65316
 */
public class EntradaSaida {
    
    private int Id;
    private String modelo, placa;
    private double valor;
    
    public String getStatus(){
//        if (valor == 0){
//            return "Pendente";
//        } else {
//            return "Pago";
//        }
    return valor == 0 ? "Pendente" : "Pago";
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
