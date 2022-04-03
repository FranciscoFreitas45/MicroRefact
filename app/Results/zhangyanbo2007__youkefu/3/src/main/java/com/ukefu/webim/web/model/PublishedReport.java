package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_publishedreport")
@Entity
@Table(name = "uk_publishedreport")
@org.hibernate.annotations.Proxy(lazy = false)
public class PublishedReport {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String reporttype;

 private  String dicid;

 private  String orgi;

 private  String dataid;

 private  String dataflag;

 private  int startindex;

 private  Date startdate;

 private  int dataversion;

 private  String creater;

 private  String reportcontent;

 private  Date createtime;

 private  Report report;


public void setName(String name){
    this.name = name;
}


public void setDataversion(int dataversion){
    this.dataversion = dataversion;
}


public String getName(){
    return name;
}


public void setReportcontent(String reportcontent){
    this.reportcontent = reportcontent;
}


public String getReporttype(){
    return reporttype;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getDataflag(){
    return dataflag;
}


@Transient
public Report getReport(){
    Base64 base64 = new Base64();
    try {
        return report != null ? report : (report = (this.reportcontent == null ? null : (Report) UKTools.toObject(base64.decode(this.reportcontent))));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return report;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public Date getStartdate(){
    return startdate;
}


public void setStartdate(Date startdate){
    this.startdate = startdate;
}


public Date getCreatetime(){
    return createtime;
}


public void setStartindex(int startindex){
    this.startindex = startindex;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCode(){
    return code;
}


public int getStartindex(){
    return startindex;
}


public String getReportcontent(){
    return reportcontent;
}


public void setReporttype(String reporttype){
    this.reporttype = reporttype;
}


public void setDataflag(String dataflag){
    this.dataflag = dataflag;
}


public void setCode(String code){
    this.code = code;
}


public int getDataversion(){
    return dataversion;
}


public String getDicid(){
    return dicid;
}


public String getDataid(){
    return dataid;
}


public String getOrgi(){
    return orgi;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setCreater(String creater){
    this.creater = creater;
}


}