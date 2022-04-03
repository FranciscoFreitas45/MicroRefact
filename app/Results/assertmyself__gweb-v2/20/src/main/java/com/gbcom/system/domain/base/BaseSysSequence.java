package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysSequence implements Serializable{

 public  String REF;

 public  String PROP_LASTID;

 public  String PROP_ID;

 private  int hashCode;

 private  java.lang.String id;

 private  java.lang.Long lastid;

// constructors
public BaseSysSequence() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysSequence(java.lang.String id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysSequence(java.lang.String id, java.lang.Long lastid) {
    this.setId(id);
    this.setLastid(lastid);
    initialize();
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


public void setLastid(java.lang.Long lastid){
    this.lastid = lastid;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysSequence))
        return false;
    else {
        com.gbcom.system.domain.SysSequence sysSequence = (com.gbcom.system.domain.SysSequence) obj;
        if (null == this.getId() || null == sysSequence.getId())
            return false;
        else
            return (this.getId().equals(sysSequence.getId()));
    }
}


public java.lang.Long getLastid(){
    return lastid;
}


public void setId(java.lang.String id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public java.lang.String getId(){
    return id;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(lastid);
    return builder.toString();
}


public void initialize(){
}


}