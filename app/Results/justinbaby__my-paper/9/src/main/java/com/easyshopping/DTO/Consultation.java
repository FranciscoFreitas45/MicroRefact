package com.easyshopping.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Consultation extends BaseEntity{

 private  long serialVersionUID;

 private  String PATH_PREFIX;

 private  String PATH_SUFFIX;

 private  String content;

 private  Boolean isShow;

 private  String ip;

 private  Member member;

 private  Product product;

 private  Consultation forConsultation;

 private  Set<Consultation> replyConsultations;


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


@OneToMany(mappedBy = "forConsultation", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate asc")
public Set<Consultation> getReplyConsultations(){
    return replyConsultations;
}


public void setReplyConsultations(Set<Consultation> replyConsultations){
    this.replyConsultations = replyConsultations;
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


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Consultation getForConsultation(){
    return forConsultation;
}


public void setForConsultation(Consultation forConsultation){
    this.forConsultation = forConsultation;
}


@Transient
public String getPath(){
    if (getProduct() != null && getProduct().getId() != null) {
        return PATH_PREFIX + "/" + getProduct().getId() + PATH_SUFFIX;
    }
    return null;
}


public void setIsShow(Boolean isShow){
    this.isShow = isShow;
}


}