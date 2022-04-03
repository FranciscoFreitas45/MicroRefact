package com.boast.adapter;
 public class Guards extends Player{

public Guards(String name) {
    super(name);
}
@Override
public void defense(){
    System.out.println("guards defense");
}


@Override
public void attack(){
    System.out.println("guards attack");
}


}