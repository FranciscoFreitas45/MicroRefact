package com.ukefu.webim.web.model;
 import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_drilldown")
@org.hibernate.annotations.Proxy(lazy = false)
public class DrillDown {

 private  long serialVersionUID;

 private  String id;

 private  boolean enable;

 private  String modelid;

 private  String drillpos;

 private  String dataid;

 private  String dataname;

 private  String paramtype;

 private  String drilltype;

 private  String tdstyle;

 private  String paramtarget;

 private  String paramreport;

 private  String paramurl;

 private  String paramname;

 private  String targetmime;

 private  String orgi;

 private  String reportid;

 private  String reportdicid;

 private  String name;

 private  String code;

 private  String memo;

 private  String chartid;

 private  String paramvalue;

 private  String gotomaxvalue;

 private  Map<String,String> paramvalues;


public void setName(String name){
    this.name = name;
}


public void setParamtype(String paramtype){
    this.paramtype = paramtype;
}


public void setParamreport(String paramreport){
    this.paramreport = paramreport;
}


public String getName(){
    return name;
}


public String getTdstyle(){
    return tdstyle;
}


public String getParamvalue(){
    return paramvalue;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Transient
public Map<String,String> getParamvalues(){
    return paramvalues;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getReportdicid(){
    return reportdicid;
}


public String getParamname(){
    return paramname;
}


public void setReportid(String reportid){
    this.reportid = reportid;
}


public String getDrilltype(){
    return drilltype;
}


public boolean isEnable(){
    return enable;
}


public void setGotomaxvalue(String gotomaxvalue){
    this.gotomaxvalue = gotomaxvalue;
}


public String getParamurl(){
    return paramurl;
}


public void setParamname(String paramname){
    this.paramname = paramname;
}


public void setId(String id){
    this.id = id;
}


public String getChartid(){
    return chartid;
}


public String getParamreport(){
    return paramreport;
}


public String getCode(){
    return code;
}


public String getModelid(){
    return modelid;
}


public String getParamtarget(){
    return paramtarget;
}


public void setParamurl(String paramurl){
    this.paramurl = paramurl;
}


public void setModelid(String modelid){
    this.modelid = modelid;
}


public String getParamtype(){
    return paramtype;
}


public String getReportid(){
    return reportid;
}


public String getDataname(){
    return dataname;
}


public void setEnable(boolean enable){
    this.enable = enable;
}


public void setParamvalue(String paramvalue){
    this.paramvalue = paramvalue;
}


public void setCode(String code){
    this.code = code;
}


public String getDrillpos(){
    return drillpos;
}


public void setReportdicid(String reportdicid){
    this.reportdicid = reportdicid;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Transient
public String getGotomaxvalue(){
    return gotomaxvalue;
}


public void setParamtarget(String paramtarget){
    this.paramtarget = paramtarget;
}


public String getMemo(){
    return memo;
}


public String getDataid(){
    return dataid;
}


public void setDataname(String dataname){
    this.dataname = dataname;
}


public String getTargetmime(){
    return targetmime;
}


public void setTargetmime(String targetmime){
    this.targetmime = targetmime;
}


public void setDrilltype(String drilltype){
    this.drilltype = drilltype;
}


public String getOrgi(){
    return orgi;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setDrillpos(String drillpos){
    this.drillpos = drillpos;
}


public void setParamvalues(Map<String,String> paramvalues){
    this.paramvalues = paramvalues;
}


public void setChartid(String chartid){
    this.chartid = chartid;
}


public void setTdstyle(String tdstyle){
    this.tdstyle = tdstyle;
}


}