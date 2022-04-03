package com.ushahidi.swiftriver.core.api.filter;
 import java.util.Date;
public class TrendFilter {

 private  int page;

 private  int count;

 private  Date dateFrom;

 private  Date dateTo;


public Date getDateFrom(){
    return dateFrom;
}


public void setDateTo(Date dateTo){
    this.dateTo = dateTo;
}


public int getPage(){
    return page;
}


public Date getDateTo(){
    return dateTo;
}


public int getCount(){
    return count;
}


public void setDateFrom(Date dateFrom){
    this.dateFrom = dateFrom;
}


public void setCount(int count){
    this.count = count;
}


public void setPage(int page){
    this.page = page;
}


}