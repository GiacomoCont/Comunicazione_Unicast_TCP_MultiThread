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
    for(;;) {
      try {
        System.out.println("Inserisci il messaggio da trasmettere al server: ");
        stringaUtente = tastiera.readLine();
        // la spedisco al server
        System.out.println("Invio della stringa al server...");
        outVersoServer.writeBytes(stringaUtente + '\n');
        // leggo la risposta dal server
        stringaRicevutaDalServer = inDalServer.readLine();
        System.out.println("Risposta del server: " + '\n' + stringaRicevutaDalServer);
        if (stringaUtente.equals("FINE")) {
          System.out.println("Chiusura connessione!");
          mioSocket.close();
          break;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Errore durante la comunicazione col server!");
        System.exit(1);
      }
    }
  }

  public Socket connetti() {
    System.out.println("Esecuzione del client avviata");
    try {
      // input da tastiera
      tastiera = new BufferedReader(new InputStreamReader(System.in));
      mioSocket = new Socket (nomeServer, 6789);
      // associo due oggetti al socket per effettuare la scrittura e la lettura
      outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
      inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("Host sconosciuto!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("");
    }
  return mioSocket;
  }
}