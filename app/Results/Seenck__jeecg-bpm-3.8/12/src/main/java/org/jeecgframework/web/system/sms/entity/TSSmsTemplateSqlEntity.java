package org.jeecgframework.web.system.sms.entity;
 import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_sms_template_sql", schema = "")
@SuppressWarnings("serial")
public class TSSmsTemplateSqlEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

@Excel(name = "配置CODE")
 private  java.lang.String code;

@Excel(name = "配置名称")
 private  java.lang.String name;

@Excel(name = "业务SQLID")
 private  java.lang.String sqlId;

@Excel(name = "消息模本ID")
 private  java.lang.String templateId;


public void setName(java.lang.String name){
    this.name = name;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "NAME", nullable = true, length = 32)
public java.lang.String getName(){
    return this.name;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public void setTemplateId(java.lang.String templateId){
    this.templateId = templateId;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "TEMPLATE_ID", nullable = true, length = 32)
public java.lang.String getTemplateId(){
    return this.templateId;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setSqlId(java.lang.String sqlId){
    this.sqlId = sqlId;
}


@Column(name = "SQL_ID", nullable = true, length = 32)
public java.lang.String getSqlId(){
    return this.sqlId;
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


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "CODE", nullable = true, length = 32)
public java.lang.String getCode(){
    return this.code;
}


}