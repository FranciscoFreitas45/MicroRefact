package com.crazy.chapter17.duplicate.lantalk.ComUtil;
 import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ReadBroad extends Thread{


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


}