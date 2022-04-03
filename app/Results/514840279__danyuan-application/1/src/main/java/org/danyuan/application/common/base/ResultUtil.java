package org.danyuan.application.common.base;
 public class ResultUtil {


public BaseResult<T> success(){
    return commonResult(200, "请求成功", null);
}


public BaseResult<T> error(Integer code,String errorMsg){
    return commonResult(code, errorMsg, null);
}


public BaseResult<T> commonResult(Integer code,String errMsg,T data){
    BaseResult<T> result = new BaseResult<>();
    result.setCode(code);
    result.setMsg(errMsg);
    result.setData(data);
    return result;
}


}