package com.example.steam.DTO;
 import org.springframework.stereotype.Component;
public class Label {

 private  Long id;

 private  String name;

 private  int hotNum;

public Label() {
}public Label(Long id, String name, Integer hotNum) {
    this.id = id;
    this.name = name;
    this.hotNum = hotNum;
}
public String getName(){
    return name;
}


public Long getId(){
    return id;
}


public void setHotNum(int hotNum){
    this.hotNum = hotNum;
}


public int getHotNum(){
    return hotNum;
}


}