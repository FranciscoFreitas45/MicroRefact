package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysDept implements Serializable{

 public  String REF;

 public  String PROP_PARENT;

 public  String PROP_TELEPHONE;

 public  String PROP_UPDATE_TIME;

 public  String PROP_ORGANIZATION_CODE;

 public  String PROP_CODE;

 public  String PROP_CONTACTER;

 public  String PROP_NAME;

 public  String PROP_CREATE_USER;

 public  String PROP_IS_LEAF;

 public  String PROP_ORDER_NO;

 public  String PROP_IS_VALID;

 public  String PROP_CREATE_TIME;

 public  String PROP_SHORT_NAME;

 public  String PROP_ADDRESS;

 public  String PROP_MEMO;

 public  String PROP_ID;

 public  String PROP_IS_COMAPNY;

 public  String PROP_TREE_ID;

 public  String PROP_UPDATE_USER;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.String shortName;

 private  java.lang.String organizationCode;

 private  java.lang.String address;

 private  java.lang.String contacter;

 private  java.lang.String telephone;

 private  java.lang.Boolean isCompany;

 private  java.lang.Boolean isValid;

 private  java.lang.Boolean isLeaf;

 private  java.lang.String treeId;

 private  java.lang.Long orderNo;

 private  java.lang.String memo;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String createUser;

 private  java.lang.String updateUser;

 private  com.gbcom.system.domain.SysDept parent;

 private  java.util.Set<com.gbcom.system.domain.SysPersonDept> sysPersonDepts;

// constructors
public BaseSysDept() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysDept(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.String getCreateUser(){
    return createUser;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public java.lang.String getName(){
    return name;
}


public void setOrganizationCode(java.lang.String organizationCode){
    this.organizationCode = organizationCode;
}


public java.lang.Long getId(){
    return id;
}


public java.lang.String getTelephone(){
    return telephone;
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


public java.lang.String getAddress(){
    return address;
}


public void setIsLeaf(java.lang.Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setParent(com.gbcom.system.domain.SysDept parent){
    this.parent = parent;
}


public java.lang.String getCode(){
    return code;
}


public void setSysPersonDepts(java.util.Set<com.gbcom.system.domain.SysPersonDept> sysPersonDepts){
    this.sysPersonDepts = sysPersonDepts;
}


public java.lang.Boolean getIsValid(){
    return isValid;
}


public java.util.Set<com.gbcom.system.domain.SysPersonDept> getSysPersonDepts(){
    if (sysPersonDepts == null) {
        sysPersonDepts = new java.util.LinkedHashSet<com.gbcom.system.domain.SysPersonDept>();
    }
    return sysPersonDepts;
}


public java.lang.Boolean getIsLeaf(){
    return isLeaf;
}


public com.gbcom.system.domain.SysDept getParent(){
    return parent;
}


public void setIsValid(java.lang.Boolean isValid){
    this.isValid = isValid;
}


public java.lang.Long getOrderNo(){
    return orderNo;
}


public void addTosysPersonDepts(com.gbcom.system.domain.SysPersonDept sysPersonDept){
    if (null == getSysPersonDepts())
        setSysPersonDepts(new java.util.LinkedHashSet<com.gbcom.system.domain.SysPersonDept>());
    getSysPersonDepts().add(sysPersonDept);
}


public void setAddress(java.lang.String address){
    this.address = address;
}


public void setOrderNo(java.lang.Long orderNo){
    this.orderNo = orderNo;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public void setIsCompany(java.lang.Boolean isCompany){
    this.isCompany = isCompany;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public void setCreateUser(java.lang.String createUser){
    this.createUser = createUser;
}


public void setTelephone(java.lang.String telephone){
    this.telephone = telephone;
}


public void setTreeId(java.lang.String treeId){
    this.treeId = treeId;
}


public java.lang.String getTreeId(){
    return treeId;
}


public void setMemo(java.lang.String memo){
    this.memo = memo;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public java.lang.String getMemo(){
    return memo;
}


public void setContacter(java.lang.String contacter){
    this.contacter = contacter;
}


public void setUpdateUser(java.lang.String updateUser){
    this.updateUser = updateUser;
}


public java.lang.String getUpdateUser(){
    return updateUser;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysDept))
        return false;
    else {
        com.gbcom.system.domain.SysDept sysDept = (com.gbcom.system.domain.SysDept) obj;
        if (null == this.getId() || null == sysDept.getId())
            return false;
        else
            return (this.getId().equals(sysDept.getId()));
    }
}


public java.lang.String getShortName(){
    return shortName;
}


public void setShortName(java.lang.String shortName){
    this.shortName = shortName;
}


public java.lang.Boolean getIsCompany(){
    return isCompany;
}


public void setUpdateTime(java.sql.Timestamp updateTime){
    this.updateTime = updateTime;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(name);
    builder.append(shortName);
    builder.append(organizationCode);
    builder.append(address);
    builder.append(contacter);
    builder.append(telephone);
    builder.append(isCompany);
    builder.append(isValid);
    builder.append(isLeaf);
    builder.append(treeId);
    builder.append(orderNo);
    builder.append(memo);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(createUser);
    builder.append(updateUser);
    return builder.toString();
}


public void initialize(){
}


public java.lang.String getOrganizationCode(){
    return organizationCode;
}


public java.lang.String getContacter(){
    return contacter;
}


}