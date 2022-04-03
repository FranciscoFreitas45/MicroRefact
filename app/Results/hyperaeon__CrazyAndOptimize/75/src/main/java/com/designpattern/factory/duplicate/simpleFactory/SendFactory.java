package com.designpattern.factory.duplicate.simpleFactory;
 public class SendFactory {


public Sender produce(String type){
    if ("sms".equals(type)) {
        return new SmsSender();
    } else if ("email".equals(type)) {
        return new EmailSender();
    } else {
        System.out.println("Please input the correct type");
        return null;
    }
}


}