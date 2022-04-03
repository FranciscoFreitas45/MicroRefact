package com.ukefu.webim.web.model;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_dimension")
@org.hibernate.annotations.Proxy(lazy = false)
public class Dimension {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String type;

 private  String modeltype;

 private  String cubeid;

 private  String orgi;

 private  String allmembername;

 private  String postop;

 private  Dimension dim;

 private  String posleft;

 private  int sortindex;

 private  String parameters;

 private  String attribue;

 private  String fkfield;

 private  String fktable;

 private  String fktableid;

 private  List<CubeLevel> cubeLevel;

 private  Date createtime;

 private  String creater;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setDim(Dimension dim){
    this.dim = dim;
}


public void setFktable(String fktable){
    this.fktable = fktable;
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


public void setFkfield(String fkfield){
    this.fkfield = fkfield;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setFktableid(String fktableid){
    this.fktableid = fktableid;
}


public Date getCreatetime(){
    return createtime;
}


public void setModeltype(String modeltype){
    this.modeltype = modeltype;
}


public void setId(String id){
    this.id = id;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "dim")
public Dimension getDim(){
    return dim;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getFktable(){
    return fktable;
}


public String getPosleft(){
    return posleft;
}


public String getCode(){
    return code;
}


public String getAllmembername(){
    return allmembername;
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


public void setAllmembername(String allmembername){
    this.allmembername = allmembername;
}


public void setType(String type){
    this.type = type;
}


public void setPosleft(String posleft){
    this.posleft = posleft;
}


@OneToMany(fetch = FetchType.EAGER)
@JoinColumn(name = "dimid", insertable = false, updatable = false)
@OrderBy("sortindex")
public List<CubeLevel> getCubeLevel(){
    return cubeLevel;
}


public void setCubeLevel(List<CubeLevel> cubeLevel){
    this.cubeLevel = cubeLevel;
}


public String getFkfield(){
    return fkfield;
}


public String getFktableid(){
    return fktableid;
}


public String getType(){
    return type;
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


public void setPostop(String postop){
    this.postop = postop;
}


public int getSortindex(){
    return sortindex;
}


public String getPostop(){
    return postop;
}


}