package com.gbcom.update.server.xml;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("UpdateServer")
public class UpdateServer {

@XStreamAsAttribute
 private  String name;

@XStreamAsAttribute
 private  String product;

@XStreamAsAttribute
 private  String version;

@XStreamAsAttribute
 private  String no;

@XStreamAsAttribute
 private  String date;

@XStreamAsAttribute
 private  String method;

@XStreamAlias("ZipRules")
 private  List<ZipRule> zipRules;

@XStreamAlias("FilterRules")
 private  List<FilterRule> filterRules;


public void setName(String name){
    this.name = name;
}


public String getVersion(){
    return version;
}


public void setProduct(String product){
    this.product = product;
}


public String getProduct(){
    return product;
}


public String getName(){
    return name;
}


public void setFilterRules(List<FilterRule> filterRules){
    this.filterRules = filterRules;
}


public void setVersion(String version){
    this.version = version;
}


public void setNo(String no){
    this.no = no;
}


public String getMethod(){
    return method;
}


public String getNo(){
    return no;
}


public void setMethod(String method){
    this.method = method;
}


public void setZipRules(List<ZipRule> zipRules){
    this.zipRules = zipRules;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public List<ZipRule> getZipRules(){
    return zipRules;
}


public List<FilterRule> getFilterRules(){
    return filterRules;
}


}