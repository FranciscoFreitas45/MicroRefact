package com.ukefu.webim.util.server.message;
 import com.ukefu.util.UKTools;
public class AgentServiceMessage extends Message{

 private  long serialVersionUID;

 private  String type;

 private  String id;


public String getType(){
    return type;
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


}