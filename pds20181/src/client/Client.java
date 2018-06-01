package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public void execute(String server, int port) throws IOException {
        Socket socket = null;
        PrintWriter saida = null;
        BufferedReader entrada = null;
        String texto = "";
        ClientThread client = null;

        try {
            System.out.println("Iniciando conexao com o servidor...");
            socket = new Socket(server, port);
            saida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Nao conectou ao servidor: " + server);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Nao obteve acesso de E/S "
                    + "na conexao com: " + server);
            System.exit(1);
        }

        client = new ClientThread(entrada);
        client.start();  // Dispara thread de escrita

        System.out.println("Conexao realizada.");
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Digite as mensagens (FIM) para terminar.");

        Scanner sc = new Scanner(System.in);

        while (!texto.equals("FIM")) {
            texto = sc.nextLine();
            saida.println(texto);
        }

        saida.close();
        stdIn.close();
        socket.close();
        client.cancel();
        System.out.println("Conexao finalizada...");
    }

    public static void main(String[] args) {
        Client client;
        String server = "127.0.0.1";
        int port = 9999;
        if (args.length > 0) {
            server = args[0];
        }
        if (args.length > 1) {
            String portaStr = args[1];
            port = Integer.parseInt(portaStr);
        }
        try {
            client = new Client();
            client.execute(server,port);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
