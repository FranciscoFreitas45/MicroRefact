package com.kingen.util;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Page {

 protected  int page;

 protected  int limit;

 private  Integer total;

 private  List<T> dataList;

 private  Boolean success;

 private  String orderBy;


public void setTotal(Integer total){
    this.total = total;
}


public int getLimit(){
    return limit;
}


public int getPage(){
    return page;
}


public List<T> getDataList(){
    return dataList;
}


public void setOrderBy(String orderBy){
    this.orderBy = orderBy;
}


public int getFirstResult(){
    int firstResult = (page - 1) * limit;
    if (firstResult >= total) {
        firstResult = 0;
    }
    return firstResult;
}


public Boolean getSuccess(){
    return success;
}


public void setSuccess(Boolean success){
    this.success = success;
}


public String getOrderBy(){
    return orderBy;
}


public void setLimit(int limit){
    this.limit = limit;
}


public Integer getTotal(){
    return total;
}


public void setDataList(List<T> dataList){
    this.dataList = dataList;
}


public void setPage(int page){
    this.page = page;
}


}