package com.crazy.chapter17.duplicate.lantalk;
 import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ComUtil {

 private  String BROADCAST_IP;

 public  int BROADCAST_PORT;

 private  int DATA_LEN;

 private  MulticastSocket socket;

 private  DatagramSocket singleSocket;

 private  InetAddress broadcastAddress;

 private byte[] inBuff;

 private  DatagramPacket inPacket;

 private  DatagramPacket outPacket;

 private  LanTalk lanTalk;

 private byte[] buf;

 private  DatagramPacket singlePacket;

public ComUtil(LanTalk lanTalk) throws Exception {
    this.lanTalk = lanTalk;
    socket = new MulticastSocket(BROADCAST_PORT);
    singleSocket = new DatagramSocket(BROADCAST_PORT + 1);
    broadcastAddress = InetAddress.getByName(BROADCAST_IP);
    socket.joinGroup(broadcastAddress);
    socket.setLoopbackMode(false);
    outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, BROADCAST_PORT);
    new ReadBroad().start();
    Thread.sleep(1);
    new SingleBroad().start();
}
public void broadCast(String msg){
    try {
        byte[] buff = msg.getBytes();
        outPacket.setData(buff);
        socket.send(outPacket);
    } catch (IOException ex) {
        ex.printStackTrace();
        if (socket != null) {
            socket.close();
        }
        JOptionPane.showMessageDialog(null, "Send message failed", "Network exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}


public void run(){
    while (true) {
        try {
            socket.receive(inPacket);
            String msg = new String(inBuff, 0, inPacket.getLength());
            if (msg.startsWith(Protocol.PRESENCE) && msg.startsWith(Protocol.PRESENCE)) {
                String userMsg = msg.substring(2, msg.length() - 2);
                String[] userInfo = userMsg.split(Protocol.SPLITTER);
                UserInfo user = new UserInfo(userInfo[1], userInfo[0], inPacket.getSocketAddress(), 0);
                boolean addFlag = true;
                ArrayList<Integer> delList = new ArrayList<Integer>();
                for (int i = 1; i < lanTalk.getUserNum(); i++) {
                    UserInfo current = lanTalk.getUser(i);
                    current.setLost(current.getLost() + 1);
                    if (current.equals(user)) {
                        current.setLost(0);
                        addFlag = false;
                    }
                    if (current.getLost() > 2) {
                        delList.add(i);
                    }
                }
                for (int i = 0; i < delList.size(); i++) {
                    lanTalk.removeUser(delList.get(i));
                }
                if (addFlag) {
                    lanTalk.addUser(user);
                }
            } else {
                lanTalk.processMsg(inPacket, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (socket != null) {
                socket.close();
            }
            JOptionPane.showMessageDialog(null, "Send message failed", "Network exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}


public void sendSingle(String message,SocketAddress dest){
    try {
        byte[] buf = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, dest);
        singleSocket.send(packet);
    } catch (IOException e) {
        e.printStackTrace();
        if (singleSocket != null) {
            singleSocket.close();
        }
        JOptionPane.showMessageDialog(null, "Send message failed", "Network exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}


}