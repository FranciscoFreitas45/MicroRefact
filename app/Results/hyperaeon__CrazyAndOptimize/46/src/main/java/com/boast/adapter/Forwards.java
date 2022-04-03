package com.boast.adapter;
 public class Forwards extends Player{

public Forwards(String name) {
    super(name);
}
@Override
public void defense(){
    System.out.println("forwards defense");
}


@Override
public void attack(){
    System.out.println("forwards attack");
}


}