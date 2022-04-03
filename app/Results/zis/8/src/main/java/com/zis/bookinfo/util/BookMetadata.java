package com.zis.bookinfo.util;
 import java.text.SimpleDateFormat;
import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
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


public void setName(String name){
    this.name = name;
}


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public void setSource(String source){
    this.source = source;
}


public String getName(){
    return name;
}


public String getAuthor(){
    return author;
}


public void setIsbnCode(String isbnCode){
    this.isbnCode = isbnCode;
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


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
    this.publishDateStr = new SimpleDateFormat("yyyy-MM-dd").format(publishDate);
}


public void setEdition(String edition){
    this.edition = edition;
}


public void setCatalog(String catalog){
    this.catalog = catalog;
}


public Date getPublishDate(){
    return publishDate;
}


public void setPrice(Double price){
    this.price = price;
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


public void setPublishDateStr(String publishDateStr){
    this.publishDateStr = publishDateStr;
}


public String getPublishDateStr(){
    return publishDateStr;
}


public void setSalesPrice(Double salesPrice){
    this.salesPrice = salesPrice;
}


public void setSummary(String summary){
    this.summary = summary;
}


public void setStock(Integer stock){
    this.stock = stock;
}


public String getSummary(){
    return summary;
}


public String getOutId(){
    return outId;
}


public void setSales(Integer sales){
    this.sales = sales;
}


public Double getPrice(){
    return price;
}


public String getIsbnCode(){
    return isbnCode;
}


public void setAuthor(String author){
    this.author = author;
}


public String getPublisher(){
    return publisher;
}


public String getSource(){
    return source;
}


public void setOutId(String outId){
    this.outId = outId;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("BookMetadata [name=");
    builder.append(name);
    builder.append(", publisher=");
    builder.append(publisher);
    builder.append(", publishDate=");
    builder.append(publishDate);
    builder.append(", publishDateStr=");
    builder.append(publishDateStr);
    builder.append(", edition=");
    builder.append(edition);
    builder.append(", author=");
    builder.append(author);
    builder.append(", price=");
    builder.append(price);
    builder.append(", isbnCode=");
    builder.append(isbnCode);
    builder.append(", summary=");
    builder.append(getDigest(summary));
    builder.append(", catalog=");
    builder.append(getDigest(catalog));
    builder.append(", imageUrl=");
    builder.append(imageUrl);
    builder.append(", stock=");
    builder.append(stock);
    builder.append(", salesPrice=");
    builder.append(salesPrice);
    builder.append(", sales=");
    builder.append(sales);
    builder.append(", outId=");
    builder.append(outId);
    builder.append(", source=");
    builder.append(source);
    builder.append("]");
    return builder.toString();
}


public void setPublisher(String publisher){
    this.publisher = publisher;
}


}