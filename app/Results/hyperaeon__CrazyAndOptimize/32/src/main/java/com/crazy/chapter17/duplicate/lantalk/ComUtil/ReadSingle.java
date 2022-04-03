package com.crazy.chapter17.duplicate.lantalk.ComUtil;
 import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ReadSingle extends Thread{

 private byte[] buf;

 private  DatagramPacket singlePacket;


public void run(){
    while (true) {
        try {
            singleSocket.receive(singlePacket);
            lanTalk.processMsg(singlePacket, true);
        } catch (IOException ex) {
            ex.printStackTrace();
            if (singleSocket != null) {
                singleSocket.close();
            }
            JOptionPane.showMessageDialog(null, "Send message failed", "Network exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}


}