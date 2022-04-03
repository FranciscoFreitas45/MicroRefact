package com.lingxiang2014;
 import com.lingxiang2014.util.SpringUtils;
public class Message {

 private  Type type;

 private  String content;

public Message() {
}public Message(Type type, String content) {
    this.type = type;
    this.content = content;
}public Message(Type type, String content, Object... args) {
    this.type = type;
    this.content = SpringUtils.getMessage(content, args);
}
public Message warn(String content,Object args){
    return new Message(Type.warn, content, args);
}


public void setContent(String content){
    this.content = content;
}


public Type getType(){
    return type;
}


public Message success(String content,Object args){
    return new Message(Type.success, content, args);
}


public String getContent(){
    return content;
}


@Override
public String toString(){
    return SpringUtils.getMessage(content);
}


public Message error(String content,Object args){
    return new Message(Type.error, content, args);
}


public void setType(Type type){
    this.type = type;
}


}