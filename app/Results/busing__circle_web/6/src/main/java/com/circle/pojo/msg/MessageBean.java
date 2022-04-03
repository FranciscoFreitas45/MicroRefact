package com.circle.pojo.msg;
 public class MessageBean {

 private  String id;

 private  String typeId;

 private  String msgContent;

 private  String createTime;

 private  String status;

 private  String sendUserId;

 private  String reciveUserId;

 private  String params;

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


public void setMsgContent(String msgContent){
    this.msgContent = msgContent;
}


public void setCreateTime(String createTime){
    this.createTime = createTime;
}


public void setReciveUserId(String reciveUserId){
    this.reciveUserId = reciveUserId;
}


public void setTypeId(String typeId){
    this.typeId = typeId;
}


public String getStatus(){
    return status;
}


public String getTypeId(){
    return typeId;
}


public void setStatus(String status){
    this.status = status;
}


public void setSendUserId(String sendUserId){
    this.sendUserId = sendUserId;
}


public String getReciveUserId(){
    return reciveUserId;
}


public void setId(String id){
    this.id = id;
}


public String getSendUserId(){
    return sendUserId;
}


public String getParams(){
    return params;
}


public void setParams(String params){
    this.params = params;
}


}