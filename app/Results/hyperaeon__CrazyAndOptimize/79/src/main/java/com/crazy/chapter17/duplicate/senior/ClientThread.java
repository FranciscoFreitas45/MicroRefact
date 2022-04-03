package com.crazy.chapter17.duplicate.senior;
 import java.io.BufferedReader;
import java.io.IOException;
public class ClientThread extends Thread{

 private  BufferedReader brServer;

public ClientThread(BufferedReader brServer) {
    this.brServer = brServer;
}
public void run(){
    try {
        String line = null;
        while ((line = brServer.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (brServer != null) {
                brServer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


}