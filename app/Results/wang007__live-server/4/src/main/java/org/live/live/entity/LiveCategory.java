package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "live_category")
public class LiveCategory extends BaseEntity{

@Column
 private  String categoryName;

@Column
 private  String coverUrl;

@Column
 private  String description;

@Column
 private  boolean enabled;

 private  int serialNo;


public void setCoverUrl(String coverUrl){
    this.coverUrl = coverUrl;
}


public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public boolean isEnabled(){
    return enabled;
}


public String getCategoryName(){
    return categoryName;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
}


public void setDescription(String description){
    this.description = description;
}


public void setSerialNo(int serialNo){
    this.serialNo = serialNo;
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