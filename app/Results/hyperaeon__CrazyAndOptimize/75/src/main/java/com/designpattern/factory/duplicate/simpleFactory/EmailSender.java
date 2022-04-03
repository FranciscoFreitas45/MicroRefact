package com.designpattern.factory.duplicate.simpleFactory;
 public class EmailSender implements Sender{


@Override
public void send(){
    System.out.println("This is email sender");
}


}