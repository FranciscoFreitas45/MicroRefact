package com.zis.common.mail;
 public class MailSenderFactory {

 private  SimpleMailSender serviceSms;


public void main(String[] args){
    try {
        getSender().send(new String[] { "lvbin0502@126.com" }, "test", "testshangde");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


public SimpleMailSender getSender(){
    if (serviceSms == null) {
        serviceSms = new SimpleMailSender("lvbin0502@126.com", "716startY");
    }
    return serviceSms;
}


}