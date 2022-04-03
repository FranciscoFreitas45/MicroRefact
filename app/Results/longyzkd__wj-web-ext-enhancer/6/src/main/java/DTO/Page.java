package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public int getLimit(){
    return limit;
}


public int getPage(){
    return page;
}


public List<T> getDataList(){
    return dataList;
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


public String getOrderBy(){
    return orderBy;
}


public Integer getTotal(){
    return total;
}


public void setTotal(Integer total){
    this.total = total;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotal"))

.queryParam("total",total)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDataList(List<T> dataList){
    this.dataList = dataList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDataList"))

.queryParam("dataList",dataList)
;
restTemplate.put(builder.toUriString(),null);
}


}