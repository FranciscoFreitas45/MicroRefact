package org.danyuan.application.crawler.config.po;
 import org.springframework.data.annotation.Id;
public class SysRuleInfo {

@Id
 private  String uuid;

 private  String pageType;

 private  String pageName;

 private  GroupList groupList;

 private  GroupList dictList;


public String getPageName(){
    return pageName;
}


public GroupList getGroupList(){
    return groupList;
}


public void setGroupList(GroupList groupList){
    this.groupList = groupList;
}


public void setPageType(String pageType){
    this.pageType = pageType;
}


public void setPageName(String pageName){
    this.pageName = pageName;
}


public String getUuid(){
    return uuid;
}


public String getPageType(){
    return pageType;
}


public void setDictList(GroupList dictList){
    this.dictList = dictList;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public GroupList getDictList(){
    return dictList;
}


}