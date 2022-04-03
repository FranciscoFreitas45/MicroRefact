package DTO;
 import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSAttachment extends IdEntity{

 private  long serialVersionUID;

 private  TSUser TSUser;

 private  String businessKey;

 private  String subclassname;

 private  String attachmenttitle;

 private  byte[] attachmentcontent;

 private  String realpath;

 private  Timestamp createdate;

 private  String note;

 private  String swfpath;

 private  String extend;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "userid")
public TSUser getTSUser(){
    return this.TSUser;
}


@Column(name = "subclassname", length = 300)
public String getSubclassname(){
    return subclassname;
}


@Column(name = "createdate", length = 35)
public Timestamp getCreatedate(){
    return createdate;
}


@Column(name = "note", length = 300)
public String getNote(){
    return this.note;
}


@Column(name = "swfpath", length = 300)
public String getSwfpath(){
    return this.swfpath;
}


@Column(name = "extend", length = 32)
public String getExtend(){
    return extend;
}


@Column(name = "businesskey", length = 32)
public String getBusinessKey(){
    return businessKey;
}


@Column(name = "attachmentcontent", length = 3000)
@Lob
public byte[] getAttachmentcontent(){
    return this.attachmentcontent;
}


@Column(name = "attachmenttitle", length = 100)
public String getAttachmenttitle(){
    return this.attachmenttitle;
}


@Column(name = "realpath", length = 100)
public String getRealpath(){
    return this.realpath;
}


public void setAttachmenttitle(String attachmenttitle){
    this.attachmenttitle = attachmenttitle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAttachmenttitle"))

.queryParam("attachmenttitle",attachmenttitle)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatedate(Timestamp createdate){
    this.createdate = createdate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatedate"))

.queryParam("createdate",createdate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExtend(String extend){
    this.extend = extend;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExtend"))

.queryParam("extend",extend)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRealpath(String realpath){
    this.realpath = realpath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRealpath"))

.queryParam("realpath",realpath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSwfpath(String swfpath){
    this.swfpath = swfpath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSwfpath"))

.queryParam("swfpath",swfpath)
;
restTemplate.put(builder.toUriString(),null);
}


}