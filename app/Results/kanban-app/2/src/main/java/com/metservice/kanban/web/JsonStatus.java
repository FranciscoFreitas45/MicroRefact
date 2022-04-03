package com.metservice.kanban.web;
 public class JsonStatus {

 public  String status;

 public  String message;

private JsonStatus(String status, String message) {
    this.status = status;
    this.message = message;
}
public JsonStatus createOkStatus(){
    return createOkStatus("");
}


public JsonStatus createErrorStatus(String message){
    return new JsonStatus("error", message);
}


}