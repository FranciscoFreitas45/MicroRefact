package com.kingen.util;
 import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class JsonResultBuilder {

 public  String SUCCESS;

 public  String ERRORS;

 public  String ERROR;

 public  String DETAIL;

 public  String MSG;

 public  String ERRORCODE;

 public  String ERRORMSG;

 public  String DATA;

 private JSONObject jsonObject;

private JsonResultBuilder() {
}
public JsonResultBuilder msg(String result,String reason,String suggest){
    String message = String.format("%s，%s，%s。", result, reason, suggest);
    msg(message);
    return this;
}


public JsonResultBuilder addError(String name,String msg){
    JSONObject errorObject = getErrorJson();
    JSONArray json = (JSONArray) jsonObject.get(ERRORS);
    if (json == null) {
        json = new JSONArray();
        errorObject.put(ERRORS, json);
    }
    JSONObject newErrorJson = new JSONObject();
    newErrorJson.put(name, msg);
    json.add(newErrorJson);
    return this;
}


public JsonResultBuilder data(Object data){
    jsonObject.put(DATA, data);
    return this;
}


public JsonResultBuilder success(boolean success){
    JsonResultBuilder jsonResultBuilder = new JsonResultBuilder();
    JSONObject jsonObject = new JSONObject();
    jsonResultBuilder.jsonObject = jsonObject;
    jsonObject.put(SUCCESS, success);
    return jsonResultBuilder;
}


public JSONObject getErrorJson(){
    JSONObject errorJson = (JSONObject) jsonObject.get(ERROR);
    if (errorJson == null) {
        errorJson = new JSONObject();
        jsonObject.put(ERROR, errorJson);
    }
    return errorJson;
}


public JsonResultBuilder errorCode(int errorCode){
    JSONObject errorObject = getErrorJson();
    errorObject.put(ERRORCODE, errorCode);
    return this;
}


public JsonResultBuilder errorDetail(String detail){
    JSONObject errorObject = getErrorJson();
    errorObject.put(DETAIL, detail);
    return this;
}


public JSONObject json(){
    return jsonObject;
}


public String toString(){
    return jsonObject.toString();
}


public JsonResultBuilder errorMsg(String errorMsg){
    JSONObject errorObject = getErrorJson();
    errorObject.put(ERRORMSG, errorMsg);
    return this;
}


}