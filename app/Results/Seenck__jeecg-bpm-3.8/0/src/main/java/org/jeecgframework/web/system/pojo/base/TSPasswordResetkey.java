package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_password_resetkey", schema = "")
@SuppressWarnings("serial")
public class TSPasswordResetkey {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

@Excel(name = "用户名", width = 15)
 private  java.lang.String username;

@Excel(name = "邮箱地址", width = 15)
 private  java.lang.String email;

@Excel(name = "是否已重置", width = 15)
 private  java.lang.Integer isReset;


@Column(name = "IS_RESET", nullable = true, length = 2)
public java.lang.Integer getIsReset(){
    return this.isReset;
}


public void setUsername(java.lang.String username){
    this.username = username;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "USERNAME", nullable = true, length = 100)
public java.lang.String getUsername(){
    return this.username;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setEmail(java.lang.String email){
    this.email = email;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "EMAIL", nullable = true, length = 100)
public java.lang.String getEmail(){
    return this.email;
}


public void setIsReset(java.lang.Integer isReset){
    this.isReset = isReset;
}


}