package com.DTO;
 public class MessageBean {

 private  String id;

 private  String typeId;

 private  String msgContent;

 private  String createTime;

 private  String status;

 private  String sendUserId;

 private  String reciveUserId;

 private  String params;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

/**
 * 无参构造函数
 */
public MessageBean() {
}/**
 * 部分参数构造函数
 */
public MessageBean(String typeId, String msgContent, String sendUserId, String reciveUserId, String params) {
    this.typeId = typeId;
    this.msgContent = msgContent;
    this.sendUserId = sendUserId;
    this.reciveUserId = reciveUserId;
    this.params = params;
}/**
 * 全参构造函数
 */
public MessageBean(String id, String typeId, String msgContent, String createTime, String status, String sendUserId, String params) {
    this.id = id;
    this.typeId = typeId;
    this.msgContent = msgContent;
    this.createTime = createTime;
    this.status = status;
    this.sendUserId = sendUserId;
    this.params = params;
}
public String getMsgContent(){
    return msgContent;
}


public String getCreateTime(){
    return createTime;
}


public String getId(){
    return id;
}


public String getStatus(){
    return status;
}


public String getTypeId(){
    return typeId;
}


public String getReciveUserId(){
    return reciveUserId;
}


public String getSendUserId(){
    return sendUserId;
}


public String getParams(){
    return params;
}


public void setTypeId(String typeId){
    this.typeId = typeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTypeId"))

.queryParam("typeId",typeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMsgContent(String msgContent){
    this.msgContent = msgContent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMsgContent"))

.queryParam("msgContent",msgContent)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSendUserId(String sendUserId){
    this.sendUserId = sendUserId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSendUserId"))

.queryParam("sendUserId",sendUserId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReciveUserId(String reciveUserId){
    this.reciveUserId = reciveUserId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReciveUserId"))

.queryParam("reciveUserId",reciveUserId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParams(String params){
    this.params = params;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParams"))

.queryParam("params",params)
;
restTemplate.put(builder.toUriString(),null);
}


}