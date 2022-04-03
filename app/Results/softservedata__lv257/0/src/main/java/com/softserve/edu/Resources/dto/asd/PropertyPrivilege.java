package com.softserve.edu.Resources.dto.asd;
 public class PropertyPrivilege {

 private  String name;

 private  boolean read;

 private  boolean update;

public PropertyPrivilege() {
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setRead(boolean read){
    this.read = read;
}


public void setUpdate(boolean update){
    this.update = update;
}


public boolean isRead(){
    return read;
}


public boolean isUpdate(){
    return update;
}


}