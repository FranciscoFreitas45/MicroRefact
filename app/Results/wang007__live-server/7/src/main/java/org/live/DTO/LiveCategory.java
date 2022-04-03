package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
public class LiveCategory extends BaseEntity{

 private  String categoryName;

 private  String coverUrl;

 private  String description;

 private  boolean enabled;

 private  int serialNo;


public String getCategoryName(){
    return categoryName;
}


public String getCoverUrl(){
    return coverUrl;
}


public String getDescription(){
    return description;
}


public int getSerialNo(){
    return serialNo;
}


}