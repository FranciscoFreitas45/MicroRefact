package com.empl.mgr.dto;
 import java.io.Serializable;
public class ModuleDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  String code;

 private  String superCode;

 private  String url;

 private  int level;

 private  boolean find;

 private  boolean del;

 private  boolean modify;

 private  boolean add;

public ModuleDto() {
// TODO Auto-generated constructor stub
}public ModuleDto(long id, String name, String code, String superCode, String url, int level) {
    super();
    this.id = id;
    this.name = name;
    this.code = code;
    this.superCode = superCode;
    this.url = url;
    this.level = level;
}
public void setName(String name){
    this.name = name;
}


public boolean isFind(){
    return find;
}


public String getName(){
    return name;
}


public void setSuperCode(String superCode){
    this.superCode = superCode;
}


public void setCode(String code){
    this.code = code;
}


public long getId(){
    return id;
}


public void setDel(boolean del){
    this.del = del;
}


public void setModify(boolean modify){
    this.modify = modify;
}


public boolean isAdd(){
    return add;
}


public void setUrl(String url){
    this.url = url;
}


public void setLevel(int level){
    this.level = level;
}


public String getUrl(){
    return url;
}


public int getLevel(){
    return level;
}


public void setFind(boolean find){
    this.find = find;
}


public void setId(long id){
    this.id = id;
}


@Override
public String toString(){
    return "ModuleDto [id:" + id + ", name:" + name + ", code:" + code + ", superCode:" + superCode + ", url:" + url + ", level:" + level + ", find:" + find + ", del:" + del + ", modify:" + modify + ", add:" + add + "]";
}


public String getSuperCode(){
    return superCode;
}


public boolean isModify(){
    return modify;
}


public String getCode(){
    return code;
}


public boolean isDel(){
    return del;
}


public void setAdd(boolean add){
    this.add = add;
}


}