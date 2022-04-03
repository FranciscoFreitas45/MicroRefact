package com.fzshuai.vo;
 public class BlogQuery {

 private  String title;

 private  Long typeId;

 private  boolean recommend;

public BlogQuery() {
}
public boolean isRecommend(){
    return recommend;
}


public String getTitle(){
    return title;
}


public void setTitle(String title){
    this.title = title;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Long getTypeId(){
    return typeId;
}


public void setRecommend(boolean recommend){
    this.recommend = recommend;
}


}