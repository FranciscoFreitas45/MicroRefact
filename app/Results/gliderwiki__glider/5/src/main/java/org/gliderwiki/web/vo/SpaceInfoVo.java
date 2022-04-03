package org.gliderwiki.web.vo;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.AuthorityType;
public class SpaceInfoVo {

 private  Integer spaceIdx;

 private  String spaceName;

 private  String spaceDesc;

 private  String profileImg;

 private  boolean exposed;

 private  AuthorityType viewPrivacy;

 private  AuthorityType editPrivacy;

 private  Integer adminUserIdx;

 private List<Map<String,Object>> viewParticipant;

 private List<Map<String,Object>> editParticipant;

public SpaceInfoVo() {
}
public void setSpaceIdx(Integer spaceIdx){
    this.spaceIdx = spaceIdx;
}


public void setEditPrivacy(AuthorityType editPrivacy){
    this.editPrivacy = editPrivacy;
}


public AuthorityType getViewPrivacy(){
    return viewPrivacy;
}


public void setExposed(boolean exposed){
    this.exposed = exposed;
}


public void setAdminUserIdx(Integer adminUserIdx){
    this.adminUserIdx = adminUserIdx;
}


public List<Map<String,Object>> getEditParticipant(){
    return editParticipant;
}


public String getSpaceDesc(){
    return spaceDesc;
}


public void setSpaceDesc(String spaceDesc){
    this.spaceDesc = spaceDesc;
}


public void setViewPrivacy(AuthorityType viewPrivacy){
    this.viewPrivacy = viewPrivacy;
}


public void setSpaceName(String spaceName){
    this.spaceName = spaceName;
}


public void setViewParticipant(List<Map<String,Object>> viewParticipant){
    this.viewParticipant = viewParticipant;
}


public Integer getSpaceIdx(){
    return spaceIdx;
}


public List<Map<String,Object>> getViewParticipant(){
    return viewParticipant;
}


public String getSpaceName(){
    return spaceName;
}


public String getProfileImg(){
    return profileImg;
}


public void setProfileImg(String profileImg){
    this.profileImg = profileImg;
}


public void setEditParticipant(List<Map<String,Object>> editParticipant){
    this.editParticipant = editParticipant;
}


public Integer getAdminUserIdx(){
    return adminUserIdx;
}


public boolean isExposed(){
    return exposed;
}


public AuthorityType getEditPrivacy(){
    return editPrivacy;
}


}