package com.crazy.chapter17.duplicate.noblock.NClient;
 import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
public class ClientThread extends Thread{


public void run(){
    try {
        while (selector.select() > 0) {
            for (SelectionKey sk : selector.selectedKeys()) {
                selector.selectedKeys().remove(sk);
                if (sk.isReadable()) {
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    while (sc.read(buff) > 0) {
                        // sc.read(buff);
                        buff.flip();
                        content += charset.decode(buff);
                    }
                    System.out.println("聊天信息：" + content);
                    sk.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}