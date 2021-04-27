/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.servidor;

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

    public Conector(int port) {
        try {
            ss = new ServerSocket(port);
            s = ss.accept();

            //creacion de entrada de datos para lectura de msg
            entradaSocket = new InputStreamReader(s.getInputStream());
            entrada = new BufferedReader(entradaSocket);

            //creacion de salida de datos para el envio de msg
            salida = new DataOutputStream((s.getOutputStream()));

        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        String msg;
        while (true) {
            msg = readMSG();
            VServidor.txtaChat.setText(VServidor.txtaChat.getText() + "\n" + msg);
        }
    }

    public void sendMSG(String msg) {
        try {
            salida.writeUTF(msg+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String readMSG() {
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void disconnect() {
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
