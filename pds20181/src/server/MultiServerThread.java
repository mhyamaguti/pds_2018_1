package server;

import java.io.*;
import java.net.*;

public class MultiServerThread extends Thread {
    private Socket clientSocket = null;
    private int id;
    private MultiServer multiServer;
    private BufferedReader in;
    private PrintWriter out;

    public MultiServerThread(Socket socket, int id, MultiServer multiServer) {
        super("MultiServerThread");
        clientSocket = socket;
        this.id = id;
        this.multiServer=multiServer;
        System.out.println("Servidor numero: "+id+" iniciado");
    }

    public void atualiza(String msg) {
        out.println(msg);
    }
    
    public void run() {
        String linha;
        try {
            System.out.println(id+":Conexao iniciada...");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            multiServer.addClient(out);
            in = new BufferedReader(
                    new InputStreamReader(
                    clientSocket.getInputStream()));
            while ((linha = in.readLine()) != null) {
                 System.out.println(id+":Mensagem recebida: "+linha);
                 multiServer.setMensagem(linha);
                 if (linha.equals("FIM"))
                    break;
            }
            out.close();
            in.close();
            clientSocket.close();
            System.out.println(id+":Conexao finalizada...");                    
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}