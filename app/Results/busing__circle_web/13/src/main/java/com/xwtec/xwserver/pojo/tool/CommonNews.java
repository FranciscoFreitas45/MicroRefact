package com.xwtec.xwserver.pojo.tool;
 public class CommonNews {

 private  String nid;

 private  String appid;

 private  String cateid;

 private  String city_code;

 private  String ntitle;

 private  String nphoto;

 private  String ncontent;

 private  String npostdate;

 private  String is_del;


public String getNtitle(){
    return ntitle;
}


public void setIs_del(String isDel){
    is_del = isDel;
}


public void setNid(String nid){
    this.nid = nid;
}


public void setCateid(String cateid){
    this.cateid = cateid;
}


public void setNcontent(String ncontent){
    this.ncontent = ncontent;
}


public String getIs_del(){
    return is_del;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getAppid(){
    return appid;
}


public String getNcontent(){
    return ncontent;
}


public String getCateid(){
    return cateid;
}


public void setCity_code(String cityCode){
    city_code = cityCode;
}


public void setNpostdate(String npostdate){
    this.npostdate = npostdate;
}


public void setNtitle(String ntitle){
    this.ntitle = ntitle;
}


public void setNphoto(String nphoto){
    this.nphoto = nphoto;
}


public String getNid(){
    return nid;
}


public String getCity_code(){
    return city_code;
}


public String getNphoto(){
    return nphoto;
}


public String getNpostdate(){
    return npostdate;
}


}