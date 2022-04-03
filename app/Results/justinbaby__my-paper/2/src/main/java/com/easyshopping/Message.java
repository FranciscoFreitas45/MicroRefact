package com.easyshopping;
 import com.easyshopping.util.SpringUtils;
public class Message {

 private  Type type;

 private  String content;

/**
 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
 */
public Message() {
}/**
 * 初始化一个新创建的 Message 对象
 *
 * @param type
 *            类型
 * @param content
 *            内容
 */
public Message(Type type, String content) {
    this.type = type;
    this.content = content;
}/**
 * @param type
 *            类型
 * @param content
 *            内容
 * @param args
 *            参数
 */
public Message(Type type, String content, Object... args) {
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