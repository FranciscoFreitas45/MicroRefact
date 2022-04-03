package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysCodeDetail implements Serializable{

 public  String REF;

 public  String PROP_NAME;

 public  String PROP_PARENT;

 public  String PROP_SYS_CODE;

 public  String PROP_DESCRIPTION;

 public  String PROP_IS_LEAF;

 public  String PROP_IS_VALID;

 public  String PROP_TAG;

 public  String PROP_ID;

 public  String PROP_IS_RESERVED;

 public  String PROP_TREE_ID;

 public  String PROP_CODE;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.Boolean isLeaf;

 private  java.lang.String treeId;

 private  java.lang.Boolean isReserved;

 private  java.lang.String tag;

 private  java.lang.Boolean isValid;

 private  java.lang.String description;

 private  com.gbcom.system.domain.SysCodeDetail parent;

 private  com.gbcom.system.domain.SysCode sysCode;

// constructors
public BaseSysCodeDetail() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysCodeDetail(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
}


public void setSysCode(com.gbcom.system.domain.SysCode sysCode){
    this.sysCode = sysCode;
}


public java.lang.String getName(){
    return name;
}


public java.lang.Long getId(){
    return id;
}


public void setDescription(java.lang.String description){
    this.description = description;
}


public com.gbcom.system.domain.SysCode getSysCode(){
    return sysCode;
}


public java.lang.String getDescription(){
    return description;
}


public void setIsReserved(java.lang.Boolean isReserved){
    this.isReserved = isReserved;
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


public void setTag(java.lang.String tag){
    this.tag = tag;
}


public void setIsLeaf(java.lang.Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setParent(com.gbcom.system.domain.SysCodeDetail parent){
    this.parent = parent;
}


public java.lang.String getCode(){
    return code;
}


public java.lang.Boolean getIsValid(){
    return isValid;
}


public java.lang.Boolean getIsLeaf(){
    return isLeaf;
}


public com.gbcom.system.domain.SysCodeDetail getParent(){
    return parent;
}


public void setIsValid(java.lang.Boolean isValid){
    this.isValid = isValid;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public java.lang.String getTag(){
    return tag;
}


public void setTreeId(java.lang.String treeId){
    this.treeId = treeId;
}


public java.lang.String getTreeId(){
    return treeId;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysCodeDetail))
        return false;
    else {
        com.gbcom.system.domain.SysCodeDetail sysCodeDetail = (com.gbcom.system.domain.SysCodeDetail) obj;
        if (null == this.getId() || null == sysCodeDetail.getId())
            return false;
        else
            return (this.getId().equals(sysCodeDetail.getId()));
    }
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(name);
    builder.append(isLeaf);
    builder.append(treeId);
    builder.append(isReserved);
    builder.append(tag);
    builder.append(isValid);
    builder.append(description);
    return builder.toString();
}


public void initialize(){
}


public java.lang.Boolean getIsReserved(){
    return isReserved;
}


}