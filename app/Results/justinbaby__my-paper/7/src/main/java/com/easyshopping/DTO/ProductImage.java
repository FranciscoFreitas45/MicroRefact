package com.easyshopping.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
public class ProductImage implements Serializable,Comparable<ProductImage>{

 private  long serialVersionUID;

 private  String title;

 private  String source;

 private  String large;

 private  String medium;

 private  String thumbnail;

 private  Integer order;

 private  MultipartFile file;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getThumbnail(){
    return thumbnail;
}


@Transient
public MultipartFile getFile(){
    return file;
}


@Length(max = 200)
public String getTitle(){
    return title;
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


public String getMedium(){
    return medium;
}


@Transient
public boolean isEmpty(){
    return (getFile() == null || getFile().isEmpty()) && (StringUtils.isEmpty(getSource()) || StringUtils.isEmpty(getLarge()) || StringUtils.isEmpty(getMedium()) || StringUtils.isEmpty(getThumbnail()));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}