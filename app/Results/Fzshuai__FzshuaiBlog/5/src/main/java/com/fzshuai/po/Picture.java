package com.fzshuai.po;
 import javax.persistence;
@Entity
@Table(name = "t_picture")
public class Picture {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

 private  String picturename;

 private  String picturetime;

 private  String pictureaddress;

 private  String picturedescription;

public Picture() {
}
public String getPicturename(){
    return picturename;
}


public void setPicturetime(String picturetime){
    this.picturetime = picturetime;
}


public String getPictureaddress(){
    return pictureaddress;
}


public String getPicturedescription(){
    return picturedescription;
}


public void setPictureaddress(String pictureaddress){
    this.pictureaddress = pictureaddress;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "Picture{" + "id=" + id + ", picturename='" + picturename + '\'' + ", picturetime='" + picturetime + '\'' + ", pictureaddress='" + pictureaddress + '\'' + ", picturedescription='" + picturedescription + '\'' + '}';
}


public void setPicturename(String picturename){
    this.picturename = picturename;
}


public void setPicturedescription(String picturedescription){
    this.picturedescription = picturedescription;
}


public String getPicturetime(){
    return picturetime;
}


}