package com.designpattern.abstractfactory;
 public class MailSender implements Sender{


@Override
public void send(){
    System.out.println("This is mail sender");
}


}