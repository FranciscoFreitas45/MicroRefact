package sn.DTO;
 import lombok.Data;
public class ResponseDataMessage extends AbstractResponse{

 private  String message;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public ResponseDataMessage(String message) {
    this.message = message;
}
public ResponseDataMessage ok(){
    return new ResponseDataMessage("Ok");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ok"))

;
ResponseDataMessage aux = restTemplate.getForObject(builder.toUriString(),ResponseDataMessage.class);
return aux;
}


}