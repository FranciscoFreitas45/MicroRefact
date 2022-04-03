package com.zammc.domain.user;
 import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
@Entity
@Table(name = "user_info")
@DynamicInsert
@DynamicUpdate
public class UserInfoEntity {

 private  String userId;

 private  String nickName;

 private  String avatarUrl;

 private  String city;

 private  String country;

 private  Byte gender;

 private  String language;

 private  byte dataStatus;

 private  String province;

 private  int version;


@Basic
@Column(name = "avatar_url")
public String getAvatarUrl(){
    return avatarUrl;
}


public void setAvatarUrl(String avatarUrl){
    this.avatarUrl = avatarUrl;
}


@Basic
@Column(name = "version")
@Version
public int getVersion(){
    return version;
}


public void setCountry(String country){
    this.country = country;
}


@Basic
@Column(name = "language")
public String getLanguage(){
    return language;
}


@Basic
@Column(name = "country")
public String getCountry(){
    return country;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setProvince(String province){
    this.province = province;
}


public void setCity(String city){
    this.city = city;
}


public void setVersion(int version){
    this.version = version;
}


@Basic
@Column(name = "nick_name")
public String getNickName(){
    return nickName;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


@Basic
@Column(name = "gender")
public Byte getGender(){
    return gender;
}


public void setGender(Byte gender){
    this.gender = gender;
}


@Basic
@Column(name = "province")
public String getProvince(){
    return province;
}


@Id
@Column(name = "user_id")
public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


@Basic
@Column(name = "city")
public String getCity(){
    return city;
}


public void setLanguage(String lanaguage){
    this.language = lanaguage;
}


}