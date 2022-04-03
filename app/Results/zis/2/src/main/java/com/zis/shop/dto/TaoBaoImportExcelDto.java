package com.zis.shop.dto;
 public class TaoBaoImportExcelDto {

 private  String numIid;

 private  String title;

 private  String outerId;


public String getTitle(){
    return title;
}


public void setNumIid(String numIid){
    this.numIid = numIid;
}


public void setTitle(String title){
    this.title = title;
}


public String getOuterId(){
    return outerId;
}


public String getNumIid(){
    return numIid;
}


public void setOuterId(String outerId){
    this.outerId = outerId;
}


}