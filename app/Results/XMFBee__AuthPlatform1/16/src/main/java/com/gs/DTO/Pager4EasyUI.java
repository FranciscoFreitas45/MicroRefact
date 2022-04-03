package com.gs.DTO;
 import java.util.List;
public class Pager4EasyUI {

 private  int pageSize;

 private  int pageNo;

 private  int beginIndex;

 private  int total;

 private  List<T> rows;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public Pager4EasyUI() {
}public Pager4EasyUI(int total, List<T> rows) {
    this.total = total;
    this.rows = rows;
}
public int getPageSize(){
    return pageSize;
}


public int getTotal(){
    return total;
}


public int getPageNo(){
    return pageNo;
}


public List<T> getRows(){
    return rows;
}


public int getBeginIndex(){
    if (pageNo == 0) {
        pageNo = 1;
    }
    return (pageNo - 1) * pageSize;
}


public void setRows(List<T> rows){
    this.rows = rows;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRows"))

.queryParam("rows",rows)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTotal(int total){
    this.total = total;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotal"))

.queryParam("total",total)
;
restTemplate.put(builder.toUriString(),null);
}


}