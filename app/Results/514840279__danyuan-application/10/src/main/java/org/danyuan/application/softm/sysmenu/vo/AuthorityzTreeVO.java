package org.danyuan.application.softm.sysmenu.vo;
 import java.util.ArrayList;
import java.util.List;
public class AuthorityzTreeVO {

 private  String id;

 private  String name;

 private  boolean checked;

 private  List<AuthorityzTreeVO> children;

 private  boolean chkDisabled;

 private  String click;

 private  boolean halfCheck;

 private  String icon;

 private  String iconClose;

 private  String iconOpen;

 private  String iconSkin;

 private  String isHidden;

 private  String isParent;

 private  String nocheck;

 private  String open;

 private  String target;

 private  String url;

 private  Boolean homePage;


public void setName(String name){
    this.name = name;
}


public void setOpen(String open){
    this.open = open;
}


public String getName(){
    return name;
}


public String getOpen(){
    return open;
}


public String getIconOpen(){
    return iconOpen;
}


public void setHomePage(Boolean homePage){
    this.homePage = homePage;
}


public String getId(){
    return id;
}


public void setIconSkin(String iconSkin){
    this.iconSkin = iconSkin;
}


public String getClick(){
    return click;
}


public String getIconSkin(){
    return iconSkin;
}


public String getTarget(){
    return target;
}


public void setClick(String click){
    this.click = click;
}


public void setChecked(boolean checked){
    this.checked = checked;
}


public void setIsHidden(String isHidden){
    this.isHidden = isHidden;
}


public String getIsParent(){
    return isParent;
}


public void setId(String id){
    this.id = id;
}


public void setTarget(String target){
    this.target = target;
}


public Boolean getHomePage(){
    return homePage;
}


public void setIsParent(String isParent){
    this.isParent = isParent;
}


public void setNocheck(String nocheck){
    this.nocheck = nocheck;
}


public void setChkDisabled(boolean chkDisabled){
    this.chkDisabled = chkDisabled;
}


public String getIsHidden(){
    return isHidden;
}


public void setHalfCheck(boolean halfCheck){
    this.halfCheck = halfCheck;
}


public boolean isHalfCheck(){
    return halfCheck;
}


public boolean isChecked(){
    return checked;
}


public boolean isChkDisabled(){
    return chkDisabled;
}


public String getIcon(){
    return icon;
}


public void setIconClose(String iconClose){
    this.iconClose = iconClose;
}


public void setUrl(String url){
    this.url = url;
}


public void setIcon(String icon){
    this.icon = icon;
}


public List<AuthorityzTreeVO> getChildren(){
    return children;
}


public void setIconOpen(String iconOpen){
    this.iconOpen = iconOpen;
}


public String getUrl(){
    return url;
}


public String getIconClose(){
    return iconClose;
}


public String getNocheck(){
    return nocheck;
}


public void setChildren(List<AuthorityzTreeVO> children){
    this.children = children;
}


}