package com.boast.adapter;
 public class Center extends Player{

public Center(String name) {
    super(name);
}
@Override
public void defense(){
    System.out.println("center defense");
}


@Override
public void attack(){
    System.out.println("center attack");
}


}