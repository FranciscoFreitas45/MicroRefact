package org.jeecgframework.web.cgform.entity.fillrule;
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
@Table(name = "t_s_fill_rule", schema = "")
@SuppressWarnings("serial")
public class TSFillRuleEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

 private  java.lang.String bpmStatus;

@Excel(name = "规则code", width = 15)
 private  java.lang.String ruleCode;

@Excel(name = "规则名称", width = 15)
 private  java.lang.String ruleName;

@Excel(name = "规则实现类", width = 15)
 private  java.lang.String ruleClass;

@Excel(name = "规则参数", width = 15)
 private  java.lang.String ruleParam;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "RULE_NAME", nullable = true, length = 255)
public java.lang.String getRuleName(){
    return this.ruleName;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setRuleCode(java.lang.String ruleCode){
    this.ruleCode = ruleCode;
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


@Column(name = "RULE_CLASS", nullable = true, length = 500)
public java.lang.String getRuleClass(){
    return this.ruleClass;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
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


public void setRuleClass(java.lang.String ruleClass){
    this.ruleClass = ruleClass;
}


@Column(name = "RULE_PARAM", nullable = true, length = 500)
public java.lang.String getRuleParam(){
    return this.ruleParam;
}


public void setRuleParam(java.lang.String ruleParam){
    this.ruleParam = ruleParam;
}


public void setBpmStatus(java.lang.String bpmStatus){
    this.bpmStatus = bpmStatus;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setSysOrgCode(java.lang.String sysOrgCode){
    this.sysOrgCode = sysOrgCode;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "BPM_STATUS", nullable = true, length = 32)
public java.lang.String getBpmStatus(){
    return this.bpmStatus;
}


@Column(name = "RULE_CODE", nullable = true, length = 255)
public java.lang.String getRuleCode(){
    return this.ruleCode;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setRuleName(java.lang.String ruleName){
    this.ruleName = ruleName;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}