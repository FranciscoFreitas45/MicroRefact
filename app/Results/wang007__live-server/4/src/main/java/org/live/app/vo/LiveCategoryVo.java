package org.live.app.vo;
 public class LiveCategoryVo {

 private  String id;

 private  String categoryName;

 private  String coverUrl;

public LiveCategoryVo() {
}public LiveCategoryVo(String id, String categoryName, String coverUrl) {
    this.id = id;
    this.categoryName = categoryName;
    this.coverUrl = coverUrl;
}
public void setCoverUrl(String coverUrl){
    this.coverUrl = coverUrl;
}


public String getCategoryName(){
    return categoryName;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
}


public String getCoverUrl(){
    return coverUrl;
}


}