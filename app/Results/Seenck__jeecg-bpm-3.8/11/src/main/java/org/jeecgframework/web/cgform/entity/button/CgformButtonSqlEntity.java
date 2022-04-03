package org.jeecgframework.web.cgform.entity.button;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "cgform_button_sql", schema = "")
@SuppressWarnings("serial")
public class CgformButtonSqlEntity {

 private  java.lang.String id;

 private  java.lang.String formId;

 private  java.lang.String buttonCode;

 private  java.lang.String cgbSqlName;

 private  byte[] cgbSql;

 private  String cgbSqlStr;

 private  java.lang.String content;


@Column(name = "FORM_ID", nullable = true, length = 32)
public java.lang.String getFormId(){
    return this.formId;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "CGB_SQL_NAME", nullable = true, length = 50)
public java.lang.String getCgbSqlName(){
    return this.cgbSqlName;
}


@Column(name = "CGB_SQL", nullable = true)
public byte[] getCgbSql(){
    return this.cgbSql;
}


@Transient
public String getCgbSqlStr(){
    if (cgbSql != null) {
        cgbSqlStr = new String(cgbSql);
    }
    return cgbSqlStr;
}


public void setCgbSql(byte[] cgbSql){
    this.cgbSql = cgbSql;
}


@Column(name = "CONTENT", nullable = true, length = 1000)
public java.lang.String getContent(){
    return this.content;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


public void setCgbSqlName(java.lang.String cgbSqlName){
    this.cgbSqlName = cgbSqlName;
}


public void setFormId(java.lang.String formId){
    this.formId = formId;
}


@Column(name = "BUTTON_CODE", nullable = true, length = 50)
public java.lang.String getButtonCode(){
    return this.buttonCode;
}


public void setId(java.lang.String id){
    this.id = id;
}


public void setButtonCode(java.lang.String buttonCode){
    this.buttonCode = buttonCode;
}


public void setCgbSqlStr(String cgbSqlStr){
    this.cgbSqlStr = cgbSqlStr;
    if (cgbSqlStr != null) {
        this.cgbSql = cgbSqlStr.getBytes();
    }
}


}