package com.uec.imonitor.common.datatables;
 import java.util.List;
public class DataTablesResponseEntity {

 private  int sEcho;

 private  long iTotalRecords;

 private  long iTotalDisplayRecords;

 private  List<T> aaData;

public DataTablesResponseEntity() {
    super();
}
public void setiTotalDisplayRecords(long iTotalDisplayRecords){
    if (iTotalDisplayRecords > 10000) {
        this.iTotalDisplayRecords = 10000;
    } else {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
}


public void setsEcho(int sEcho){
    this.sEcho = sEcho;
}


public int getsEcho(){
    return sEcho;
}


public long getiTotalRecords(){
    return iTotalRecords;
}


public List<T> getAaData(){
    return aaData;
}


public void setAaData(List<T> list){
    this.aaData = list;
}


public long getiTotalDisplayRecords(){
    return iTotalDisplayRecords;
}


public void setiTotalRecords(long iTotalRecords){
    this.iTotalRecords = iTotalRecords;
}


}