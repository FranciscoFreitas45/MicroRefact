package com.designpattern.facade.duplicate;
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
    System.out.println("Begin computer startup");
    cpu.startUp();
    memory.startUp();
    disk.startup();
    System.out.println("End computer startup");
}


public void shutdown(){
    System.out.println("Begin computer shutdown");
    cpu.shutdown();
    memory.shutdown();
    disk.shutdown();
    System.out.println("End computer shutdown");
}


}