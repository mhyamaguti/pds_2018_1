/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author Marcelo Yamaguti
 */
import java.io.*;
import java.net.*;

public class Servidor {

    static final int PORTA = 9999; // Numero da porta

    public static void main(String[] args) {
        String msg; // Mensagem lida
        try {
            ServerSocket ss = new ServerSocket(PORTA); // Cria um socket servidor
            Socket socket = ss.accept(); // Aguarda conexao e gera um socket para a mesma
            InputStream is = socket.getInputStream(); // Obtem o stream de entrada do socket
            InputStreamReader isr = new InputStreamReader(is); // "Filtro" para caracteres
            BufferedReader br = new BufferedReader(isr); // "Filtro" para outros tipos
            System.out.println("Servidor: aguardando mensagens");
            while (true) { // 'Loop' infinito
                msg = br.readLine(); // Leitura de uma String do stream
                System.out.println("Servidor: mensagem recebida: " + msg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } // main
} // class Servidor

