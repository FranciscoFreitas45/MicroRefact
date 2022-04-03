package com.cym.utils;
 import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
public class TelnetUtils {


public boolean isRunning(String host,int port){
    Socket sClient = null;
    try {
        SocketAddress saAdd = new InetSocketAddress(host.trim(), port);
        sClient = new Socket();
        // 设置超时 1s
        sClient.connect(saAdd, 1000);
    } catch (UnknownHostException e) {
        return false;
    } catch (SocketTimeoutException e) {
        return false;
    } catch (IOException e) {
        return false;
    } catch (Exception e) {
        return false;
    } finally {
        try {
            if (sClient != null) {
                sClient.close();
            }
        } catch (Exception e) {
        }
    }
    return true;
}


public void main(String[] args){
    System.out.println(isRunning("127.0.0.1", 8080));
}


}