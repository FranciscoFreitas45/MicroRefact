package com.zis.purchase.dto;
 public class TempImportDTO {

 private  String isbn;

 private  String data;

 private  String additionalInfo;


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public void setData(String data){
    this.data = data;
}


public String getIsbn(){
    return isbn;
}


public void setAdditionalInfo(String additionalInfo){
    this.additionalInfo = additionalInfo;
}


public String getData(){
    return data;
}


public String getAdditionalInfo(){
    return additionalInfo;
}


}