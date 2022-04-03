package com.empl.mgr.DTO;
 import java.io.Serializable;
public class JSONReturn implements Serializable{

 private  long serialVersionUID;

 private  boolean head;

 private  Object body;

 private  boolean HEAD_SUCCESS;

 private  boolean HEAD_FAILURE;

 private  String EMPTY_BODY;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public JSONReturn() {
// TODO Auto-generated constructor stub
}public JSONReturn(boolean head, Object body) {
    super();
    this.head = head;
    this.body = body;
}
public Object getBody(){
    return body;
}


public JSONReturn buildFailure(Object body){
    return build(HEAD_FAILURE, body);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildFailure"))

.queryParam("body",body)
;
JSONReturn aux = restTemplate.getForObject(builder.toUriString(),JSONReturn.class);
return aux;
}


public JSONReturn buildSuccess(Object body){
    return build(HEAD_SUCCESS, body);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildSuccess"))

.queryParam("body",body)
;
JSONReturn aux = restTemplate.getForObject(builder.toUriString(),JSONReturn.class);
return aux;
}


public JSONReturn buildSuccessWithEmptyBody(){
    return build(HEAD_SUCCESS, EMPTY_BODY);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildSuccessWithEmptyBody"))

;
JSONReturn aux = restTemplate.getForObject(builder.toUriString(),JSONReturn.class);
return aux;
}


}