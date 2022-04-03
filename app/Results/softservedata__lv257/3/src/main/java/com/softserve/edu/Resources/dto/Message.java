package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.entity.ResourceRequest;
public class Message {

 private  long id_request;

 private  Purpose purpose;

 private  String comment;

public Message(long id_request, Purpose purpose, String comment) {
    this.id_request = id_request;
    this.purpose = purpose;
    this.comment = comment;
}public Message() {
}
public Purpose getPurpose(){
    return purpose;
}


@Override
public ResourceRequest.Status getNeededStatus(){
    return ResourceRequest.Status.TO_REFINEMENT;
}


public void setId_request(long id_request){
    this.id_request = id_request;
}


public ResourceRequest.Status getRequestStatus(){
    return purpose.getNeededStatus();
}


public void setPurpose(Purpose purpose){
    this.purpose = purpose;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getComment(){
    return comment;
}


@Override
public String toString(){
    return "Message{" + "id_request=" + id_request + ", purpose=" + purpose + ", comment='" + comment + '\'' + '}';
}


public long getId_request(){
    return id_request;
}


@Override
public String getMessageContent(){
    return "Your request needed more information.\n";
}


}