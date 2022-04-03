package DTO;
 import java.util.List;
import java.util.Map;
public class Client {

 private  long serialVersionUID;

 private  TSUser user;

 private  Map<String,TSFunction> functions;

 private  Map<Integer,List<TSFunction>> functionMap;

 private  java.lang.String ip;

 private  java.util.Date logindatetime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public java.lang.String getIp(){
    return ip;
}


public TSUser getUser(){
    return user;
}


public Map<String,TSFunction> getFunctions(){
    return functions;
}


public void setLogindatetime(java.util.Date logindatetime){
    this.logindatetime = logindatetime;
}


public void setIp(java.lang.String ip){
    this.ip = ip;
}


public Map<Integer,List<TSFunction>> getFunctionMap(){
    return functionMap;
}


public java.util.Date getLogindatetime(){
    return logindatetime;
}


public void setUser(TSUser user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


}