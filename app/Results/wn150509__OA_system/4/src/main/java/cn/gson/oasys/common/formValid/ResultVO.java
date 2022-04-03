package cn.gson.oasys.common.formValid;
 public class ResultVO {

 private  Integer code;

 private  String msg;

 private T Data;

public ResultVO() {
}public ResultVO(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    Data = data;
}public ResultVO(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
}
public String getMsg(){
    return msg;
}


public void setData(T data){
    Data = data;
}


public ResultVO success(T data){
    this.code = ResultEnum.SUCCESS.getCode();
    this.msg = ResultEnum.SUCCESS.getMessage();
    this.Data = data;
    return new ResultVO(code, msg, data);
}


public void setCode(Integer code){
    this.code = code;
}


public ResultVO error(Integer code,String message,T data){
    this.code = ResultEnum.ERROR.getCode();
    this.msg = msg;
    this.Data = data;
    return new ResultVO(code, msg, data);
}


public Integer getCode(){
    return code;
}


public void setMsg(String msg){
    this.msg = msg;
}


public T getData(){
    return Data;
}


}