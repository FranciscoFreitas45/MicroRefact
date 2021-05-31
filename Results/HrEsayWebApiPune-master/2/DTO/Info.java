public class Info {

 private  boolean isError;

 private  String msg;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getMsg(){
    return msg;
}


public void setError(boolean isError){
    this.isError = isError;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setError"));

.queryParam("isError",isError);
restTemplate.put(builder.toUriString(),null);
}


public void setMsg(String msg){
    this.msg = msg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMsg"));

.queryParam("msg",msg);
restTemplate.put(builder.toUriString(),null);
}


public boolean isError(){
    return isError;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isError"));

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}