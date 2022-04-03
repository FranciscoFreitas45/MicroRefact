package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
public class TeDepartment {

 private  long serialVersionUID;

 private  long deptId;

 private  Date timestamp;

 private  String deptName;

 private  Date createTime;

 private  String creator;

 private  String deptDescription;

 private  long deptPrincipal;

// Constructors
/**
 * default constructor
 */
public TeDepartment() {
}/**
 * full constructor
 */
public TeDepartment(String deptName, Date createTime, String creator, String deptDescription, long deptPrincipal) {
    this.deptName = deptName;
    this.createTime = createTime;
    this.creator = creator;
    this.deptDescription = deptDescription;
    this.deptPrincipal = deptPrincipal;
}
@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "deptId", unique = true, nullable = false)
public long getDeptId(){
    return this.deptId;
}


@Column(name = "deptDescription", length = 1024)
public String getDeptDescription(){
    return this.deptDescription;
}


@Column(name = "deptName", length = 64)
public String getDeptName(){
    return this.deptName;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Column(name = "deptPrincipal")
public long getDeptPrincipal(){
    return this.deptPrincipal;
}


}