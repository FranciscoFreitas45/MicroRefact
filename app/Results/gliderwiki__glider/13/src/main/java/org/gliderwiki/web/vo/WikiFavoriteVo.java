package org.gliderwiki.web.vo;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.web.domain.FavorityType;
public class WikiFavoriteVo extends BaseObjectBean{

 private  String weSpaceName;

 private  String weSpaceDesc;

 private  String weWikiTitle;

 private  String weWikiRevision;

 private  String weWikiStatus;

 private  Integer weAdminIdx;

 private  Integer weUserIdx;

@Column(name = "we_favorite_type")
 private  FavorityType weFavoriteType;

 private  Integer weSpaceIdx;

 private  Integer weWikiIdx;

 private  String weUseYn;

 private  String weAddDate;

 private  String weDelDate;


public void setWeAdminIdx(Integer weAdminIdx){
    this.weAdminIdx = weAdminIdx;
}


public void setWeWikiTitle(String weWikiTitle){
    this.weWikiTitle = weWikiTitle;
}


public String getWeAddDate(){
    return weAddDate;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public void setWeWikiRevision(String weWikiRevision){
    this.weWikiRevision = weWikiRevision;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


public String getWeWikiTitle(){
    return weWikiTitle;
}


public void setWeFavoriteType(FavorityType weFavoriteType){
    this.weFavoriteType = weFavoriteType;
}


public String getWeDelDate(){
    return weDelDate;
}


public void setWeDelDate(String weDelDate){
    this.weDelDate = weDelDate;
}


public void setWeSpaceDesc(String weSpaceDesc){
    this.weSpaceDesc = weSpaceDesc;
}


public String getWeUseYn(){
    return weUseYn;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public void setWeSpaceName(String weSpaceName){
    this.weSpaceName = weSpaceName;
}


public Integer getWeWikiIdx(){
    return weWikiIdx;
}


public void setWeWikiIdx(Integer weWikiIdx){
    this.weWikiIdx = weWikiIdx;
}


public FavorityType getWeFavoriteType(){
    return weFavoriteType;
}


public void setWeAddDate(String weAddDate){
    this.weAddDate = weAddDate;
}


public String getWeWikiStatus(){
    return weWikiStatus;
}


public Integer getWeAdminIdx(){
    return weAdminIdx;
}


public void setWeSpaceIdx(Integer weSpaceIdx){
    this.weSpaceIdx = weSpaceIdx;
}


public String getWeWikiRevision(){
    return weWikiRevision;
}


public Integer getWeSpaceIdx(){
    return weSpaceIdx;
}


public String getWeSpaceDesc(){
    return weSpaceDesc;
}


public void setWeWikiStatus(String weWikiStatus){
    this.weWikiStatus = weWikiStatus;
}


public String getWeSpaceName(){
    return weSpaceName;
}


}