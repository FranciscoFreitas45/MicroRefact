package com.gbcom.system.menu;
 import java.util.List;
public class LiTag {

 private  String id;

 private  ATag a;

 private  List<UlTag> ulList;


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setA(ATag a){
    this.a = a;
}


public ATag getA(){
    return a;
}


public void setUlList(List<UlTag> ulList){
    this.ulList = ulList;
}


public List<UlTag> getUlList(){
    return ulList;
}


}