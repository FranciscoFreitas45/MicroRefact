package org.gliderwiki.DTO;
 public class Search {

 public  String SEARCH_TYPE_SUBJECT;

 public  String SEARCH_TYPE_CONTENT;

 public  String SEARCH_TYPE_NICKNAME;

 private  String orderQuery;

 private  Object parameter;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://14";


public String getOrderQuery(){
    return orderQuery;
}


public Object getParameter(){
    return parameter;
}


public void setParameter(Object parameter){
    this.parameter = parameter;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParameter"))

.queryParam("parameter",parameter)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrderQuery(String orderQuery){
    this.orderQuery = orderQuery;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderQuery"))

.queryParam("orderQuery",orderQuery)
;
restTemplate.put(builder.toUriString(),null);
}


}