package org.jeecgframework.web.cgdynamgraph.entity.core;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jform_cgdynamgraph_param", schema = "")
@SuppressWarnings("serial")
public class CgDynamGraphConfigParamEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

@Excel(name = "参数名称")
 private  java.lang.String paramName;

@Excel(name = "参数说明")
 private  java.lang.String paramDesc;

@Excel(name = "数值")
 private  java.lang.String paramValue;

@Excel(name = "排序")
 private  java.lang.Integer seq;

@Excel(name = "动态报表ID")
 private  java.lang.String cgrheadId;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setParamName(java.lang.String paramName){
    this.paramName = paramName;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


public void setParamValue(java.lang.String paramValue){
    this.paramValue = paramValue;
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


@Column(name = "CGRHEAD_ID", nullable = true, length = 36)
public java.lang.String getCgrheadId(){
    return cgrheadId;
}


public void setParamDesc(java.lang.String paramDesc){
    this.paramDesc = paramDesc;
}


@Column(name = "PARAM_DESC", nullable = true, length = 32)
public java.lang.String getParamDesc(){
    return this.paramDesc;
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


public void setSeq(java.lang.Integer seq){
    this.seq = seq;
}


@Column(name = "SEQ", nullable = true, length = 32)
public java.lang.Integer getSeq(){
    return this.seq;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "PARAM_NAME", nullable = false, length = 32)
public java.lang.String getParamName(){
    return this.paramName;
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


@Column(name = "PARAM_VALUE", nullable = true, length = 32)
public java.lang.String getParamValue(){
    return this.paramValue;
}


public void setCgrheadId(java.lang.String cgrheadId){
    this.cgrheadId = cgrheadId;
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


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}