package com.optimize.chapter2.duplicate.decorator;
 public class PacketDecorator implements IPacketCreator{

 private IPacketCreator component;

public PacketDecorator(IPacketCreator c) {
    component = c;
}
}