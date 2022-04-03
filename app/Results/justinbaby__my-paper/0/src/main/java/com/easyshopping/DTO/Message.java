package com.easyshopping.DTO;
 import com.easyshopping.util.SpringUtils;
public class Message {

 private  Type type;

 private  String content;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

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
public Type getType(){
    return type;
}


public String getContent(){
    return content;
}


public Message warn(String content,Object args){
    return new Message(Type.warn, content, args);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/warn"))

.queryParam("content",content)
.queryParam("args",args)
;
Message aux = restTemplate.getForObject(builder.toUriString(),Message.class);
return aux;
}


public Message error(String content,Object args){
    return new Message(Type.error, content, args);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/error"))

.queryParam("content",content)
.queryParam("args",args)
;
Message aux = restTemplate.getForObject(builder.toUriString(),Message.class);
return aux;
}


public Message success(String content,Object args){
    return new Message(Type.success, content, args);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/success"))

.queryParam("content",content)
.queryParam("args",args)
;
Message aux = restTemplate.getForObject(builder.toUriString(),Message.class);
return aux;
}


}