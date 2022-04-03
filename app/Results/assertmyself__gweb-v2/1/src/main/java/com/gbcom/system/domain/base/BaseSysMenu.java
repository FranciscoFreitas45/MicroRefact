package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysMenu implements Serializable{

 public  String REF;

 public  String PROP_PARENT;

 public  String PROP_PARAM;

 public  String PROP_PRIVILEGE;

 public  String PROP_JS_EVENT;

 public  String PROP_MENU_LEVEL;

 public  String PROP_NAME;

 public  String PROP_TARGET;

 public  String PROP_URL;

 public  String PROP_IS_LEAF;

 public  String PROP_IS_VALID;

 public  String PROP_ID;

 public  String PROP_TREE_ID;

 public  String PROP_ICON;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String name;

 private  java.lang.String privilege;

 private  java.lang.Integer menuLevel;

 private  java.lang.String url;

 private  java.lang.String jsEvent;

 private  java.lang.Boolean isLeaf;

 private  java.lang.String treeId;

 private  java.lang.Boolean isValid;

 private  java.lang.String param;

 private  java.lang.String icon;

 private  java.lang.String target;

 private  com.gbcom.system.domain.SysMenu parent;

 private  java.util.Set<com.gbcom.system.domain.SysMenu> sysMenus;

// constructors
public BaseSysMenu() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysMenu(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysMenu(java.lang.Long id, java.lang.String name, java.lang.String privilege) {
    this.setId(id);
    this.setName(name);
    this.setPrivilege(privilege);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.String getName(){
    return name;
}


public void setParam(java.lang.String param){
    this.param = param;
}


public java.lang.Long getId(){
    return id;
}


public void setMenuLevel(java.lang.Integer menuLevel){
    this.menuLevel = menuLevel;
}


public java.lang.String getTarget(){
    return target;
}


public java.lang.String getPrivilege(){
    return privilege;
}


public void setPrivilege(java.lang.String privilege){
    this.privilege = privilege;
}


public int hashCode(){
    if (Integer.MIN_VALUE == this.hashCode) {
        if (null == this.getId())
            return super.hashCode();
        else {
            String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
            this.hashCode = hashStr.hashCode();
        }
    }
    return this.hashCode;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setTarget(java.lang.String target){
    this.target = target;
}


public void setIsLeaf(java.lang.Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setParent(com.gbcom.system.domain.SysMenu parent){
    this.parent = parent;
}


public void setJsEvent(java.lang.String jsEvent){
    this.jsEvent = jsEvent;
}


public void setSysMenus(java.util.Set<com.gbcom.system.domain.SysMenu> sysMenus){
    this.sysMenus = sysMenus;
}


public java.lang.Boolean getIsValid(){
    return isValid;
}


public java.util.Set<com.gbcom.system.domain.SysMenu> getSysMenus(){
    if (sysMenus == null) {
        sysMenus = new java.util.LinkedHashSet<com.gbcom.system.domain.SysMenu>();
    }
    return sysMenus;
}


public java.lang.Boolean getIsLeaf(){
    return isLeaf;
}


public java.lang.String getParam(){
    return param;
}


public com.gbcom.system.domain.SysMenu getParent(){
    return parent;
}


public void setIsValid(java.lang.Boolean isValid){
    this.isValid = isValid;
}


public void addTosysMenus(com.gbcom.system.domain.SysMenu sysMenu){
    if (null == getSysMenus())
        setSysMenus(new java.util.LinkedHashSet<com.gbcom.system.domain.SysMenu>());
    getSysMenus().add(sysMenu);
}


public void setUrl(java.lang.String url){
    this.url = url;
}


public void setTreeId(java.lang.String treeId){
    this.treeId = treeId;
}


public java.lang.String getIcon(){
    return icon;
}


public java.lang.String getTreeId(){
    return treeId;
}


public void setIcon(java.lang.String icon){
    this.icon = icon;
}


public java.lang.String getUrl(){
    return url;
}


public java.lang.Integer getMenuLevel(){
    return menuLevel;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysMenu))
        return false;
    else {
        com.gbcom.system.domain.SysMenu sysMenu = (com.gbcom.system.domain.SysMenu) obj;
        if (null == this.getId() || null == sysMenu.getId())
            return false;
        else
            return (this.getId().equals(sysMenu.getId()));
    }
}


public java.lang.String getJsEvent(){
    return jsEvent;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(name);
    builder.append(privilege);
    builder.append(menuLevel);
    builder.append(url);
    builder.append(jsEvent);
    builder.append(isLeaf);
    builder.append(treeId);
    builder.append(isValid);
    builder.append(param);
    builder.append(icon);
    builder.append(target);
    return builder.toString();
}


public void initialize(){
}


}