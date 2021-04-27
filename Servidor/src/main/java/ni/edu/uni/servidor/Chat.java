/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.servidor;

/**
 *
 * @author Rodrigo
 */
public class Chat {
    public static Conector server;
    public static void main(String[] args) {
        VServidor vserver = new VServidor();
        vserver.setVisible(true);
    }

    public static void initServer(int port) {
        server = new Conector(port);
        server.start();
    }
    
    
}
