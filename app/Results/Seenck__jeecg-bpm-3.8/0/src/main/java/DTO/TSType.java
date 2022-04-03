package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSType extends IdEntity{

 private  TSTypegroup TSTypegroup;

 private  TSType TSType;

 private  String typename;

 private  String typecode;

 private  Date createDate;

 private  String createName;

 private  List<TSType> TSTypes;

 private  Integer orderNum;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Column(name = "order_num", length = 3)
public Integer getOrderNum(){
    return orderNum;
}


@Column(name = "create_name", length = 36)
public String getCreateName(){
    return createName;
}


@Column(name = "typecode", length = 50)
public String getTypecode(){
    return this.typecode;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "typegroupid")
public TSTypegroup getTSTypegroup(){
    return this.TSTypegroup;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "typepid")
public TSType getTSType(){
    return this.TSType;
}


@Column(name = "typename", length = 50)
public String getTypename(){
    return this.typename;
}


@Column(name = "create_date")
public Date getCreateDate(){
    return createDate;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSType")
public List<TSType> getTSTypes(){
    return this.TSTypes;
}


public void setTSType(TSType TSType){
    this.TSType = TSType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSType"))

.queryParam("TSType",TSType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTSTypegroup(TSTypegroup TSTypegroup){
    this.TSTypegroup = TSTypegroup;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSTypegroup"))

.queryParam("TSTypegroup",TSTypegroup)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTSTypes(List<TSType> TSTypes){
    this.TSTypes = TSTypes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSTypes"))

.queryParam("TSTypes",TSTypes)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrderNum(Integer orderNum){
    this.orderNum = orderNum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderNum"))

.queryParam("orderNum",orderNum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTypecode(String typecode){
    this.typecode = typecode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTypecode"))

.queryParam("typecode",typecode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTypename(String typename){
    this.typename = typename;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTypename"))

.queryParam("typename",typename)
;
restTemplate.put(builder.toUriString(),null);
}


}