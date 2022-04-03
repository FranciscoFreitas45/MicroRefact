package com.interview.chapter10;
 public class Base {

 private int i;

Base() {
    add(1);
    System.out.println(i);
}
public void add(int v){
    i += v;
    System.out.println(i);
}


public void print(){
    System.out.println(i);
}


}