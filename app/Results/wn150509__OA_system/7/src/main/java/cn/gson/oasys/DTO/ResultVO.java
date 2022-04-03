package cn.gson.oasys.DTO;
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


public Integer getCode(){
    return code;
}


public T getData(){
    return Data;
}


}