package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
public class TSDepart extends IdEntity{

 private  TSDepart TSPDepart;

 private  String departname;

 private  String description;

 private  String orgCode;

 private  String orgType;

 private  String mobile;

 private  String fax;

 private  String address;

 private  String departOrder;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String sysOrgCode;

 private  java.lang.String sysCompanyCode;

 private  List<TSDepart> TSDeparts;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
public java.lang.String getSysOrgCode(){
    return this.sysOrgCode;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "description", length = 500)
public String getDescription(){
    return this.description;
}


@Column(name = "org_code", length = 64)
public String getOrgCode(){
    return orgCode;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
@JsonDeserialize(using = CustomJsonDateDeserializer.class)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "address", length = 100)
public String getAddress(){
    return address;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "parentdepartid")
public TSDepart getTSPDepart(){
    return this.TSPDepart;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSPDepart")
public List<TSDepart> getTSDeparts(){
    return TSDeparts;
}


@Column(name = "depart_order")
public String getDepartOrder(){
    return departOrder;
}


@Column(name = "fax", length = 32)
public String getFax(){
    return fax;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "org_type", length = 1)
public String getOrgType(){
    return orgType;
}


@Column(name = "mobile", length = 32)
public String getMobile(){
    return mobile;
}


@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
public java.lang.String getSysCompanyCode(){
    return this.sysCompanyCode;
}


@Column(name = "departname", nullable = false, length = 100)
public String getDepartname(){
    return this.departname;
}


public void setTSPDepart(TSDepart TSPDepart){
    this.TSPDepart = TSPDepart;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSPDepart"))

.queryParam("TSPDepart",TSPDepart)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgCode(String orgCode){
    this.orgCode = orgCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgCode"))

.queryParam("orgCode",orgCode)
;
restTemplate.put(builder.toUriString(),null);
}


}