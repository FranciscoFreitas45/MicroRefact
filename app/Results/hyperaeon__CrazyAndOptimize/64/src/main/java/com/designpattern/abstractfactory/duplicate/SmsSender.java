package com.designpattern.abstractfactory.duplicate;
 public class SmsSender implements Sender{


@Override
public void send(){
    System.out.println("Sms sender");
}


}