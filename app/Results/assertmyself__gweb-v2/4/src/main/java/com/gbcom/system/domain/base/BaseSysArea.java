package com.gbcom.system.domain.base;
 import java.io.Serializable;
import java.util.Set;
import com.gbcom.system.domain.SysAreaNes;
public class BaseSysArea implements Serializable{

 public  String REF;

 public  String PROP_AREA_NAME;

 public  String PROP_PID;

 public  String PROP_ZIPCODE;

 public  String PROP_LAYER;

 public  String PROP_DISPLAY_NAME;

 public  String PROP_ID;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.Long layer;

 private  java.lang.String areaName;

 private  java.lang.String areaCode;

 private  java.lang.String displayName;

 private  java.lang.Boolean isReserved;

 private  java.lang.Boolean isLeaf;

 private  java.lang.String treeId;

 private  java.lang.String description;

 private  com.gbcom.system.domain.SysArea parent;

 private  Set<SysAreaNes> nes;

// constructors
public BaseSysArea() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysArea(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setLayer(java.lang.Long layer){
    this.layer = layer;
}


public java.lang.Long getId(){
    return id;
}


public void setDescription(java.lang.String description){
    this.description = description;
}


public void setDisplayName(java.lang.String displayName){
    this.displayName = displayName;
}


public java.lang.String getDescription(){
    return description;
}


public void setIsReserved(java.lang.Boolean isReserved){
    this.isReserved = isReserved;
}


public java.lang.String getAreaCode(){
    return areaCode;
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


public void setNes(Set<SysAreaNes> nes){
    this.nes = nes;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setIsLeaf(java.lang.Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setParent(com.gbcom.system.domain.SysArea parent){
    this.parent = parent;
}


public java.lang.Boolean getIsLeaf(){
    return isLeaf;
}


public com.gbcom.system.domain.SysArea getParent(){
    return parent;
}


public java.lang.Long getLayer(){
    return layer;
}


public void setAreaCode(java.lang.String zipcode){
    this.areaCode = zipcode;
}


public java.lang.String getAreaName(){
    return areaName;
}


public Set<SysAreaNes> getNes(){
    return nes;
}


public void setTreeId(java.lang.String treeId){
    this.treeId = treeId;
}


public java.lang.String getTreeId(){
    return treeId;
}


public java.lang.String getDisplayName(){
    return displayName;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysUser))
        return false;
    else {
        com.gbcom.system.domain.SysUser sysUser = (com.gbcom.system.domain.SysUser) obj;
        if (null == this.getId() || null == sysUser.getId())
            return false;
        else
            return (this.getId().equals(sysUser.getId()));
    }
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    // builder.append(parent.getId());
    builder.append(layer);
    builder.append(displayName);
    builder.append(areaName);
    builder.append(areaCode);
    return builder.toString();
}


public void setAreaName(java.lang.String areaName){
    this.areaName = areaName;
}


public void initialize(){
}


public java.lang.Boolean getIsReserved(){
    return isReserved;
}


}