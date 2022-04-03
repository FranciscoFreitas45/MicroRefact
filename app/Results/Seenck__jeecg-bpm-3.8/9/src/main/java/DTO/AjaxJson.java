package DTO;
 import java.util.Map;
import com.alibaba.fastjson.JSONObject;
public class AjaxJson {

 private  boolean success;

 private  String msg;

 private  Object obj;

 private  Map<String,Object> attributes;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


public Map<String,Object> getAttributes(){
    return attributes;
}


public String getMsg(){
    return msg;
}


public Object getObj(){
    return obj;
}


public String getJsonStr(){
    JSONObject obj = new JSONObject();
    obj.put("success", this.isSuccess());
    obj.put("msg", this.getMsg());
    obj.put("obj", this.obj);
    obj.put("attributes", this.attributes);
    return obj.toJSONString();
}


public void setObj(Object obj){
    this.obj = obj;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setObj"))

.queryParam("obj",obj)
;
restTemplate.put(builder.toUriString(),null);
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