package com.example.steam.DTO;
 import org.springframework.stereotype.Component;
public class Type {

 private  Long id;

 private  String typeName;

public Type() {
}
public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public String getTypeName(){
    return typeName;
}


}