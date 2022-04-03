package com.easyshopping.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_navigation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_navigation_sequence")
public class Navigation extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Position position;

 private  String url;

 private  Boolean isBlankTarget;


public void setName(String name){
    this.name = name;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getUrl(){
    return url;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@NotNull
@Column(nullable = false)
public Position getPosition(){
    return position;
}


public void setIsBlankTarget(Boolean isBlankTarget){
    this.isBlankTarget = isBlankTarget;
}


@NotNull
@Column(nullable = false)
public Boolean getIsBlankTarget(){
    return isBlankTarget;
}


public void setPosition(Position position){
    this.position = position;
}


public void setUrl(String url){
    this.url = url;
}


}