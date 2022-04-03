package com.designpattern.abstractfactory.duplicate;
 public class EmailSender implements Sender{


@Override
public void send(){
    System.out.println("Email Sender");
}


}