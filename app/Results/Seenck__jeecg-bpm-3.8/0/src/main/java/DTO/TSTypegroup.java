package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSTypegroup extends IdEntity{

 private  String typegroupname;

 private  String typegroupcode;

 private  Date createDate;

 private  String createName;

 private  List<TSType> TSTypes;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Column(name = "typegroupcode", length = 50)
public String getTypegroupcode(){
    return this.typegroupcode;
}


@Column(name = "create_date")
public Date getCreateDate(){
    return createDate;
}


@Column(name = "create_name", length = 36)
public String getCreateName(){
    return createName;
}


@Column(name = "typegroupname", length = 50)
public String getTypegroupname(){
    return this.typegroupname;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSTypegroup")
public List<TSType> getTSTypes(){
    return this.TSTypes;
}


public void setTSTypes(List<TSType> TSTypes){
    this.TSTypes = TSTypes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSTypes"))

.queryParam("TSTypes",TSTypes)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTypegroupcode(String typegroupcode){
    this.typegroupcode = typegroupcode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTypegroupcode"))

.queryParam("typegroupcode",typegroupcode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTypegroupname(String typegroupname){
    this.typegroupname = typegroupname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTypegroupname"))

.queryParam("typegroupname",typegroupname)
;
restTemplate.put(builder.toUriString(),null);
}


}