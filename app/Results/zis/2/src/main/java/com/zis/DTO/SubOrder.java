package com.zis.DTO;
 import java.util.List;
public class SubOrder {

 private  Integer itemId;

 private  String itemOutNum;

 private  Integer skuId;

 private  String itemName;

 private  Integer itemCount;

 private  Double itemPrice;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public Integer getItemId(){
    return itemId;
}


public String getItemName(){
    return itemName;
}


public String getItemOutNum(){
    return itemOutNum;
}


public Integer getSkuId(){
    return skuId;
}


public Integer getItemCount(){
    return itemCount;
}


public Double getItemPrice(){
    return itemPrice;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItemCount"))

.queryParam("itemCount",itemCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setItemId(Integer itemId){
    this.itemId = itemId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItemId"))

.queryParam("itemId",itemId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setItemName(String itemName){
    this.itemName = itemName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItemName"))

.queryParam("itemName",itemName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItemOutNum"))

.queryParam("itemOutNum",itemOutNum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setItemPrice(Double itemPrice){
    this.itemPrice = itemPrice;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItemPrice"))

.queryParam("itemPrice",itemPrice)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSkuId"))

.queryParam("skuId",skuId)
;
restTemplate.put(builder.toUriString(),null);
}


}