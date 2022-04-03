package com.ukefu.webim.util.server.message;
 import com.ukefu.util.UKTools;
public class NewRequestMessage extends Message{

 private  long serialVersionUID;

 private  String id;

 private  String type;

 private  boolean noagent;


public String getType(){
    return type;
}


public boolean isNoagent(){
    return noagent;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public void setNoagent(boolean noagent){
    this.noagent = noagent;
}


}