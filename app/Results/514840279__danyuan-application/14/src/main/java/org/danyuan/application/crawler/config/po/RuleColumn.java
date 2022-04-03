package org.danyuan.application.crawler.config.po;
 import java.util.List;
public class RuleColumn {

 private  String uuid;

 private  String name;

 private  String rule;

 private  String type;

 private  String detialUrl;

 private  String imgUrl;

 private  String pageType;

 private  String pageName;

 private  List<GroupList> groupLists;


public void setName(String name){
    this.name = name;
}


public String getPageName(){
    return pageName;
}


public String getName(){
    return name;
}


public void setPageType(String pageType){
    this.pageType = pageType;
}


public void setImgUrl(String imgUrl){
    this.imgUrl = imgUrl;
}


public String getPageType(){
    return pageType;
}


public void setType(String type){
    this.type = type;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public List<GroupList> getGroupLists(){
    return groupLists;
}


public String getDetialUrl(){
    return detialUrl;
}


public void setDetialUrl(String detialUrl){
    this.detialUrl = detialUrl;
}


public void setRule(String rule){
    this.rule = rule;
}


public String getType(){
    return type;
}


public String getRule(){
    return rule;
}


public String getImgUrl(){
    return imgUrl;
}


public void setPageName(String pageName){
    this.pageName = pageName;
}


public String getUuid(){
    return uuid;
}


public void setGroupLists(List<GroupList> groupLists){
    this.groupLists = groupLists;
}


}