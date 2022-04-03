package com.example.steam.vo;
 import com.example.steam.entity.User;
public class UserVo extends User{

 private  String avatarImage;

public UserVo() {
    super();
}
public void setAvatarImage(String avatarImage){
    this.avatarImage = avatarImage;
}


public String getAvatarImage(){
    return avatarImage;
}


@Override
public String toString(){
    return avatarImage;
}


}