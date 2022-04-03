package org.live.common.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
// 值为null的属性不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompleteResponseModel extends BatchResponseModel{

 private  Map<String,Object> body;

public CompleteResponseModel() {
    super();
}public CompleteResponseModel(Map body) {
    super();
    this.body = body;
}public CompleteResponseModel(int status, String message, Map body) {
    super();
    this.setStatus(status);
    this.setMessage(message);
    this.setBody(body);
}
public Map<String,Object> getBody(){
    return body;
}


public void setBody(Map<String,Object> body){
    this.body = body;
}


}