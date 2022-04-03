package org.jeecgframework.web.superquery.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "super_query_field", schema = "")
@SuppressWarnings("serial")
public class SuperQueryFieldEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

@Excel(name = "序号", width = 15)
 private  java.lang.String seq;

@Excel(name = "表名", width = 15)
 private  java.lang.String tableName;

@Excel(name = "字段名", width = 15)
 private  java.lang.String name;

@Excel(name = "字段文本", width = 15)
 private  java.lang.String txt;

@Excel(name = "字段类型", width = 15, dicCode = "field_type")
 private  java.lang.String ctype;

@Excel(name = "控件类型", width = 15, dicCode = "s_type")
 private  java.lang.String stype;

@Excel(name = "字典Table", width = 15)
 private  java.lang.String dictTable;

@Excel(name = "字典Code", width = 15)
 private  java.lang.String dictCode;

@Excel(name = "字典Text", width = 15)
 private  java.lang.String dictText;

 private  java.lang.String mainId;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setTableName(java.lang.String tableName){
    this.tableName = tableName;
}


public void setName(java.lang.String name){
    this.name = name;
}


@Column(name = "DICT_TEXT", nullable = true, length = 32)
public java.lang.String getDictText(){
    return this.dictText;
}


@Column(name = "NAME", nullable = true, length = 32)
public java.lang.String getName(){
    return this.name;
}


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "DICT_CODE", nullable = true, length = 32)
public java.lang.String getDictCode(){
    return this.dictCode;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setCtype(java.lang.String ctype){
    this.ctype = ctype;
}


@Column(name = "DICT_TABLE", nullable = true, length = 32)
public java.lang.String getDictTable(){
    return this.dictTable;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setDictTable(java.lang.String dictTable){
    this.dictTable = dictTable;
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


public void setStype(java.lang.String stype){
    this.stype = stype;
}


public void setSeq(java.lang.String seq){
    this.seq = seq;
}


@Column(name = "STYPE", nullable = true, length = 32)
public java.lang.String getStype(){
    return this.stype;
}


@Column(name = "CTYPE", nullable = true, length = 32)
public java.lang.String getCtype(){
    return this.ctype;
}


@Column(name = "SEQ", nullable = true, length = 32)
public java.lang.String getSeq(){
    return this.seq;
}


public void setMainId(java.lang.String mainId){
    this.mainId = mainId;
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


@Column(name = "TABLE_NAME", nullable = true, length = 32)
public java.lang.String getTableName(){
    return this.tableName;
}


public void setTxt(java.lang.String txt){
    this.txt = txt;
}


public void setDictCode(java.lang.String dictCode){
    this.dictCode = dictCode;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "TXT", nullable = true, length = 32)
public java.lang.String getTxt(){
    return this.txt;
}


public void setDictText(java.lang.String dictText){
    this.dictText = dictText;
}


@Column(name = "MAIN_ID", nullable = true, length = 32)
public java.lang.String getMainId(){
    return this.mainId;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


}