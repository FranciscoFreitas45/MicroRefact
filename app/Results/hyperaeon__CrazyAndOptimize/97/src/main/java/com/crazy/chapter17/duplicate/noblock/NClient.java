package com.crazy.chapter17.duplicate.noblock;
 import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
public class NClient {

 private  Selector selector;

 private  int PORT;

 private  Charset charset;

 private  SocketChannel sc;


public void init(){
    selector = Selector.open();
    InetSocketAddress isa = new InetSocketAddress("localhost", PORT);
    sc = SocketChannel.open(isa);
    sc.configureBlocking(false);
    sc.register(selector, SelectionKey.OP_READ);
    new ClientThread().start();
    Scanner scan = new Scanner(System.in);
    while (scan.hasNext()) {
        String line = scan.next();
        sc.write(charset.encode(line));
    }
}


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


public void main(String[] args){
    new NClient().init();
}


}