package com.easyshopping.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Review extends BaseEntity{

 private  long serialVersionUID;

 private  String PATH_PREFIX;

 private  String PATH_SUFFIX;

 private  Integer score;

 private  String content;

 private  Boolean isShow;

 private  String ip;

 private  Member member;

 private  Product product;


public void setProduct(Product product){
    this.product = product;
}


public void setContent(String content){
    this.content = content;
}


@Column(nullable = false)
public Boolean getIsShow(){
    return isShow;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Product getProduct(){
    return product;
}


@Column(nullable = false, updatable = false)
public String getIp(){
    return ip;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false, updatable = false)
public String getContent(){
    return content;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Member getMember(){
    return member;
}


public void setIp(String ip){
    this.ip = ip;
}


public void setMember(Member member){
    this.member = member;
}


@Transient
public String getPath(){
    if (getProduct() != null && getProduct().getId() != null) {
        return PATH_PREFIX + "/" + getProduct().getId() + PATH_SUFFIX;
    }
    return null;
}


@NotNull
@Min(1)
@Max(5)
@Column(nullable = false, updatable = false)
public Integer getScore(){
    return score;
}


public void setIsShow(Boolean isShow){
    this.isShow = isShow;
}


public void setScore(Integer score){
    this.score = score;
}


}