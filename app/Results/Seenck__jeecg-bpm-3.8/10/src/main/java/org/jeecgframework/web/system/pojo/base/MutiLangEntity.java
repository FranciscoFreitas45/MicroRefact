package org.jeecgframework.web.system.pojo.base;
 import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_s_muti_lang", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MutiLangEntity {

 private  java.lang.String id;

 private  java.lang.String langKey;

 private  java.lang.String langContext;

 private  java.lang.String langCode;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setLangContext(java.lang.String langContext){
    this.langContext = langContext;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "LANG_CODE", nullable = false, length = 50)
public java.lang.String getLangCode(){
    return this.langCode;
}


@Column(name = "CREATE_NAME", nullable = false, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "LANG_KEY", nullable = false, length = 50)
public java.lang.String getLangKey(){
    return this.langKey;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


public void setLangKey(java.lang.String langKey){
    this.langKey = langKey;
}


@Column(name = "UPDATE_NAME", nullable = false, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = false, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setLangCode(java.lang.String langCode){
    this.langCode = langCode;
}


@Column(name = "CREATE_DATE", nullable = false)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = false)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = false, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "LANG_CONTEXT", nullable = false, length = 500)
public java.lang.String getLangContext(){
    return this.langContext;
}


}