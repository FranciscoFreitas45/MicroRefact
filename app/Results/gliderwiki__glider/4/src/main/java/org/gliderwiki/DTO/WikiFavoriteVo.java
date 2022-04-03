package org.gliderwiki.DTO;
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

 private  FavorityType weFavoriteType;

 private  Integer weSpaceIdx;

 private  Integer weWikiIdx;

 private  String weUseYn;

 private  String weAddDate;

 private  String weDelDate;


public void setWeWikiTitle(String weWikiTitle){
    this.weWikiTitle = weWikiTitle;
}


public String getWeAddDate(){
    return weAddDate;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public void setWeUseYn(String weUseYn){
    this.weUseYn = weUseYn;
}


public String getWeWikiTitle(){
    return weWikiTitle;
}


public String getWeDelDate(){
    return weDelDate;
}


public void setWeSpaceDesc(String weSpaceDesc){
    this.weSpaceDesc = weSpaceDesc;
}


public String getWeUseYn(){
    return weUseYn;
}


public void setWeSpaceName(String weSpaceName){
    this.weSpaceName = weSpaceName;
}


public Integer getWeWikiIdx(){
    return weWikiIdx;
}


public FavorityType getWeFavoriteType(){
    return weFavoriteType;
}


public String getWeWikiStatus(){
    return weWikiStatus;
}


public Integer getWeAdminIdx(){
    return weAdminIdx;
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


public String getWeSpaceName(){
    return weSpaceName;
}


}