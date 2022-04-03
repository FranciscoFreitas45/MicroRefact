package org.jeecgframework.web.superquery.page;
 import org.jeecgframework.web.superquery.entity.SuperQueryFieldEntity;
import org.jeecgframework.web.superquery.entity.SuperQueryTableEntity;
import java.util.List;
import java.util.ArrayList;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
public class SuperQueryMainPage {

 private  long serialVersionUID;

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

@Excel(name = "查询规则名称")
 private  java.lang.String queryName;

@Excel(name = "查询规则编码")
 private  java.lang.String queryCode;

@Excel(name = "查询类型")
 private  java.lang.String queryType;

@Excel(name = "说明")
 private  java.lang.String content;

@ExcelCollection(name = "表集合")
 private  List<SuperQueryTableEntity> superQueryTableList;

@ExcelCollection(name = "字段配置")
 private  List<SuperQueryFieldEntity> superQueryFieldList;


public void setSysCompanyCode(java.lang.String sysCompanyCode){
    this.sysCompanyCode = sysCompanyCode;
}


public void setContent(java.lang.String content){
    this.content = content;
}


public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


public java.lang.String getCreateName(){
    return this.createName;
}


public java.lang.String getContent(){
    return this.content;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public java.lang.String getId(){
    return this.id;
}


public java.lang.String getQueryName(){
    return this.queryName;
}


public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setQueryCode(java.lang.String queryCode){
    this.queryCode = queryCode;
}


public java.lang.String getQueryType(){
    return this.queryType;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public List<SuperQueryTableEntity> getSuperQueryTableList(){
    return superQueryTableList;
}


public void setSuperQueryFieldList(List<SuperQueryFieldEntity> superQueryFieldList){
    this.superQueryFieldList = superQueryFieldList;
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


public void setSuperQueryTableList(List<SuperQueryTableEntity> superQueryTableList){
    this.superQueryTableList = superQueryTableList;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public java.lang.String getQueryCode(){
    return this.queryCode;
}


public void setQueryType(java.lang.String queryType){
    this.queryType = queryType;
}


public java.lang.String getUpdateName(){
    return this.updateName;
}


public List<SuperQueryFieldEntity> getSuperQueryFieldList(){
    return superQueryFieldList;
}


public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


public void setQueryName(java.lang.String queryName){
    this.queryName = queryName;
}


}