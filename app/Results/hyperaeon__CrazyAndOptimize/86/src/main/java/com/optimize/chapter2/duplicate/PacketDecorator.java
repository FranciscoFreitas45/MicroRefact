package com.optimize.chapter2.duplicate;
 public class PacketDecorator implements IPacketCreator{

 private IPacketCreator component;

public PacketDecorator(IPacketCreator c) {
    this.component = c;
}
}