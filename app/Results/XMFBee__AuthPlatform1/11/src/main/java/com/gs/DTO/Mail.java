package com.gs.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getSubject(){
    return subject;
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


public String getType(){
    return type;
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


public void setSubject(String subject){
    this.subject = subject;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSubject"))

.queryParam("subject",subject)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBccRecipients(String bccRecipients){
    this.bccRecipients = bccRecipients;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBccRecipients"))

.queryParam("bccRecipients",bccRecipients)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMultinart(Multipart multipart){
    this.multipart = multipart;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMultinart"))

.queryParam("multipart",multipart)
;
restTemplate.put(builder.toUriString(),null);
}


}