package com.zis.DTO;
 import com.zis.bookinfo.bean.Bookinfo;
public class CheckOutIdDTO {

 private  Bookinfo book;

 private  boolean isSuccess;

 private  String failMsg;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public boolean getIsSuccess(){
    return isSuccess;
}


public Bookinfo getBook(){
    return book;
}


public String getFailMsg(){
    return failMsg;
}


public void setFailMsg(String failMsg){
    this.failMsg = failMsg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFailMsg"))

.queryParam("failMsg",failMsg)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsSuccess(boolean isSuccess){
    this.isSuccess = isSuccess;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsSuccess"))

.queryParam("isSuccess",isSuccess)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBook(Bookinfo book){
    this.book = book;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBook"))

.queryParam("book",book)
;
restTemplate.put(builder.toUriString(),null);
}


}