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
@Table(name = "t_s_sms_template", schema = "")
@SuppressWarnings("serial")
public class TSSmsTemplateEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

@Excel(name = "模板类型")
 private  java.lang.String templateType;

@Excel(name = "模板CODE")
 private  java.lang.String templateCode;

@Excel(name = "模板名称")
 private  java.lang.String templateName;

@Excel(name = "模板内容")
 private  java.lang.String templateContent;

 private  java.lang.String templateTestJson;


@Column(name = "TEMPLATE_CONTENT", nullable = true, length = 1000)
public java.lang.String getTemplateContent(){
    return this.templateContent;
}


public void setTemplateContent(java.lang.String templateContent){
    this.templateContent = templateContent;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "TEMPLATE_CODE", nullable = true)
public java.lang.String getTemplateCode(){
    return templateCode;
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


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setTemplateTestJson(java.lang.String templateTestJson){
    this.templateTestJson = templateTestJson;
}


public void setTemplateName(java.lang.String templateName){
    this.templateName = templateName;
}


@Column(name = "TEMPLATE_TYPE", nullable = true, length = 1)
public java.lang.String getTemplateType(){
    return this.templateType;
}


public void setTemplateCode(java.lang.String templateCode){
    this.templateCode = templateCode;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "TEMPLATE_TEST_JSON", nullable = true)
public java.lang.String getTemplateTestJson(){
    return templateTestJson;
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


@Column(name = "TEMPLATE_NAME", nullable = true, length = 50)
public java.lang.String getTemplateName(){
    return this.templateName;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setTemplateType(java.lang.String templateType){
    this.templateType = templateType;
}


}