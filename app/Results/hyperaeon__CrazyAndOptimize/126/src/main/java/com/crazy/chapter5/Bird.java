package com.crazy.chapter5;
 public class Bird {

 private  Animal a;

public Bird(Animal a) {
    this.a = a;
}
public void breath(){
    a.breath();
}


public void beat(){
    a.beat();
}


}