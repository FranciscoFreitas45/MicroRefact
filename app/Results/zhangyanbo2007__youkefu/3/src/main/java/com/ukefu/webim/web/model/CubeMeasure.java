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
@Entity
@Table(name = "uk_cubemeasure")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeMeasure {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String modeltype;

 private  CubeMeasure measure;

 private  String columname;

 private  boolean uniquemembers;

 private  String type;

 private  String aggregator;

 private  boolean calculatedmember;

 private  String formatstring;

 private  String tablename;

 private  String cubeid;

 private  String orgi;

 private  String mid;

 private  int sortindex;

 private  String parameters;

 private  String attribue;

 private  Date createtime;

 private  String description;

 private  String creater;


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


public void setMid(String mid){
    this.mid = mid;
}


public void setCubeid(String cubeid){
    this.cubeid = cubeid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getModeltype(){
    return modeltype;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getFormatstring(){
    return formatstring;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getAggregator(){
    return aggregator;
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


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "measure")
public CubeMeasure getMeasure(){
    return measure;
}


public Date getCreatetime(){
    return createtime;
}


public void setModeltype(String modeltype){
    this.modeltype = modeltype;
}


public void setAggregator(String aggregator){
    this.aggregator = aggregator;
}


public void setId(String id){
    this.id = id;
}


public boolean isCalculatedmember(){
    return calculatedmember;
}


public String getMid(){
    return mid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
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


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setCode(String code){
    this.code = code;
}


public void setType(String type){
    this.type = type;
}


public String getColumname(){
    return columname;
}


public void setMeasure(CubeMeasure measure){
    this.measure = measure;
}


public void setCalculatedmember(boolean calculatedmember){
    this.calculatedmember = calculatedmember;
}


public void setFormatstring(String formatstring){
    this.formatstring = formatstring;
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


public int getSortindex(){
    return sortindex;
}


}