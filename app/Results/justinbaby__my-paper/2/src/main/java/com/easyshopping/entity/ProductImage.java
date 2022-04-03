package com.easyshopping.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
@Embeddable
public class ProductImage implements Serializable,Comparable<ProductImage>{

 private  long serialVersionUID;

 private  String title;

 private  String source;

 private  String large;

 private  String medium;

 private  String thumbnail;

 private  Integer order;

 private  MultipartFile file;


public void setSource(String source){
    this.source = source;
}


public void setThumbnail(String thumbnail){
    this.thumbnail = thumbnail;
}


@Transient
public boolean isEmpty(){
    return (getFile() == null || getFile().isEmpty()) && (StringUtils.isEmpty(getSource()) || StringUtils.isEmpty(getLarge()) || StringUtils.isEmpty(getMedium()) || StringUtils.isEmpty(getThumbnail()));
}


public void setTitle(String title){
    this.title = title;
}


public int compareTo(ProductImage productImage){
    return new CompareToBuilder().append(getOrder(), productImage.getOrder()).toComparison();
}


public String getThumbnail(){
    return thumbnail;
}


@Transient
public MultipartFile getFile(){
    return file;
}


public void setOrder(Integer order){
    this.order = order;
}


public void setLarge(String large){
    this.large = large;
}


@Length(max = 200)
public String getTitle(){
    return title;
}


public void setFile(MultipartFile file){
    this.file = file;
}


public String getSource(){
    return source;
}


@Min(0)
@Column(name = "orders")
public Integer getOrder(){
    return order;
}


public String getLarge(){
    return large;
}


public void setMedium(String medium){
    this.medium = medium;
}


public String getMedium(){
    return medium;
}


}