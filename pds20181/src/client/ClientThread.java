package client;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {
    private BufferedReader entrada;

    public ClientThread(BufferedReader entrada) {
        super("ClientChatThread");
        this.entrada = entrada;
    }

    public void run() {
        String texto = "";
        try {
            do {
                texto = entrada.readLine();
                System.out.println("Eco: " + texto);
            }
            while(!interrupted() && texto!=null);
        }
        catch (SocketException e1) {            
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public void cancel() {
        this.interrupt();
    }
    
}
