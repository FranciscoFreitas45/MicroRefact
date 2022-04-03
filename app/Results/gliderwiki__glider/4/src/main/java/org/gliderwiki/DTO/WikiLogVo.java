package org.gliderwiki.DTO;
 public class WikiLogVo extends BaseObjectBean{

 private  Integer weSpaceIdx;

 private  String weSpaceName;

 private  String weSpaceDesc;

 private  String weAdminDix;

 private  Integer weWikiIdx;

 private  String weWikiTitle;

 private  Integer weWikiAgree;

 private  Integer weWikiViewCnt;

 private  String weWikiProtect;

 private  String weInsUser;

 private  String weInsUserName;

 private  String weWikiStatus;

 private  Integer weUserIdx;

 private  String weWikiRevision;

 private  String weWikiActionType;

 private  String weInsDate;

 private  String weCreDate;


public void setWeWikiActionType(String weWikiActionType){
    this.weWikiActionType = weWikiActionType;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public void setWeWikiRevision(String weWikiRevision){
    this.weWikiRevision = weWikiRevision;
}


public String getWeCreDate(){
    return weCreDate;
}


public String getWeWikiTitle(){
    return weWikiTitle;
}


public Integer getWeWikiAgree(){
    return weWikiAgree;
}


public String getWeWikiActionType(){
    return weWikiActionType;
}


public String getWeAdminDix(){
    return weAdminDix;
}


public void setWeCreDate(String weCreDate){
    this.weCreDate = weCreDate;
}


public void setWeSpaceName(String weSpaceName){
    this.weSpaceName = weSpaceName;
}


public String getWeInsUser(){
    return weInsUser;
}


public Integer getWeWikiIdx(){
    return weWikiIdx;
}


public String getWeWikiStatus(){
    return weWikiStatus;
}


public String getWeWikiProtect(){
    return weWikiProtect;
}


public Integer getWeWikiViewCnt(){
    return weWikiViewCnt;
}


public void setWeAdminDix(String weAdminDix){
    this.weAdminDix = weAdminDix;
}


public String getWeWikiRevision(){
    return weWikiRevision;
}


public String getWeInsUserName(){
    return weInsUserName;
}


public Integer getWeSpaceIdx(){
    return weSpaceIdx;
}


public String getWeInsDate(){
    return weInsDate;
}


public String getWeSpaceDesc(){
    return weSpaceDesc;
}


public String getWeSpaceName(){
    return weSpaceName;
}


}