package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class SystemNeed {

 private  Long id;

 private  String operatingSystem;

 private  String cpu;

 private  String ram;

 private  String graphicsCard;

 private  String directx;

 private  String network;

 private  String rom;

 private  String soundCard;

public SystemNeed() {
}public SystemNeed(String operatingSystem, String cpu, String ram, String graphicsCard, String directx, String network, String rom, String soundCard) {
    this.operatingSystem = operatingSystem;
    this.cpu = cpu;
    this.ram = ram;
    this.graphicsCard = graphicsCard;
    this.directx = directx;
    this.network = network;
    this.rom = rom;
    this.soundCard = soundCard;
}
public String getRam(){
    return ram;
}


public void setGraphicsCard(String graphicsCard){
    this.graphicsCard = graphicsCard;
}


public void setCpu(String cpu){
    this.cpu = cpu;
}


public String getOperatingSystem(){
    return operatingSystem;
}


public String getGraphicsCard(){
    return graphicsCard;
}


public String getDirectx(){
    return directx;
}


public Long getId(){
    return id;
}


public void setRom(String rom){
    this.rom = rom;
}


public void setSoundCard(String soundCard){
    this.soundCard = soundCard;
}


public String getRom(){
    return rom;
}


public void setNetwork(String network){
    this.network = network;
}


public String getSoundCard(){
    return soundCard;
}


public String getNetwork(){
    return network;
}


public void setId(Long id){
    this.id = id;
}


public String getCpu(){
    return cpu;
}


public void setOperatingSystem(String operatingSystem){
    this.operatingSystem = operatingSystem;
}


public void setDirectx(String directx){
    this.directx = directx;
}


public void setRam(String ram){
    this.ram = ram;
}


}