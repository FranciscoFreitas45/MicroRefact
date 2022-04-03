package org.live.common.response;
 public interface ResponseModel {


public ResponseModel setData(T data)
;

public ResponseModel success(String message)
;

public String getMessage()
;

public int getStatus()
;

public ResponseModel setMessage(String message)
;

public ResponseModel error(String message)
;

public ResponseModel setStatus(int status)
;

public T getData()
;

}