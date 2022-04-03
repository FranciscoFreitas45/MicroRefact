package org.jeecgframework.tag.vo.superquery;
 public class Table {

 private  String table;

 private  String isMain;

 private  String fKey;

 private  int seq;


public int getSeq(){
    return seq;
}


public String getfKey(){
    return fKey;
}


public void setfKey(String fKey){
    this.fKey = fKey;
}


public String getIsMain(){
    return isMain;
}


public String getTable(){
    return table;
}


public void setTable(String table){
    this.table = table;
}


public void setIsMain(String isMain){
    this.isMain = isMain;
}


public void setSeq(int seq){
    this.seq = seq;
}


}