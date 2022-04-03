package com.designpattern.builder;
 import java.util.ArrayList;
import java.util.List;
import com.designpattern.abstractfactory.MailSender;
import com.designpattern.abstractfactory.Sender;
import com.designpattern.abstractfactory.SmsSender;
public class Builder {

 private  List<Sender> list;


public void produceSmsSender(int count){
    for (int i = 0; i < count; i++) {
        list.add(new SmsSender());
    }
}


public void produceMailSender(int count){
    for (int i = 0; i < count; i++) {
        list.add(new MailSender());
    }
}


}