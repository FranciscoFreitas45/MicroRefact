package org.jeecgframework.web.system.pojo.base;
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
@Entity
@Table(name = "t_s_attachment")
@Inheritance(strategy = InheritanceType.JOINED)
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


public void setCreatedate(Timestamp createdate){
    this.createdate = createdate;
}


public void setSubclassname(String subclassname){
    this.subclassname = subclassname;
}


public void setSwfpath(String swfpath){
    this.swfpath = swfpath;
}


public void setExtend(String extend){
    this.extend = extend;
}


@Column(name = "note", length = 300)
public String getNote(){
    return this.note;
}


public void setAttachmenttitle(String attachmenttitle){
    this.attachmenttitle = attachmenttitle;
}


@Column(name = "swfpath", length = 300)
public String getSwfpath(){
    return this.swfpath;
}


@Column(name = "extend", length = 32)
public String getExtend(){
    return extend;
}


public void setTSUser(TSUser TSUser){
    this.TSUser = TSUser;
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


public void setAttachmentcontent(byte[] attachmentcontent){
    this.attachmentcontent = attachmentcontent;
}


public void setNote(String note){
    this.note = note;
}


public void setBusinessKey(String businessKey){
    this.businessKey = businessKey;
}


public void setRealpath(String realpath){
    this.realpath = realpath;
}


}