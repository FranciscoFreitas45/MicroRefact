package org.jeecgframework.tag.vo.datatable;
 import java.util.List;
public class DataTableReturn {

 private  Integer iTotalRecords;

 private  Integer iTotalDisplayRecords;

 private  Integer sEcho;

 private  List<?> aaData;

public DataTableReturn(Integer iTotalRecords, Integer iTotalDisplayRecords, Integer sEcho, List<?> aaData) {
    this.iTotalRecords = iTotalRecords;
    this.iTotalDisplayRecords = iTotalDisplayRecords;
    this.sEcho = sEcho;
    this.aaData = aaData;
}
public void setiTotalDisplayRecords(Integer iTotalDisplayRecords){
    this.iTotalDisplayRecords = iTotalDisplayRecords;
}


public void setsEcho(Integer sEcho){
    this.sEcho = sEcho;
}


public Integer getiTotalRecords(){
    return iTotalRecords;
}


public Integer getsEcho(){
    return sEcho;
}


public List<?> getAaData(){
    return aaData;
}


public void setAaData(List<?> aaData){
    this.aaData = aaData;
}


public Integer getiTotalDisplayRecords(){
    return iTotalDisplayRecords;
}


public void setiTotalRecords(Integer iTotalRecords){
    this.iTotalRecords = iTotalRecords;
}


}