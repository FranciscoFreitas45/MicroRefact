package com.zis.youzan.response;
 import java.util.List;
import com.alibaba.fastjson.annotation.JSONField;
public class ResultJsonToItemList {

@JSONField(name = "response")
 private  Response response;

@JSONField(name = "error_response")
 private  ErrorResponse errorResponse;

@JSONField(name = "total_results")
 private  Long totalResults;

@JSONField(name = "items")
 private  List<Item> items;


public List<Item> getItems(){
    return items;
}


public ErrorResponse getErrorResponse(){
    return errorResponse;
}


public Response getResponse(){
    return response;
}


public void setItems(List<Item> items){
    this.items = items;
}


public void setTotalResults(Long totalResults){
    this.totalResults = totalResults;
}


public void setResponse(Response response){
    this.response = response;
}


public void setErrorResponse(ErrorResponse errorResponse){
    this.errorResponse = errorResponse;
}


@Override
public String toString(){
    return "Response [totalResults=" + totalResults + ", items=" + items + "]";
}


public Long getTotalResults(){
    return totalResults;
}


}