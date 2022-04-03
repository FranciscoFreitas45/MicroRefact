package com.metservice.kanban.DTO;
 public class JsonStatus {

 public  String status;

 public  String message;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

private JsonStatus(String status, String message) {
    this.status = status;
    this.message = message;
}
public JsonStatus createOkStatus(){
    return createOkStatus("");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createOkStatus"))

;
JsonStatus aux = restTemplate.getForObject(builder.toUriString(),JsonStatus.class);
return aux;
}


}