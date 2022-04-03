package com.boast.adapter;
 public class AdapterMain {


public void main(String[] args){
    Player b = new Forwards("Badeer");
    b.attack();
    Player ch = new Translator("YO");
    ch.attack();
}


}