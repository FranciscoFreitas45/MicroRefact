package com.easyshopping.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_friend_link")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_friend_link_sequence")
public class FriendLink extends OrderEntity{

 private  long serialVersionUID;

 private  String name;

 private  Type type;

 private  String logo;

 private  String url;


public void setName(String name){
    this.name = name;
}


public void setLogo(String logo){
    this.logo = logo;
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


@Length(max = 200)
public String getLogo(){
    return logo;
}


@NotNull
@Column(nullable = false)
public Type getType(){
    return type;
}


public void setType(Type type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


}