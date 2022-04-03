package DTO;
 public class ValidForm {

 private  String status;

 private  String info;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getInfo(){
    return info;
}


public String getStatus(){
    return status;
}


public void setInfo(String info){
    this.info = info;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInfo"))

.queryParam("info",info)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


}