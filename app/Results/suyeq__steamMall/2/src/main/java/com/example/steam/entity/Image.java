package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class Image {

 private  Long id;

 private  String url;

 private  String gameName;

 private  String type;

public Image() {
}public Image(String url, String gameName, String type) {
    this.url = url;
    this.gameName = gameName;
    this.type = type;
}
public String getUrl(){
    return url;
}


public void setGameName(String gameName){
    this.gameName = gameName;
}


public String getType(){
    return type;
}


public String getGameName(){
    return gameName;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


}