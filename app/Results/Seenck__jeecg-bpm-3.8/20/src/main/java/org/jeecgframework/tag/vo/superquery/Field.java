package org.jeecgframework.tag.vo.superquery;
 public class Field {

 private  String name;

 private  String txt;

 private  String dtype;

 private  String stype;

 private  String dicCode;

 private  String dicTable;

 private  String dictTxt;

 private  int seq;


public void setName(String name){
    this.name = name;
}


public int getSeq(){
    return seq;
}


public void setDicTable(String dicTable){
    this.dicTable = dicTable;
}


public String getName(){
    return name;
}


public void setDicCode(String dicCode){
    this.dicCode = dicCode;
}


public String getDicCode(){
    return dicCode;
}


public void setTxt(String txt){
    this.txt = txt;
}


public String getTxt(){
    return txt;
}


public String getDictTxt(){
    return dictTxt;
}


public void setDictTxt(String dictTxt){
    this.dictTxt = dictTxt;
}


public String getDtype(){
    return dtype;
}


public void setStype(String stype){
    this.stype = stype;
}


public String getDicTable(){
    return dicTable;
}


public void setSeq(int seq){
    this.seq = seq;
}


public void setDtype(String dtype){
    this.dtype = dtype;
}


public String getStype(){
    return stype;
}


}