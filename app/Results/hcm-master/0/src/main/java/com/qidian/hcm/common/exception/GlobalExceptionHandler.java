package com.qidian.hcm.common.exception;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.common.utils.ErrorResult;
import com.qidian.hcm.common.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.NoHandlerFoundException;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


@ExceptionHandler
@ResponseStatus(HttpStatus.NOT_FOUND)
public ErrorResult onNoHandlerFoundException(NoHandlerFoundException e){
    return new ErrorResult(ResultCode.RESOURCE_NOT_FOUND, e.getMessage());
}


@ExceptionHandler
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ErrorResult onMethodArgumentNotValidException(MethodArgumentNotValidException e){
    BindingResult bindingResult = e.getBindingResult();
    JSONObject error = new JSONObject();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
        error.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return new ErrorResult(ResultCode.PARAM_ERROR, error);
}


@ExceptionHandler
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ErrorResult onHttpMessageNotReadableException(HttpMessageNotReadableException e){
    return new ErrorResult(ResultCode.PARAM_ERROR, e.getMessage());
}


@ExceptionHandler(BizException.class)
public ErrorResult handleBizException(BizException e){
    return new ErrorResult(e.getErrorCode(), e.getMessage(), e.getData());
}


@ExceptionHandler
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public ErrorResult onException(Exception e){
    // 增加打印日志
    log.error(ExceptionUtils.getStackTrace(e));
    return new ErrorResult(ResultCode.SYSTEM_ERROR, e.getMessage());
}


@ExceptionHandler
@ResponseStatus(HttpStatus.OK)
public ErrorResult onAccessDeniedException(AccessDeniedException e){
    return new ErrorResult(ResultCode.DO_NOT_HAVE_ACCESS, e.getMessage());
}


}