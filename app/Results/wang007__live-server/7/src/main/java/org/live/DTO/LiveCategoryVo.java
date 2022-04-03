package org.live.DTO;
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
public String getCategoryName(){
    return categoryName;
}


public String getId(){
    return id;
}


public String getCoverUrl(){
    return coverUrl;
}


}