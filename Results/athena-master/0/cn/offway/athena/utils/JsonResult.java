import java.io.Serializable;
public class JsonResult implements Serializable{

 private  long serialVersionUID;

 private  String code;

 private  String msg;

 private  Object data;


public String getMsg(){
    return msg;
}


public void setData(Object data){
    this.data = data;
}


public void setCode(String code){
    this.code = code;
}


public String getCode(){
    return code;
}


public void setMsg(String msg){
    this.msg = msg;
}


public Object getData(){
    return data;
}


}