/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.cliente;

/**
 *
 * @author Rodrigo
 */
public class Chat {
    public static Conector cliente;
    public static void main(String[] args) {
        VCliente vcliente = new VCliente();
        vcliente.setVisible(true);
    }
    
    public static void initCliente(String name, String ip, int puerto){
        cliente = new Conector(name,ip,puerto);
        cliente.start();
                
    }
}
