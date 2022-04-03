package com.cocay.sicecd.dto;
 public class ResponseGenericPagination {

 private  Integer total;

 private  Object rows;

 private  String paginaSig;

 private  String paginaAnt;


public void setTotal(Integer total){
    this.total = total;
}


public String getPaginaAnt(){
    return paginaAnt;
}


public void setPaginaAnt(String paginaAnt){
    this.paginaAnt = paginaAnt;
}


public void setRows(Object rows){
    this.rows = rows;
}


public void setPaginaSig(String paginaSig){
    this.paginaSig = paginaSig;
}


public Integer getTotal(){
    return total;
}


public Object getRows(){
    return rows;
}


public String getPaginaSig(){
    return paginaSig;
}


}