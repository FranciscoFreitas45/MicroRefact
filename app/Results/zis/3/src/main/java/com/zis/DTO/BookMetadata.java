package com.zis.DTO;
 import java.text.SimpleDateFormat;
import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
public class BookMetadata {

 private  String name;

 private  String publisher;

 private  Date publishDate;

 private  String publishDateStr;

 private  String edition;

 private  String author;

 private  Double price;

 private  String isbnCode;

 private  String summary;

 private  String catalog;

 private  String imageUrl;

 private  Integer stock;

 private  Double salesPrice;

 private  Integer sales;

 private  String outId;

 private  String source;


public String getName(){
    return name;
}


public String getAuthor(){
    return author;
}


public Integer getStock(){
    return stock;
}


public Double getSalesPrice(){
    return salesPrice;
}


public Integer getSales(){
    return sales;
}


public String getDigest(String str){
    if (str == null) {
        return "";
    }
    if (str.length() > 10) {
        return str.substring(0, 10) + "...";
    } else {
        return str;
    }
}


public Date getPublishDate(){
    return publishDate;
}


public String getEdition(){
    return edition;
}


public String getCatalog(){
    return catalog;
}


public String getImageUrl(){
    return imageUrl;
}


public String getPublishDateStr(){
    return publishDateStr;
}


public String getSummary(){
    return summary;
}


public String getOutId(){
    return outId;
}


public Double getPrice(){
    return price;
}


public String getIsbnCode(){
    return isbnCode;
}


public String getPublisher(){
    return publisher;
}


public String getSource(){
    return source;
}


}