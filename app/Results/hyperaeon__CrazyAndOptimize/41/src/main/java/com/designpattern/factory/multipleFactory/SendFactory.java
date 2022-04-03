package com.designpattern.factory.multipleFactory;
 import com.designpattern.factory.simpleFactory.MailSender;
import com.designpattern.factory.simpleFactory.Sender;
import com.designpattern.factory.simpleFactory.SmsSender;
public class SendFactory {


public Sender produceMail(){
    return new MailSender();
}


public Sender produceSms(){
    return new SmsSender();
}


}