package com.optimize.chapter2.decorator;
 public class PacketDecorator implements IPacketCreator{

 private IPacketCreator component;

public PacketDecorator(IPacketCreator component) {
    this.component = component;
}
}