package com.easyshopping.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_ad")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_ad_sequence")
public class Ad extends OrderEntity{

 private  long serialVersionUID;

 private  String title;

 private  Type type;

 private  String content;

 private  String path;

 private  Date beginDate;

 private  Date endDate;

 private  String url;

 private  AdPosition adPosition;


public void setContent(String content){
    this.content = content;
}


@Transient
public boolean hasBegun(){
    return getBeginDate() == null || new Date().after(getBeginDate());
}


@Lob
public String getContent(){
    return content;
}


public void setTitle(String title){
    this.title = title;
}


public void setPath(String path){
    this.path = path;
}


public Date getEndDate(){
    return endDate;
}


public void setType(Type type){
    this.type = type;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public void setUrl(String url){
    this.url = url;
}


public void setAdPosition(AdPosition adPosition){
    this.adPosition = adPosition;
}


@Length(max = 200)
public String getUrl(){
    return url;
}


public Date getBeginDate(){
    return beginDate;
}


public void setBeginDate(Date beginDate){
    this.beginDate = beginDate;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getTitle(){
    return title;
}


@Transient
public boolean hasEnded(){
    return getEndDate() != null && new Date().after(getEndDate());
}


@NotNull
@Column(nullable = false)
public Type getType(){
    return type;
}


@Length(max = 200)
public String getPath(){
    return path;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public AdPosition getAdPosition(){
    return adPosition;
}


}