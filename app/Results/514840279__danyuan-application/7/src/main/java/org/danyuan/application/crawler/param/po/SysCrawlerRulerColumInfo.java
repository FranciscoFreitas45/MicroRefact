package org.danyuan.application.crawler.param.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_crawler_ruler_colum_info")
@NamedQuery(name = "SysCrawlerRulerColumInfo.findAll", query = "SELECT s FROM SysCrawlerRulerColumInfo s")
public class SysCrawlerRulerColumInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "colum_name")
 private  String columName;

@Column(name = "ruler_uuid")
 private  String rulerUuid;

@Column(name = "param")
 private  String param;

@Column(name = "end", precision = 10)
 private  Integer end;

@Column(name = "spl2", precision = 10)
 private  Integer spl2;

@Column(name = "spl1")
 private  String spl1;

@Column(name = "start", precision = 10)
 private  Integer start;

@Column(name = "ruler")
 private  String ruler;

@Column(name = "param_new")
 private  String paramNew;

@Column(name = "arr", precision = 10)
 private  Integer arr;

@Column(name = "parent_uuid")
 private  String parentUuid;

@Column(name = "app1")
 private  String app1;

@Column(name = "type")
 private  String type;

@Column(name = "md5flag")
 private  String md5flag;

@Column(name = "app2")
 private  String app2;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysCrawlerRulerColumInfo() {
    super();
}
public void setRulerUuid(String rulerUuid){
    this.rulerUuid = rulerUuid;
}


public Integer getStart(){
    return start;
}


public void setColumName(String columName){
    this.columName = columName;
}


public void setParam(String param){
    this.param = param;
}


public String getParentUuid(){
    return parentUuid;
}


public void setStart(Integer start){
    this.start = start;
}


public void setParentUuid(String parentUuid){
    this.parentUuid = parentUuid;
}


public String getRuler(){
    return ruler;
}


public String getApp2(){
    return app2;
}


public void setEnd(Integer end){
    this.end = end;
}


public String getApp1(){
    return app1;
}


public Integer getSpl2(){
    return spl2;
}


public String getSpl1(){
    return spl1;
}


public void setRuler(String ruler){
    this.ruler = ruler;
}


public void setArr(Integer arr){
    this.arr = arr;
}


public String getRulerUuid(){
    return rulerUuid;
}


public String getParam(){
    return param;
}


public Integer getArr(){
    return arr;
}


public String getParamNew(){
    return paramNew;
}


public String getMd5flag(){
    return md5flag;
}


public void setType(String type){
    this.type = type;
}


public Integer getEnd(){
    return end;
}


public void setMd5flag(String md5flag){
    this.md5flag = md5flag;
}


public void setParamNew(String paramNew){
    this.paramNew = paramNew;
}


public void setSpl1(String spl1){
    this.spl1 = spl1;
}


public void setApp2(String app2){
    this.app2 = app2;
}


public void setSpl2(Integer spl2){
    this.spl2 = spl2;
}


public void setApp1(String app1){
    this.app1 = app1;
}


public String getType(){
    return type;
}


public String getColumName(){
    return columName;
}


}