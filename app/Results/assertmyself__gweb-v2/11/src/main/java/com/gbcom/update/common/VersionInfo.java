package com.gbcom.update.common;
 public class VersionInfo {

 private  String name;

 private  String product;

 private  String version;

 private  String no;

 private  String date;

 private  String method;


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


@Override
public String toString(){
    return "VersionInfo [date=" + date + ", method=" + method + ", name=" + name + ", no=" + no + ", product=" + product + ", version=" + version + "]";
}


}