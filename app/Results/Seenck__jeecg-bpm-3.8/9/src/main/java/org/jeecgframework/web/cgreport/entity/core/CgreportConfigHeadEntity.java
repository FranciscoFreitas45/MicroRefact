package org.jeecgframework.web.cgreport.entity.core;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "jform_cgreport_head", schema = "")
@SuppressWarnings("serial")
public class CgreportConfigHeadEntity {

 private  java.lang.String id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.String cgrSql;

 private  java.lang.String content;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;

 private  String dbSource;

 private  String returnValField;

 private  String returnTxtField;

 private  String popRetype;


public void setName(java.lang.String name){
    this.name = name;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "NAME", nullable = false, length = 100)
public java.lang.String getName(){
    return this.name;
}


@Column(name = "pop_retype", length = 2)
public String getPopRetype(){
    return popRetype;
}


@Column(name = "CONTENT", nullable = false, length = 1000)
public java.lang.String getContent(){
    return this.content;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "db_source", length = 36)
public String getDbSource(){
    return dbSource;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "return_txt_field", length = 100)
public String getReturnTxtField(){
    return returnTxtField;
}


public void setReturnTxtField(String returnTxtField){
    this.returnTxtField = returnTxtField;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setDbSource(String dbSource){
    this.dbSource = dbSource;
}


public void setPopRetype(String popRetype){
    this.popRetype = popRetype;
}


@Column(name = "CODE", nullable = false, length = 36)
public java.lang.String getCode(){
    return this.code;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setCode(java.lang.String code){
    this.code = code;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CGR_SQL", nullable = false, length = 2000)
public java.lang.String getCgrSql(){
    return this.cgrSql;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setReturnValField(String returnValField){
    this.returnValField = returnValField;
}


@Column(name = "return_val_field", length = 100)
public String getReturnValField(){
    return returnValField;
}


public void setCgrSql(java.lang.String cgrSql){
    this.cgrSql = cgrSql;
}


}