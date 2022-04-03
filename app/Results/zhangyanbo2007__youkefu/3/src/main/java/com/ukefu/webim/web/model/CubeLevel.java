package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "uk_cubelevel")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeLevel {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String formatstr;

 private  String columname;

 private  boolean uniquemembers;

 private  String type;

 private  String leveltype;

 private  String tablename;

 private  String cubeid;

 private  TableProperties tableproperty;

 private  String orgi;

 private  String dimid;

 private  int sortindex;

 private  boolean permissions;

 private  String parameters;

 private  String attribue;

 private  Date createtime;

 private  String creater;

 private  String description;


public void setName(String name){
    this.name = name;
}


@Transient
public String getNameAlias(){
    return this.columname;
}


public String getName(){
    return name;
}


public boolean isUniquemembers(){
    return uniquemembers;
}


public void setCubeid(String cubeid){
    this.cubeid = cubeid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "tableproperty")
@NotFound(action = NotFoundAction.IGNORE)
public TableProperties getTableproperty(){
    return tableproperty;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getLeveltype(){
    return leveltype;
}


public void setPermissions(boolean permissions){
    this.permissions = permissions;
}


public String getFormatstr(){
    return formatstr;
}


public String getDescription(){
    return description;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public String getTablename(){
    return tablename;
}


public void setTablename(String tablename){
    this.tablename = tablename;
}


public Date getCreatetime(){
    return createtime;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setTableproperty(TableProperties tableproperty){
    this.tableproperty = tableproperty;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
}


public String getCubeid(){
    return cubeid;
}


public void setFormatstr(String formatstr){
    this.formatstr = formatstr;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setCode(String code){
    this.code = code;
}


public void setLeveltype(String leveltype){
    this.leveltype = leveltype;
}


public void setType(String type){
    this.type = type;
}


public String getColumname(){
    return columname;
}


public String getType(){
    return type;
}


public void setColumname(String columname){
    this.columname = columname;
}


public void setUniquemembers(boolean uniquemembers){
    this.uniquemembers = uniquemembers;
}


public String getOrgi(){
    return orgi;
}


public String getParameters(){
    return parameters;
}


public String getAttribue(){
    return attribue;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setAttribue(String attribue){
    this.attribue = attribue;
}


public String getDimid(){
    return dimid;
}


public boolean isPermissions(){
    return permissions;
}


public int getSortindex(){
    return sortindex;
}


public void setDimid(String dimid){
    this.dimid = dimid;
}


}