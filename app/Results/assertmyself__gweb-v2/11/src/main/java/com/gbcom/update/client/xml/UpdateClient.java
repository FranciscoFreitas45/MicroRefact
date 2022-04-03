package com.gbcom.update.client.xml;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("UpdateClient")
public class UpdateClient {

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

@XStreamAlias("FileFilters")
 private  List<FileFilter> fileFilters;


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


public void setFileFilters(List<FileFilter> fileFilters){
    this.fileFilters = fileFilters;
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


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public List<FileFilter> getFileFilters(){
    return fileFilters;
}


}