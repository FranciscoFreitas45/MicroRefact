package com.qidian.hcm.common.utils;
 public class ResultGenerator {

 private  String DEFAULT_SUCCESS_MESSAGE;

private ResultGenerator() {
}
public Result genSuccessResult(Object data){
    return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
}


public Result genFailResult(String code,String message){
    return new Result().setCode(code).setMessage(message);
}


}