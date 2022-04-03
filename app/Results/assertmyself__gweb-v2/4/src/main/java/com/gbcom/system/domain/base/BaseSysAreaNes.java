package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysAreaNes implements Serializable{

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String neName;

 private  java.lang.String neID;

 private  java.lang.String fkCol;

 private  java.lang.String fkValue;

 private  java.lang.String fkTable;

 private  com.gbcom.system.domain.SysArea area;

// constructors
public BaseSysAreaNes() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysAreaNes(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setFkCol(java.lang.String fkCol){
    this.fkCol = fkCol;
}


public void setArea(com.gbcom.system.domain.SysArea area){
    this.area = area;
}


public java.lang.String getFkCol(){
    return fkCol;
}


public java.lang.Long getId(){
    return id;
}


public java.lang.String getNeID(){
    return neID;
}


public void setFkValue(java.lang.String fkValue){
    this.fkValue = fkValue;
}


public void setNeName(java.lang.String neName){
    this.neName = neName;
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


public java.lang.String getNeName(){
    return neName;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setNeID(java.lang.String neID){
    this.neID = neID;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    // builder.append(parent.getId());
    builder.append(neName);
    builder.append(neID);
    builder.append(fkCol);
    builder.append(fkValue);
    return builder.toString();
}


public void setFkTable(java.lang.String fkTable){
    this.fkTable = fkTable;
}


public void initialize(){
}


public com.gbcom.system.domain.SysArea getArea(){
    return area;
}


public java.lang.String getFkValue(){
    return fkValue;
}


public java.lang.String getFkTable(){
    return fkTable;
}


}