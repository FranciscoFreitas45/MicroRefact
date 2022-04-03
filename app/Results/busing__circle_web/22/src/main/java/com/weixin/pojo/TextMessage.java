package com.weixin.pojo;
 public class TextMessage {

 private  String ToUserName;

 private  String FromUserName;

 private  String CreateTime;

 private  String MsgType;

 private  String Content;

 private  String MsgId;

 private  String Event;

 private  String EventKey;


public String getMsgType(){
    return MsgType;
}


public String getEvent(){
    return Event;
}


public void setEventKey(String eventKey){
    EventKey = eventKey;
}


public String getCreateTime(){
    return CreateTime;
}


public void setContent(String content){
    Content = content;
}


public String getContent(){
    return Content;
}


public void setFromUserName(String fromUserName){
    FromUserName = fromUserName;
}


public void setCreateTime(String createTime){
    CreateTime = createTime;
}


public void setEvent(String event){
    Event = event;
}


public String getToUserName(){
    return ToUserName;
}


public void setMsgType(String msgType){
    MsgType = msgType;
}


public String getFromUserName(){
    return FromUserName;
}


public String getMsgId(){
    return MsgId;
}


public String getEventKey(){
    return EventKey;
}


public void setMsgId(String msgId){
    MsgId = msgId;
}


public void setToUserName(String toUserName){
    ToUserName = toUserName;
}


}