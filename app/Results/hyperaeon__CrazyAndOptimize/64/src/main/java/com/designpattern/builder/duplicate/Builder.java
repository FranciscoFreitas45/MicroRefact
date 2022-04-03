package com.designpattern.builder.duplicate;
 import java.util.ArrayList;
import java.util.List;
import com.designpattern.abstractfactory.duplicate.EmailSender;
import com.designpattern.abstractfactory.duplicate.Sender;
import com.designpattern.abstractfactory.duplicate.SmsSender;
public class Builder {

 private  List<Sender> senderList;


public void produceSmsSender(int count){
    for (int i = 0; i < count; i++) {
        senderList.add(new SmsSender());
    }
}


public void produceEmailSender(int count){
    for (int i = 0; i < count; i++) {
        senderList.add(new EmailSender());
    }
}


}