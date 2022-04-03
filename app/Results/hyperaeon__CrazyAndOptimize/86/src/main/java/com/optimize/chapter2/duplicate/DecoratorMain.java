package com.optimize.chapter2.duplicate;
 public class DecoratorMain {


public void main(String[] args){
    IPacketCreator pc = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new PacketBodyCreator()));
    System.out.println(pc.handleContent());
}


}