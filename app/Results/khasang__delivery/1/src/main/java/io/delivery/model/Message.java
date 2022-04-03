package io.delivery.model;
 import org.springframework.stereotype.Component;
@Component
public class Message {

 private  String infoMessage;

public Message(String infoMessage) {
    this.infoMessage = infoMessage;
}public Message() {
}
public void setInfoMessage(String infoMessage){
    this.infoMessage = infoMessage;
}


public void sqlTask(){
}


public String getInfoMessage(){
    return infoMessage;
}


}