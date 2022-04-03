package com.zis.bookinfo.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "bookinfo_aid")
public class BookinfoAid {

 private  long serialVersionUID;

@Id
@GeneratedValue
@Column(name = "id")
 private  Integer id;

@Column(name = "groupKey", nullable = false)
 private  String groupKey;

@Column(name = "shortBookName", nullable = false)
 private  String shortBookName;

@Column(name = "ids", nullable = false)
 private  String ids;

@Column(name = "totalCount", nullable = false)
 private  Integer totalCount;

@Column(name = "check_level")
 private  Integer checkLevel;

// Constructors
/**
 * default constructor
 */
public BookinfoAid() {
}/**
 * full constructor
 */
public BookinfoAid(String groupKey, String shortBookName, String ids, Integer totalCount) {
    this.groupKey = groupKey;
    this.shortBookName = shortBookName;
    this.ids = ids;
    this.totalCount = totalCount;
}
public String getShortBookName(){
    return this.shortBookName;
}


public void setTotalCount(Integer totalCount){
    this.totalCount = totalCount;
}


public void setGroupKey(String groupKey){
    this.groupKey = groupKey;
}


public void setCheckLevel(Integer checkLevel){
    this.checkLevel = checkLevel;
}


public void setIds(String ids){
    this.ids = ids;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getTotalCount(){
    return this.totalCount;
}


public Integer getId(){
    return this.id;
}


public String getIds(){
    return this.ids;
}


public Integer getCheckLevel(){
    return checkLevel;
}


public String getGroupKey(){
    return this.groupKey;
}


public void setShortBookName(String shortBookName){
    this.shortBookName = shortBookName;
}


}