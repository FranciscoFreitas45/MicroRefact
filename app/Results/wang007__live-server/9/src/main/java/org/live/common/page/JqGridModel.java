package org.live.common.page;
 import java.util.List;
public class JqGridModel {

 private  int page;

 private  int total;

 private  long records;

 private  List<E> rows;


public void setTotal(int total){
    this.total = total;
}


public void setRows(List<E> rows){
    this.rows = rows;
}


public void setRecords(long records){
    this.records = records;
}


public int getPage(){
    return page;
}


public long getRecords(){
    return records;
}


public int getTotal(){
    return total;
}


public List<E> getRows(){
    return rows;
}


public void setPage(int page){
    this.page = page;
}


}