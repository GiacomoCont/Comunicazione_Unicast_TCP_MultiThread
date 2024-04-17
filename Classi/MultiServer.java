package com.mycompany.tcp_multithread;

/**
 *
 * @author Giacomo Contini
 * 11/04/2024
 */

import java.net.*;

public class MultiServer {
  
  public void start() {
    try {
      ServerSocket serverSocket = new ServerSocket(6789);
      for (;;) {
        System.out.println("1 Server in attesa..");
        Socket socket = serverSocket.accept();
        System.out.println("3 Server socket " + socket);
        ServerThread serverThread = new ServerThread(socket);
        serverThread.start();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Errore durante l'istanza del server!");
      System.exit(1);
    }
  }
}