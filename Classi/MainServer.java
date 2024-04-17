package com.mycompany.tcp_multithread;

/**
 *
 * @author Giacomo Contini
 * 11/04/2024
 */

public class MainServer {
  public static void main (String[] args) {
    MultiServer tcpServer = new MultiServer();
    tcpServer.start();
  }
}