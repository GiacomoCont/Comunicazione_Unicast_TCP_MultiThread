package com.mycompany.tcp_multithread;

/**
 *
 * @author Giacomo Contini
 * 11/04/2024
 */

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
  private Socket client;

  public ServerThread(Socket socket) {
    this.client = socket;
  }

  @Override
  public void run() {
    comunica();
    try {
      client.close();
    } catch (IOException e) {
      System.err.println("Errore durante la chiusura della connessione: " + e.getMessage());
    }
  }

  public void comunica() {
    BufferedReader inDalClient = null;
    PrintWriter outVersoClient = null;

    try {
      inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
      outVersoClient = new PrintWriter(client.getOutputStream(), true);

      String stringaRicevuta;
      while ((stringaRicevuta = inDalClient.readLine()) != null) {
        if (stringaRicevuta.equals("FINE")) {
          outVersoClient.println(stringaRicevuta + " Server in chiusura...");
          System.out.println("Echo sul server in chiusura: " + stringaRicevuta);
          client.close();
          break;
        } else {
          outVersoClient.println(stringaRicevuta + " Stringa ricevuta dal client");
          System.out.println("Echo sul server: " + stringaRicevuta);
        }
      }
    } catch (IOException e) {
      System.err.println("Errore durante la comunicazione con il client: " + e.getMessage());
    } finally {
      try {
        if (outVersoClient != null)
          outVersoClient.close();
        if (inDalClient != null)
          inDalClient.close();
      } catch (IOException e) {
        System.err.println("Errore durante la chiusura del socket: " + e.getMessage());
      }
    }
  }
}