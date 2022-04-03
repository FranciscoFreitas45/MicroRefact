package com.lingxiang2014.DTO;
 import com.lingxiang2014.util.SpringUtils;
public class Message {

 private  Type type;

 private  String content;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

public Message() {
}public Message(Type type, String content) {
    this.type = type;
    this.content = content;
}public Message(Type type, String content, Object... args) {
    this.type = type;
    this.content = SpringUtils.getMessage(content, args);
}
public Type getType(){
    return type;
}


public String getContent(){
    return content;
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


}