package com.gbcom.common.im.desc;
 public class RootClassDesc extends ClassDesc{

 private  long serialVersionUID;

 private  String type;

 private  String version;

/**
 * 构造函数
 *
 * @param classID int
 * @param className String
 */
public RootClassDesc(int classID, String className) {
    super(classID, className);
}/**
 * 构造函数
 *
 * @param classID String
 * @param className String
 */
public RootClassDesc(String classID, String className) {
    super(classID, className);
}
public String getVersion(){
    return this.version;
}


public String getType(){
    return this.type;
}


public void setVersion(String version){
    this.version = version;
}


public void setType(String type){
    this.type = type;
}


}