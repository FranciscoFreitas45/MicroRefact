package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
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
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "[name=" + name + ",hot=" + hotNum + "]";
}


public void setHotNum(int hotNum){
    this.hotNum = hotNum;
}


public int getHotNum(){
    return hotNum;
}


}