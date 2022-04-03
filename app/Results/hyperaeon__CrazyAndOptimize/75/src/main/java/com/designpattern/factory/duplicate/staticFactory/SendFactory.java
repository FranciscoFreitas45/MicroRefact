package com.designpattern.factory.duplicate.staticFactory;
 import com.designpattern.factory.duplicate.simpleFactory.EmailSender;
import com.designpattern.factory.duplicate.simpleFactory.Sender;
import com.designpattern.factory.duplicate.simpleFactory.SmsSender;
public class SendFactory {


public Sender produceEmail(){
    return new EmailSender();
}


public Sender produceSms(){
    return new SmsSender();
}


}