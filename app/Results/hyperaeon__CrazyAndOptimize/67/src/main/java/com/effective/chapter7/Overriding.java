package com.effective.chapter7;
 public class Overriding {


public void main(String[] args){
    Wine[] wines = { new Wine(), new SparklingWine(), new Champagne() };
    for (Wine w : wines) {
        System.out.println(w.name());
    }
}


}