package com.gbcom.system.domain.base;
 import com.gbcom.system.domain.SysUserPrivilege;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.io.Serializable;
import com.gbcom.Interface.SysCodeDetail;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class BaseSysPrivilege implements Serializable{

 public  String REF;

 public  String PROP_NAME;

 public  String PROP_PARENT;

 public  String PROP_DESCRIPTION;

 public  String PROP_URL;

 public  String PROP_IS_LEAF;

 public  String PROP_TYPE;

 public  String PROP_TAG;

 public  String PROP_ID;

 public  String PROP_TREE_ID;

 public  String PROP_CODE;

 public  String PROP_DEFINITION;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.Long tag;

 private  java.lang.String url;

 private  java.lang.String definition;

 private  java.lang.String description;

 private  java.lang.Boolean isLeaf;

 private  java.lang.String treeId;

 private  com.gbcom.system.domain.SysPrivilege parent;

 private  com.gbcom.system.domain.SysCodeDetail type;

 private  java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges;

 private  java.util.Set<com.gbcom.system.domain.SysPrivilege> sysPrivileges;

 private  java.util.Set<SysUserPrivilege> sysUserPrivileges;

// constructors
public BaseSysPrivilege() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysPrivilege(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysPrivilege(java.lang.Long id, java.lang.String code) {
    this.setId(id);
    this.setCode(code);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.String getDefinition(){
    return definition;
}


public java.lang.String getName(){
    return name;
}


public void setSysUserPrivileges(java.util.Set<SysUserPrivilege> sysUserPrivileges){
    this.sysUserPrivileges = sysUserPrivileges;
}


public java.lang.Long getId(){
    return id;
}


public void setDescription(java.lang.String description){
    this.description = description;
}


public java.lang.String getDescription(){
    return description;
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


public void addTosysPrivileges(com.gbcom.system.domain.SysPrivilege sysPrivilege){
    if (null == getSysPrivileges())
        setSysPrivileges(new java.util.LinkedHashSet<com.gbcom.system.domain.SysPrivilege>());
    getSysPrivileges().add(sysPrivilege);
}


public void setTag(java.lang.Long tag){
    this.tag = tag;
}


public void setIsLeaf(java.lang.Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setParent(com.gbcom.system.domain.SysPrivilege parent){
    this.parent = parent;
}


public java.lang.String getCode(){
    return code;
}


public java.lang.Boolean getIsLeaf(){
    return isLeaf;
}


public com.gbcom.system.domain.SysPrivilege getParent(){
    return parent;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public java.lang.Long getTag(){
    return tag;
}


public void setType(com.gbcom.system.domain.SysCodeDetail type){
    this.type = type;
}


public java.util.Set<com.gbcom.system.domain.SysRolePrivilege> getSysRolePrivileges(){
    if (sysRolePrivileges == null) {
        sysRolePrivileges = new java.util.LinkedHashSet<com.gbcom.system.domain.SysRolePrivilege>();
    }
    return sysRolePrivileges;
}


public java.util.Set<SysUserPrivilege> getSysUserPrivileges(){
    if (sysUserPrivileges == null) {
        sysUserPrivileges = new java.util.LinkedHashSet<SysUserPrivilege>();
    }
    return sysUserPrivileges;
}


public void setUrl(java.lang.String url){
    this.url = url;
}


public void setTreeId(java.lang.String treeId){
    this.treeId = treeId;
}


public java.lang.String getTreeId(){
    return treeId;
}


public java.lang.String getUrl(){
    return url;
}


public void addTosysRolePrivileges(com.gbcom.system.domain.SysRolePrivilege sysRolePrivilege){
    if (null == getSysRolePrivileges())
        setSysRolePrivileges(new java.util.LinkedHashSet<com.gbcom.system.domain.SysRolePrivilege>());
    getSysRolePrivileges().add(sysRolePrivilege);
}


public com.gbcom.system.domain.SysCodeDetail getType(){
    return type;
}


public void setSysPrivileges(java.util.Set<com.gbcom.system.domain.SysPrivilege> sysPrivileges){
    this.sysPrivileges = sysPrivileges;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysPrivilege))
        return false;
    else {
        com.gbcom.system.domain.SysPrivilege sysPrivilege = (com.gbcom.system.domain.SysPrivilege) obj;
        if (null == this.getId() || null == sysPrivilege.getId())
            return false;
        else
            return (this.getId().equals(sysPrivilege.getId()));
    }
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(name);
    builder.append(tag);
    builder.append(url);
    builder.append(definition);
    builder.append(description);
    builder.append(isLeaf);
    builder.append(treeId);
    return builder.toString();
}


public void initialize(){
}


public void setDefinition(java.lang.String definition){
    this.definition = definition;
}


public void setSysRolePrivileges(java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges){
    this.sysRolePrivileges = sysRolePrivileges;
}


public java.util.Set<com.gbcom.system.domain.SysPrivilege> getSysPrivileges(){
    if (sysPrivileges == null) {
        sysPrivileges = new java.util.LinkedHashSet<com.gbcom.system.domain.SysPrivilege>();
    }
    return sysPrivileges;
}


public void addTosysUserPrivileges(SysUserPrivilege sysUserPrivilege){
    if (null == getSysUserPrivileges())
        setSysUserPrivileges(new java.util.LinkedHashSet<SysUserPrivilege>());
    getSysUserPrivileges().add(sysUserPrivilege);
}


}