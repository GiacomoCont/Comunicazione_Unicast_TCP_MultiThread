package com.mycompany.tcp_multithread;

/**
 *
 * @author Giacomo Contini
 * 11/04/2024
 */

public class MainClient {
  public static void main(String args[]) {
    MultiClient client = new MultiClient();
    client.connetti();
    client.comunica();
  }
}