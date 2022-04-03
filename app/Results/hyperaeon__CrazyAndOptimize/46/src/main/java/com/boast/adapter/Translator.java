package com.boast.adapter;
 public class Translator extends Player{

 private  ForeignCenter foreignCenter;

public Translator(String name) {
    super(name);
}
@Override
public void defense(){
    foreignCenter.defense();
}


@Override
public void attack(){
    foreignCenter.attack();
}


}