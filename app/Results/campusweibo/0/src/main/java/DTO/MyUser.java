package DTO;

import java.io.Serializable;
public class MyUser implements Serializable{

 private  Long id;

 private  String schoolcode;

 private  String password;

 private  String nickname;

 private  String image_url;

 private  String sex;

 private  String school;


public Long getId(){
    return id;
}


public String getImage_url(){
    return image_url;
}


public String getPassword(){
    return password;
}


public String getSchool(){
    return school;
}


public String getNickname(){
    return nickname;
}


public String getSex(){
    return sex;
}


public String getSchoolcode(){
    return schoolcode;
}


}