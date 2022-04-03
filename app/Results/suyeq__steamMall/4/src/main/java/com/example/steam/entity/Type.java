package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class Type {

 private  Long id;

 private  String typeName;

public Type() {
}
public void setTypeName(String typeName){
    this.typeName = typeName;
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