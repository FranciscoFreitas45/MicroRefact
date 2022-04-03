package com.xwtec.xwserver.pojo.demo;
 import java.util.List;
import java.util.Map;
public class MvcDemo {

 private  String str;

 private  Map<String,String> map;

 private  Map<String,MvcSubDemo> objMap;

 private  List<String> list;

 private  List<MvcSubDemo> mvcSubDemoList;


public List<MvcSubDemo> getMvcSubDemoList(){
    return mvcSubDemoList;
}


public String getStr(){
    return str;
}


public List<String> getList(){
    return list;
}


public void setMap(Map<String,String> map){
    this.map = map;
}


public void setObjMap(Map<String,MvcSubDemo> objMap){
    this.objMap = objMap;
}


public Map<String,MvcSubDemo> getObjMap(){
    return objMap;
}


public void setList(List<String> list){
    this.list = list;
}


public void setStr(String str){
    this.str = str;
}


public void setMvcSubDemoList(List<MvcSubDemo> mvcSubDemoList){
    this.mvcSubDemoList = mvcSubDemoList;
}


public Map<String,String> getMap(){
    return map;
}


}