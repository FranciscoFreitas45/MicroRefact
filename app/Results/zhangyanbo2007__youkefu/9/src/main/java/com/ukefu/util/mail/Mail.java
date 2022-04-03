package com.ukefu.util.mail;
 import java.util.List;
import com.ukefu.util.event.UserEvent;
public class Mail implements UserEvent{

 private  long serialVersionUID;

 private  String email;

 private  String cc;

 private  List<String> filenames;

 private  String subject;

 private  String content;

public Mail(String email, String subject, String content) {
    super();
    this.email = email;
    this.subject = subject;
    this.content = content;
}public Mail() {
    super();
}public Mail(String email, String cc, List<String> filenames, String subject, String content) {
    super();
    this.email = email;
    this.cc = cc;
    this.filenames = filenames;
    this.subject = subject;
    this.content = content;
}
public String getSubject(){
    return subject;
}


public void setSubject(String subject){
    this.subject = subject;
}


public void setContent(String content){
    this.content = content;
}


public void setEmail(String email){
    this.email = email;
}


public List<String> getFilenames(){
    return filenames;
}


public void setCc(String cc){
    this.cc = cc;
}


public String getContent(){
    return content;
}


public String getEmail(){
    return email;
}


public String getCc(){
    return cc;
}


public void setFilenames(List<String> filenames){
    this.filenames = filenames;
}


}