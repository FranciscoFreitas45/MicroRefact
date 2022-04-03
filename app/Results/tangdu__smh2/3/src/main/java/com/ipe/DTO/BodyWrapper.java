package com.ipe.DTO;
 import java.io.Serializable;
public class BodyWrapper implements Serializable{

 private  Long total;

 private  Boolean success;

 private  Object rows;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public Long getTotal(){
    return total;
}


public Object getRows(){
    return rows;
}


public Boolean getSuccess(){
    return success;
}


public void setRows(Object rows){
    this.rows = rows;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRows"))

.queryParam("rows",rows)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTotal(Long total){
    this.total = total;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotal"))

.queryParam("total",total)
;
restTemplate.put(builder.toUriString(),null);
}


}