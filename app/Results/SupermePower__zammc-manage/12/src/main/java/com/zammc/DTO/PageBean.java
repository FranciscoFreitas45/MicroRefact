package com.zammc.DTO;
 import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
public class PageBean {

 private  long totalRecorder;

 private  int pageSize;

 private  int pageNum;

 private  int totalPage;

 private  List<?> content;

 private  List<Map<String,Object>> data;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public int getPageNum(){
    return pageNum;
}


public List<?> getContent(){
    return content;
}


public int getPageSize(){
    return pageSize;
}


public long getTotalRecorder(){
    return totalRecorder;
}


public List<Map<String,Object>> getData(){
    return data;
}


public int getTotalPage(){
    if (totalRecorder % pageSize == 0) {
        totalPage = (int) (totalRecorder / pageSize);
    } else {
        totalPage = (int) ((totalRecorder / pageSize) + 1);
    }
    return totalPage;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPageSize"))

.queryParam("pageSize",pageSize)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPageNum(int pageNum){
    this.pageNum = pageNum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPageNum"))

.queryParam("pageNum",pageNum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTotalPage(int totalPage){
    this.totalPage = totalPage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotalPage"))

.queryParam("totalPage",totalPage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTotalRecorder(long totalRecorder){
    this.totalRecorder = totalRecorder;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotalRecorder"))

.queryParam("totalRecorder",totalRecorder)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContent(List<?> content){
    this.content = content;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContent"))

.queryParam("content",content)
;
restTemplate.put(builder.toUriString(),null);
}


}