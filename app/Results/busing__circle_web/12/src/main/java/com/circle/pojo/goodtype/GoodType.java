package com.circle.pojo.goodtype;
 public class GoodType {

 public  int id;

 private  String name;

 private  int parent_id;

 private  int sort_id;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public int getSort_id(){
    return sort_id;
}


public void setId(int id){
    this.id = id;
}


public void setParent_id(int parent_id){
    this.parent_id = parent_id;
}


public int getId(){
    return id;
}


public void setSort_id(int sort_id){
    this.sort_id = sort_id;
}


public int getParent_id(){
    return parent_id;
}


}