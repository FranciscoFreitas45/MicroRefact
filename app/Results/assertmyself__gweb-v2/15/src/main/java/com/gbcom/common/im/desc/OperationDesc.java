package com.gbcom.common.im.desc;
 public class OperationDesc implements IOperationDesc{

 private  long serialVersionUID;

 private  int operGroupId;

 private  int operID;

 private  int level;

 private  IClassDesc parentClass;

 private  String operName;

 private  String atCmd;

 private  String rspParserClassName;


@Override
public int getCID(){
    if (this.parentClass != null) {
        return this.parentClass.getID();
    } else {
        return -1;
    }
}


@Override
public int getOperGroup(){
    return this.operGroupId;
}


public void setOperGroup(int operGroupId){
    this.operGroupId = operGroupId;
}


@Override
public int getOperID(){
    return this.operID;
}


public void setOperName(String operName){
    this.operName = operName;
}


public IClassDesc getParentClass(){
    return parentClass;
}


@Override
public String getOperName(){
    return this.operName;
}


@Override
public String getRspParserClassName(){
    return this.rspParserClassName;
}


@Override
public int getOperLevel(){
    // 需要在VM表中定义
    return this.level;
}


public void setOperLevel(int level){
    this.level = level;
}


@Override
public String toString(){
    StringBuffer buf = new StringBuffer();
    buf.append("\n\nOpt group Id:" + this.getOperGroup());
    buf.append("\nOpt opt id:" + this.getOperID());
    buf.append("\nOpt opt name:" + this.getOperName());
    buf.append("\nOpt atCmd: " + getAtCmd());
    buf.append("\nOpt rspParserClassName: " + getRspParserClassName());
    return buf.toString();
}


@Override
public String getAtCmd(){
    return this.atCmd;
}


public void setOperID(int operID){
    this.operID = operID;
}


public void setParentClass(IClassDesc parentClass){
    this.parentClass = parentClass;
}


public void setAtCmd(String atCmd){
    this.atCmd = atCmd;
}


public void setRspParserClassName(String rspParserClassName){
    this.rspParserClassName = rspParserClassName;
}


}