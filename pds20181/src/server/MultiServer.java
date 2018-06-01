package server;

/**
 * @author Marcelo Yamaguti
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class MultiServer {
    private String mensagem = null;
    private ArrayList<PrintWriter> lista = new ArrayList<PrintWriter>();    
    
    public void addClient(PrintWriter cl) {
        lista.add(cl);
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
        System.out.println("Mensagem aos clientes: "+mensagem);
        for(PrintWriter cl:lista) {
            cl.println(mensagem);
        }
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public void executa() throws IOException {
        ServerSocket serverSocket = null;
        boolean ouvindo = true;
        int porta = 9999;
        int contador = 0;

        try {
            serverSocket = new ServerSocket(porta);
        } catch (IOException e) {
            System.err.println("Nao conseguiu escutar a porta: "+porta);
            System.exit(-1);
        }

        while (ouvindo) {
            System.out.println("Aguardando conexao...");
            Socket socket = serverSocket.accept();
            System.out.println("Conexao com novo cliente...");
            contador++;
            MultiServerThread novoServidor = new MultiServerThread(socket,contador,this);
            novoServidor.start();
        }

        serverSocket.close();
    }

    public static void main(String[] args) {
        try {
            new MultiServer().executa();
        } catch(IOException e) {
            System.out.println("Falhou na criacao do Servidor...");            
        }
    }
}