package kr.ac.sejong.api.service;
 public interface SocketService {


public void disconnect()
;

public String receiveMessage()
;

public boolean isConnected()
;

public boolean sendMessage(String message)
;

public boolean connect(String ip,int port)
;

}