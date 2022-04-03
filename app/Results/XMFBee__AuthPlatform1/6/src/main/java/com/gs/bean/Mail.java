package com.gs.bean;
 import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
public class Mail {

 public  String HTML;

 public  String TEXT;

 private  String from;

 private  String recipients;

 private  String ccRecipients;

 private  String bccRecipients;

 private  String subject;

 private  String content;

 private  String type;

 private  Multipart multipart;


public String getSubject(){
    return subject;
}


public void setCcRecipients(String ccRecipients){
    this.ccRecipients = ccRecipients;
}


public void setSubject(String subject){
    this.subject = subject;
}


public void setContent(String content){
    this.content = content;
}


public Address[] getCcRecipients(){
    try {
        if (ccRecipients != null) {
            return InternetAddress.parse(ccRecipients);
        }
    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
}


public void setFrom(String from){
    this.from = from;
}


public String getContent(){
    return content;
}


public Address[] getBccRecipients(){
    try {
        if (bccRecipients != null) {
            return InternetAddress.parse(bccRecipients);
        }
    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
}


public void setType(String type){
    this.type = type;
}


public void setRecipients(String recipients){
    this.recipients = recipients;
}


public void setMultinart(Multipart multipart){
    this.multipart = multipart;
}


public String getType(){
    return type;
}


public void setBccRecipients(String bccRecipients){
    this.bccRecipients = bccRecipients;
}


public Address[] getRecipients(){
    try {
        return InternetAddress.parse(recipients);
    } catch (AddressException e) {
        e.printStackTrace();
    }
    return null;
}


public Address getFrom(){
    try {
        return InternetAddress.parse(from)[0];
    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return null;
}


public Multipart getMultipart(){
    return multipart;
}


}