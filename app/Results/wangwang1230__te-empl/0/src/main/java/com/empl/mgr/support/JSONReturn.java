package com.empl.mgr.support;
 import java.io.Serializable;
public class JSONReturn implements Serializable{

 private  long serialVersionUID;

 private  boolean head;

 private  Object body;

 private  boolean HEAD_SUCCESS;

 private  boolean HEAD_FAILURE;

 private  String EMPTY_BODY;

public JSONReturn() {
// TODO Auto-generated constructor stub
}public JSONReturn(boolean head, Object body) {
    super();
    this.head = head;
    this.body = body;
}
public JSONReturn buildFailure(Object body){
    return build(HEAD_FAILURE, body);
}


public Object getBody(){
    return body;
}


public JSONReturn build(boolean head,Object body){
    return new JSONReturn(head, body);
}


public void setHead(boolean head){
    this.head = head;
}


public boolean isHead(){
    return head;
}


public JSONReturn buildFailureWithEmptyBody(){
    return build(HEAD_FAILURE, EMPTY_BODY);
}


public void setBody(Object body){
    this.body = body;
}


public JSONReturn buildSuccess(Object body){
    return build(HEAD_SUCCESS, body);
}


public JSONReturn buildSuccessWithEmptyBody(){
    return build(HEAD_SUCCESS, EMPTY_BODY);
}


}