package com.ukefu.webim.web.model;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_cube")
@org.hibernate.annotations.Proxy(lazy = false)
public class Cube {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String modeltype;

 private  String dstype;

 private  String db;

 private  Date createtime;

 private  String mposleft;

 private  String mpostop;

 private  String typeid;

 private  String orgi;

 private  String createdata;

 private  String dataid;

 private  String dataflag;

 private  int startindex;

 private  Date startdate;

 private  String creater;

 private  Date updatetime;

 private  String cubefile;

 private  String sql;

 private  List<CubeMetadata> metadata;

 private  List<Dimension> dimension;

 private  List<CubeMeasure> measure;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setDb(String db){
    this.db = db;
}


public String getCubefile(){
    return cubefile;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getModeltype(){
    return modeltype;
}


public String getDataflag(){
    return dataflag;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setTypeid(String typeid){
    this.typeid = typeid;
}


public void setMposleft(String mposleft){
    this.mposleft = mposleft;
}


public void setSql(String sql){
    this.sql = sql;
}


public Date getStartdate(){
    return startdate;
}


@Transient
public String getSql(){
    return sql;
}


@Transient
public List<CubeMeasure> getMeasure(){
    return measure;
}


public void setStartdate(Date startdate){
    this.startdate = startdate;
}


public Date getCreatetime(){
    return createtime;
}


public void setModeltype(String modeltype){
    this.modeltype = modeltype;
}


@Transient
public List<Dimension> getDimension(){
    return dimension;
}


public void setDimension(List<Dimension> dimension){
    this.dimension = dimension;
}


public void setStartindex(int startindex){
    this.startindex = startindex;
}


public void setId(String id){
    this.id = id;
}


public void setMpostop(String mpostop){
    this.mpostop = mpostop;
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


public void setCreatedata(String createdata){
    this.createdata = createdata;
}


public int getStartindex(){
    return startindex;
}


public String getMposleft(){
    return mposleft;
}


@Transient
public List<CubeMetadata> getMetadata(){
    return metadata;
}


public void setDataflag(String dataflag){
    this.dataflag = dataflag;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setCubefile(String cubefile){
    this.cubefile = cubefile;
}


public String getTypeid(){
    return typeid;
}


public String getDb(){
    return db;
}


public void setMeasure(List<CubeMeasure> measure){
    this.measure = measure;
}


public void setMetadata(List<CubeMetadata> metadata){
    this.metadata = metadata;
}


public String getDataid(){
    return dataid;
}


@Transient
public String getTable(){
    return "c_d_" + UKTools.md5(this.getId());
}


public void setDstype(String dstype){
    this.dstype = dstype;
}


public String getOrgi(){
    return orgi;
}


public String getCreatedata(){
    return createdata;
}


public String getDstype(){
    return dstype;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getMpostop(){
    return mpostop;
}


}