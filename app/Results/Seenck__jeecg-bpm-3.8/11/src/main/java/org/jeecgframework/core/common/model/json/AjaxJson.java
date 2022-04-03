package org.jeecgframework.core.common.model.json;
 import java.util.Map;
import com.alibaba.fastjson.JSONObject;
public class AjaxJson {

 private  boolean success;

 private  String msg;

 private  Object obj;

 private  Map<String,Object> attributes;


public void setSuccess(boolean success){
    this.success = success;
}


public Map<String,Object> getAttributes(){
    return attributes;
}


public String getMsg(){
    return msg;
}


public void setObj(Object obj){
    this.obj = obj;
}


public Object getObj(){
    return obj;
}


public void setAttributes(Map<String,Object> attributes){
    this.attributes = attributes;
}


public void setMsg(String msg){
    this.msg = msg;
}


public String getJsonStr(){
    JSONObject obj = new JSONObject();
    obj.put("success", this.isSuccess());
    obj.put("msg", this.getMsg());
    obj.put("obj", this.obj);
    obj.put("attributes", this.attributes);
    return obj.toJSONString();
}


public boolean isSuccess(){
    return success;
}


}