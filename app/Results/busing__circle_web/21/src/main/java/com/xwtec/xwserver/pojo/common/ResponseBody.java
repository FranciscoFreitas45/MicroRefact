package com.xwtec.xwserver.pojo.common;
 public class ResponseBody {

 private  String resp_desc;

 private  String resp_code;

 private  ResponseData data;

 private  String log;


public void setLog(String log){
    this.log = log;
}


public String getResp_code(){
    return resp_code;
}


public void setData(ResponseData data){
    this.data = data;
}


public String getLog(){
    return log;
}


public String getResp_desc(){
    return resp_desc;
}


public void setResp_desc(String resp_desc){
    this.resp_desc = resp_desc;
}


public void setResp_code(String resp_code){
    this.resp_code = resp_code;
}


public ResponseData getData(){
    return data;
}


}