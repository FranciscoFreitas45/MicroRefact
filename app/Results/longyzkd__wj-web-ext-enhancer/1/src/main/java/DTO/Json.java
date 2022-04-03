package DTO;
 import java.util.List;
import com.google.common.collect.Lists;
public class Json {

 private  boolean success;

 private  String msg;

 private  Object obj;

 private  List children;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public List getChildren(){
    return children;
}


public String getMsg(){
    return msg;
}


public Object getObj(){
    return obj;
}


public void setMsg(String msg){
    this.msg = msg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMsg"))

.queryParam("msg",msg)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSuccess(boolean success){
    this.success = success;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSuccess"))

.queryParam("success",success)
;
restTemplate.put(builder.toUriString(),null);
}


}