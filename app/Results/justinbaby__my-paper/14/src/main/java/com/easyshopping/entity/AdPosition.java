package com.easyshopping.entity;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_ad_position")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_ad_position_sequence")
public class AdPosition extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  Integer width;

 private  Integer height;

 private  String description;

 private  String template;

 private  Set<Ad> ads;


public void setName(String name){
    this.name = name;
}


public void setTemplate(String template){
    this.template = template;
}


@NotNull
@Min(1)
@Column(nullable = false)
public Integer getHeight(){
    return height;
}


@OneToMany(mappedBy = "adPosition", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
@OrderBy("order asc")
public Set<Ad> getAds(){
    return ads;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@NotEmpty
@Lob
@Column(nullable = false)
public String getTemplate(){
    return template;
}


@NotNull
@Min(1)
@Column(nullable = false)
public Integer getWidth(){
    return width;
}


public void setDescription(String description){
    this.description = description;
}


public void setAds(Set<Ad> ads){
    this.ads = ads;
}


public void setWidth(Integer width){
    this.width = width;
}


public void setHeight(Integer height){
    this.height = height;
}


@Length(max = 200)
public String getDescription(){
    return description;
}


}