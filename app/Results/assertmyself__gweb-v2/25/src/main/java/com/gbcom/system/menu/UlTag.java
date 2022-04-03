package com.gbcom.system.menu;
 import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
public class UlTag {

 private  String id;

 private  String text;

 private  List<LiTag> liList;


public String getText(){
    return text;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setLiList(List<LiTag> liList){
    this.liList = liList;
}


public List<LiTag> getLiList(){
    if (liList == null) {
        liList = new ArrayList();
    }
    return liList;
}


public void setText(String text){
    this.text = text;
}


}