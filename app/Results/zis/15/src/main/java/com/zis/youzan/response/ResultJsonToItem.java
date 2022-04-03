package com.zis.youzan.response;
 import com.alibaba.fastjson.annotation.JSONField;
public class ResultJsonToItem {

@JSONField(name = "response")
 private  Response response;

@JSONField(name = "error_response")
 private  ErrorResponse errorResponse;

@JSONField(name = "item")
 private  Item item;


public ErrorResponse getErrorResponse(){
    return errorResponse;
}


public Response getResponse(){
    return response;
}


public Item getItem(){
    return item;
}


public void setResponse(Response response){
    this.response = response;
}


public void setErrorResponse(ErrorResponse errorResponse){
    this.errorResponse = errorResponse;
}


@Override
public String toString(){
    return "Response [item=" + item + "]";
}


public void setItem(Item item){
    this.item = item;
}


}