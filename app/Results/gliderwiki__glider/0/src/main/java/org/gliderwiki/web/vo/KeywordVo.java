package org.gliderwiki.web.vo;
 import java.util.Date;
public class KeywordVo extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer weWikiTagIdx;

 private  String weWikiTitle;

 private  Integer weWikiIdx;

 private  String weTag;

 private  Integer weWikiRevision;

 private  Date weInsDate;

 private  int startRow;

 private  int endRow;


public void setWeWikiTitle(String weWikiTitle){
    this.weWikiTitle = weWikiTitle;
}


public void setEndRow(int endRow){
    this.endRow = endRow;
}


public Integer getWeWikiTagIdx(){
    return weWikiTagIdx;
}


public void setWeWikiRevision(Integer weWikiRevision){
    this.weWikiRevision = weWikiRevision;
}


public void setWeTag(String weTag){
    this.weTag = weTag;
}


public Integer getWeWikiRevision(){
    return weWikiRevision;
}


public String getWeTag(){
    return weTag;
}


public String getWeWikiTitle(){
    return weWikiTitle;
}


public void setWeInsDate(Date weInsDate){
    this.weInsDate = weInsDate;
}


public void setStartRow(int startRow){
    this.startRow = startRow;
}


public int getStartRow(){
    return startRow;
}


public int getEndRow(){
    return endRow;
}


public void setWeWikiTagIdx(Integer weWikiTagIdx){
    this.weWikiTagIdx = weWikiTagIdx;
}


public Integer getWeWikiIdx(){
    return weWikiIdx;
}


public void setWeWikiIdx(Integer weWikiIdx){
    this.weWikiIdx = weWikiIdx;
}


public Date getWeInsDate(){
    return weInsDate;
}


}