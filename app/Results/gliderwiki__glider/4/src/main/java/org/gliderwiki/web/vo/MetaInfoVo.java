package org.gliderwiki.web.vo;
 public class MetaInfoVo {

 private  Integer weMetaIdx;

 private  String weMetaType;

 private  String weMetaDomain;

 private  String weMetaDesc;

 private  String weMetaTableName;

 private  String weMetaTableKey;

 private  String weUseYn;

 private  String userCheckYn;


public String getWeMetaTableKey(){
    return weMetaTableKey;
}


public String getWeMetaType(){
    return weMetaType;
}


public void setWeMetaType(String weMetaType){
    this.weMetaType = weMetaType;
}


public void setWeMetaDomain(String weMetaDomain){
    this.weMetaDomain = weMetaDomain;
}


public String getWeMetaDesc(){
    return weMetaDesc;
}


public void setWeMetaDesc(String weMetaDesc){
    this.weMetaDesc = weMetaDesc;
}


public Integer getWeMetaIdx(){
    return weMetaIdx;
}


public String getUserCheckYn(){
    return userCheckYn;
}


public void setUserCheckYn(String userCheckYn){
    this.userCheckYn = userCheckYn;
}


public String getWeMetaTableName(){
    return weMetaTableName;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


public void setWeMetaTableName(String weMetaTableName){
    this.weMetaTableName = weMetaTableName;
}


public void setWeMetaTableKey(String weMetaTableKey){
    this.weMetaTableKey = weMetaTableKey;
}


public String getWeUseYn(){
    return weUseYn;
}


public void setWeMetaIdx(Integer weMetaIdx){
    this.weMetaIdx = weMetaIdx;
}


public String getWeMetaDomain(){
    return weMetaDomain;
}


}