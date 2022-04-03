package com.crazy.chapter17.senior;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
public class ServerThread extends Thread{

 private  Socket socket;

 private BufferedReader br;

 private PrintStream ps;

public ServerThread(Socket socket) {
    this.socket = socket;
}
public void run(){
    try {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ps = new PrintStream(socket.getOutputStream());
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(CrazyitProtocol.USER_ROUND) && line.endsWith(CrazyitProtocol.USER_ROUND)) {
                String userName = getRealMsg(line);
                if (Server.clients.containsKey(userName)) {
                    System.out.println("�ظ�");
                    ps.println(CrazyitProtocol.NAME_REP);
                } else {
                    System.out.println("�ɹ�");
                    ps.println(CrazyitProtocol.LOGIN_SUCCESS);
                    Server.clients.put(userName, ps);
                }
            } else if (line.startsWith(CrazyitProtocol.PRIVATE_ROUND) && line.endsWith(CrazyitProtocol.PRIVATE_ROUND)) {
                String userAndMsg = getRealMsg(line);
                String user = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[0];
                String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[1];
                Server.clients.get(user).println(Server.clients.getKeyByValue(ps) + "���ĵض���˵��" + msg);
            } else {
                String msg = getRealMsg(line);
                for (PrintStream clientPs : Server.clients.valueSet()) {
                    clientPs.println(Server.clients.getKeyByValue(ps) + "˵��" + msg);
                }
            }
        }
    } catch (IOException e) {
        Server.clients.removeByValue(ps);
        System.out.println(Server.clients.size());
        try {
            if (br != null) {
                br.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


public String getRealMsg(String line){
    return line.substring(CrazyitProtocol.PROTOCOL_LEN, line.length() - CrazyitProtocol.PROTOCOL_LEN);
}


}