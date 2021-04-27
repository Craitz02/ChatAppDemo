/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class Conector extends Thread {
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    //final int puerto = 1234;

    public Conector(String name,String ip,int puerto) {
        
        
        try {
            
            s = new Socket(ip,puerto);
            
            //creacion de entrada de datos para lectura de msg
            entradaSocket = new InputStreamReader(s.getInputStream());
            entrada = new BufferedReader(entradaSocket);
            
            //creacion de salida de datos para el envio de msg
            salida = new DataOutputStream((s.getOutputStream()));
            salida.writeUTF("** "+name+" se ha Conectado ** \n");
            
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        String msg;
        while(true){
            msg = readMSG();
            VCliente.txtaChat.setText(VCliente.txtaChat.getText()+"\n"+msg);
        }
    }
    
    public void sendMSG(String msg){
        try {
            salida.writeUTF(msg+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String readMSG(){
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void disconnect(){
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
