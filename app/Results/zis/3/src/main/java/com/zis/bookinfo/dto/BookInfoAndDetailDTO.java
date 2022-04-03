package com.zis.bookinfo.dto;
 import org.directwebremoting.annotations.DataTransferObject;
import com.zis.bookinfo.bean.Bookinfo;
@DataTransferObject
public class BookInfoAndDetailDTO extends Bookinfo{

 private  long serialVersionUID;

 private  String imageUrl;

 private  String taobaoTitle;

 private  Integer taobaoCatagoryId;

 private  String summary;

 private  String catalog;

 private  String source;

 private  Integer stockBalance;

 private  Boolean taobaoForbidden;


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public void setSummary(String summary){
    this.summary = summary;
}


public void setSource(String source){
    this.source = source;
}


public String getSummary(){
    return summary;
}


public void setTaobaoForbidden(Boolean taobaoForbidden){
    this.taobaoForbidden = taobaoForbidden;
}


public void setCatalog(String catalog){
    this.catalog = catalog;
}


public Boolean getTaobaoForbidden(){
    return taobaoForbidden;
}


public void setTaobaoTitle(String taobaoTitle){
    this.taobaoTitle = taobaoTitle;
}


public String getCatalog(){
    return catalog;
}


public Integer getStockBalance(){
    return stockBalance;
}


public String getTaobaoTitle(){
    return taobaoTitle;
}


public String getSource(){
    return source;
}


public String getImageUrl(){
    return imageUrl;
}


public void setTaobaoCatagoryId(Integer taobaoCatagoryId){
    this.taobaoCatagoryId = taobaoCatagoryId;
}


public Integer getTaobaoCatagoryId(){
    return taobaoCatagoryId;
}


public void setStockBalance(Integer stockBalance){
    this.stockBalance = stockBalance;
}


}