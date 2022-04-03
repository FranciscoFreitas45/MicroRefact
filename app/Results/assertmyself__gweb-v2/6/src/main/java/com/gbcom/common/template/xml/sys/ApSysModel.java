package com.gbcom.common.template.xml.sys;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("ApSysModel")
public class ApSysModel {

@XStreamAsAttribute
 private  String sysModel;

@XStreamAsAttribute
 private  String modelId;

@XStreamAsAttribute
 private  int hardwareType;

@XStreamAsAttribute
 private  int boardVersion;

@XStreamAsAttribute
 private  boolean dev5g;

@XStreamAsAttribute
 private  String desc;


public String getModelId(){
    return modelId;
}


public void setHardwareType(int hardwareType){
    this.hardwareType = hardwareType;
}


public void setBoardVersion(int boardVersion){
    this.boardVersion = boardVersion;
}


public void setModelId(String modelID){
    this.modelId = modelID;
}


public String getSysModel(){
    return sysModel;
}


public void setSysModel(String sysModel){
    this.sysModel = sysModel;
}


public boolean isDev5g(){
    return dev5g;
}


public int getHardwareType(){
    return hardwareType;
}


public int getBoardVersion(){
    return boardVersion;
}


public String getDesc(){
    return desc;
}


public void setDev5g(boolean dev5g){
    this.dev5g = dev5g;
}


public void setDesc(String desc){
    this.desc = desc;
}


}