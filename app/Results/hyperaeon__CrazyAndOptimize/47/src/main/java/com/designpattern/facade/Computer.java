package com.designpattern.facade;
 public class Computer {

 private  CPU cpu;

 private  Memory memory;

 private  Disk disk;

public Computer() {
    cpu = new CPU();
    memory = new Memory();
    disk = new Disk();
}
public void startup(){
    System.out.println("Startup the computer!");
    cpu.startup();
    memory.startup();
    disk.startup();
    System.out.println("start computer finished!");
}


public void shutdown(){
    System.out.println("Begin to close the computer!");
    cpu.shutdown();
    memory.shutdown();
    disk.shutdown();
    System.out.println("End to close the computer!");
}


}