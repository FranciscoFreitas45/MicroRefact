package com.optimize.chapter2.duplicate.decorator;
 public class Main {


public void main(String[] args){
    IPacketCreator pc = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new PacketBodyCreator()));
    System.out.println(pc.handleContent());
}


}