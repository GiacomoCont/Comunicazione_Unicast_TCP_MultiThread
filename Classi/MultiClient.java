package com.mycompany.tcp_multithread;

/**
 *
 * @author Giacomo Contini
 * 11/04/2024
 */

import java.io.*;
import java.net.*;

public class MultiClient {
  String nomeServer = "localhost";
  Socket mioSocket;
  BufferedReader tastiera;
  String stringaUtente;
  String stringaRicevutaDalServer;
  DataOutputStream outVersoServer;
  BufferedReader inDalServer;

  public MultiClient() {
    Socket mioSocket = new Socket();
  }

  public void comunica() {
    while(true) {
      try {
        System.out.println("Inserisci il messaggio da trasmettere al server: ");
        stringaUtente = tastiera.readLine();
        System.out.println("Invio della stringa al server...");
        outVersoServer.writeBytes(stringaUtente + '\n');
        stringaRicevutaDalServer = inDalServer.readLine();
        System.out.println("Risposta del server: " + '\n' + stringaRicevutaDalServer);
        if (stringaUtente.equals("FINE")) {
          System.out.println("Chiusura connessione!");
          mioSocket.close();
          break;
        }
      } catch (IOException e) {
        System.err.println("Errore durante la comunicazione con il server " + e.getMessage());
      }
    }
  }

  public Socket connetti() {
    System.out.println("Esecuzione del client avviata");
    try {
      tastiera = new BufferedReader(new InputStreamReader(System.in));
      mioSocket = new Socket (nomeServer, 6789);
      outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
      inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Host sconosciuto! " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Errore durante la connessione " + e.getMessage());
    }
  return mioSocket;
  }
}