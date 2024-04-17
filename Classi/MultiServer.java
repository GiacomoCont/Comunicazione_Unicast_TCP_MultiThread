package com.mycompany.tcp_multithread;

/**
 *
 * @author Giacomo Contini
 * 11/04/2024
 */

import java.io.*;
import java.net.*;

public class MultiServer {
  
  public void start() {
    try {
      ServerSocket serverSocket = new ServerSocket(6789);
      while(true) {
        System.out.println("Server in attesa...");
        Socket socket = serverSocket.accept();
        System.out.println("Il " + socket + " ha effettuato una connessione");
        ServerThread serverThread = new ServerThread(socket);
        serverThread.start();
      }
    } catch (BindException bi) {
      System.err.println("Porta già in uso o non disponibile: " + bi.getMessage());
    } catch (IOException e) {
      System.err.println("Errore durante l'attesa della connessione: " + e.getMessage());
    }
  }
}