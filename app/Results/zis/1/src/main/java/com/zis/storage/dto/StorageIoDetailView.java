package com.zis.storage.dto;
 import com.zis.bookinfo.bean.Bookinfo;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.Interface.Bookinfo;
public class StorageIoDetailView extends StorageIoDetail{

 private  Bookinfo book;

 private  String zhCnStatus;

 private  String zhCnType;

 private  String realName;


public void setZhCnType(String zhCnType){
    this.zhCnType = zhCnType;
}


public void setRealName(String realName){
    this.realName = realName;
}


public Bookinfo getBook(){
    return book;
}


public void setBook(Bookinfo book){
    this.book = book;
}


public String getZhCnType(){
    return zhCnType;
}


public String getZhCnStatus(){
    return zhCnStatus;
}


public void setZhCnStatus(String zhCnStatus){
    this.zhCnStatus = zhCnStatus;
}


public String getRealName(){
    return realName;
}


}