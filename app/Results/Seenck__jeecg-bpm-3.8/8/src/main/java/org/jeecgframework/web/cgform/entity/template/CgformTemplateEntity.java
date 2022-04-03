package org.jeecgframework.web.cgform.entity.template;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "cgform_template", schema = "")
@SuppressWarnings("serial")
public class CgformTemplateEntity {

 private  String id;

 private  String createName;

 private  String createBy;

 private  Date createDate;

 private  String updateName;

 private  String updateBy;

 private  Date updateDate;

 private  String sysOrgCode;

 private  String sysCompanyCode;

@Excel(name = "模板名称")
 private  String templateName;

@Excel(name = "模板编码")
 private  String templateCode;

@Excel(name = "模板类型")
 private  String templateType;

@Excel(name = "是否共享")
 private  String templateShare;

@Excel(name = "预览图")
 private  String templatePic;

@Excel(name = "模板描述")
 private  String templateComment;

 private  String templateZipName;

 private  String templateListName;

 private  String templateAddName;

 private  String templateUpdateName;

 private  String templateDetailName;

 private  Integer status;


public void setSysCompanyCode(String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


@Column(name = "TEMPLATE_CODE", nullable = true, length = 50)
public String getTemplateCode(){
    return this.templateCode;
}


@Column(name = "template_detail_name", nullable = true, length = 200)
public String getTemplateDetailName(){
    return templateDetailName;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "template_add_name", nullable = true, length = 200)
public String getTemplateAddName(){
    return templateAddName;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public String getCreateName(){
    return this.createName;
}


public void setUpdateName(String updateName){
    this.updateName = updateName;
}


public void setTemplateListName(String templateListName){
    this.templateListName = templateListName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public String getId(){
    return this.id;
}


@Column(name = "TEMPLATE_TYPE", nullable = true, length = 32)
public String getTemplateType(){
    return this.templateType;
}


public void setTemplateZipName(String templateZipName){
    this.templateZipName = templateZipName;
}


public void setTemplateUpdateName(String templateUpdateName){
    this.templateUpdateName = templateUpdateName;
}


@Column(name = "status")
public Integer getStatus(){
    return status;
}


public void setTemplateCode(String templateCode){
    this.templateCode = templateCode;
}


public void setTemplateShare(String templateShare){
    this.templateShare = templateShare;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "TEMPLATE_NAME", nullable = true, length = 100)
public String getTemplateName(){
    return this.templateName;
}


public void setTemplatePic(String templatePic){
    this.templatePic = templatePic;
}


@Column(name = "TEMPLATE_COMMENT", nullable = true, length = 200)
public String getTemplateComment(){
    return this.templateComment;
}


public void setId(String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public String getUpdateBy(){
    return this.updateBy;
}


public void setTemplateType(String templateType){
    this.templateType = templateType;
}


@Transient
public String getTemplateZipName(){
    return templateZipName;
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
}


public void setTemplateDetailName(String templateDetailName){
    this.templateDetailName = templateDetailName;
}


public void setSysOrgCode(String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setUpdateBy(String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(String createBy){
    this.createBy = createBy;
}


@Column(name = "template_list_name", nullable = true, length = 200)
public String getTemplateListName(){
    return templateListName;
}


@Column(name = "TEMPLATE_PIC", nullable = true, length = 100)
public String getTemplatePic(){
    return this.templatePic;
}


public void setTemplateName(String templateName){
    this.templateName = templateName;
}


public void setTemplateComment(String templateComment){
    this.templateComment = templateComment;
}


public void setStatus(Integer status){
    this.status = status;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public String getCreateBy(){
    return this.createBy;
}


@Column(name = "template_update_name", nullable = true, length = 200)
public String getTemplateUpdateName(){
    return templateUpdateName;
}


public void setCreateDate(Date createDate){
    this.createDate = createDate;
}


@Column(name = "TEMPLATE_SHARE", nullable = true, length = 10)
public String getTemplateShare(){
    return this.templateShare;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public String getSysCompanyCode(){
    return this.sysCompanyCode;
}


public void setTemplateAddName(String templateAddName){
    this.templateAddName = templateAddName;
}


}