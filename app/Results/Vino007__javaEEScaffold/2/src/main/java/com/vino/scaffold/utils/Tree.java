package com.vino.scaffold.utils;
 public class Tree {

 private  Long id;

 private  Long pId;

 private  String name;

 private  boolean checked;

 private  boolean open;


public void setName(String name){
    this.name = name;
}


public void setOpen(boolean open){
    this.open = open;
}


public boolean isOpen(){
    return open;
}


public String getName(){
    return name;
}


public void setChecked(boolean checked){
    this.checked = checked;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setpId(Long pId){
    this.pId = pId;
}


public boolean isChecked(){
    return checked;
}


public Long getpId(){
    return pId;
}


}