package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysCode implements Serializable{

 public  String REF;

 public  String PROP_NAME;

 public  String PROP_PARENT;

 public  String PROP_DESCRIPTION;

 public  String PROP_IS_LEAF;

 public  String PROP_ID;

 public  String PROP_IS_RESERVED;

 public  String PROP_TREE_ID;

 public  String PROP_CODE;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.Boolean isReserved;

 private  java.lang.Boolean isLeaf;

 private  java.lang.String treeId;

 private  java.lang.String description;

 private  com.gbcom.system.domain.SysCode parent;

 private  java.util.Set<com.gbcom.system.domain.SysCodeDetail> sysCodeDetails;

 private  java.util.Set<com.gbcom.system.domain.SysCode> sysCodes;

// constructors
public BaseSysCode() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysCode(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
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


public void setSysCodeDetails(java.util.Set<com.gbcom.system.domain.SysCodeDetail> sysCodeDetails){
    this.sysCodeDetails = sysCodeDetails;
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


public void setIsLeaf(java.lang.Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setParent(com.gbcom.system.domain.SysCode parent){
    this.parent = parent;
}


public java.lang.String getCode(){
    return code;
}


public void addTosysCodeDetails(com.gbcom.system.domain.SysCodeDetail sysCodeDetail){
    if (null == getSysCodeDetails())
        setSysCodeDetails(new java.util.LinkedHashSet<com.gbcom.system.domain.SysCodeDetail>());
    getSysCodeDetails().add(sysCodeDetail);
}


public java.lang.Boolean getIsLeaf(){
    return isLeaf;
}


public com.gbcom.system.domain.SysCode getParent(){
    return parent;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public void addTosysCodes(com.gbcom.system.domain.SysCode sysCode){
    if (null == getSysCodes())
        setSysCodes(new java.util.LinkedHashSet<com.gbcom.system.domain.SysCode>());
    getSysCodes().add(sysCode);
}


public void setTreeId(java.lang.String treeId){
    this.treeId = treeId;
}


public java.lang.String getTreeId(){
    return treeId;
}


public java.util.Set<com.gbcom.system.domain.SysCodeDetail> getSysCodeDetails(){
    if (sysCodeDetails == null) {
        sysCodeDetails = new java.util.LinkedHashSet<com.gbcom.system.domain.SysCodeDetail>();
    }
    return sysCodeDetails;
}


public void setSysCodes(java.util.Set<com.gbcom.system.domain.SysCode> sysCodes){
    this.sysCodes = sysCodes;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysCode))
        return false;
    else {
        com.gbcom.system.domain.SysCode sysCode = (com.gbcom.system.domain.SysCode) obj;
        if (null == this.getId() || null == sysCode.getId())
            return false;
        else
            return (this.getId().equals(sysCode.getId()));
    }
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(name);
    builder.append(isReserved);
    builder.append(isLeaf);
    builder.append(treeId);
    builder.append(description);
    return builder.toString();
}


public void initialize(){
}


public java.util.Set<com.gbcom.system.domain.SysCode> getSysCodes(){
    if (sysCodes == null) {
        sysCodes = new java.util.LinkedHashSet<com.gbcom.system.domain.SysCode>();
    }
    return sysCodes;
}


public java.lang.Boolean getIsReserved(){
    return isReserved;
}


}