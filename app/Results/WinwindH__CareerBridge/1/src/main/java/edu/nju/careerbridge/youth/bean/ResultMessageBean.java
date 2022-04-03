package edu.nju.careerbridge.youth.bean;
 public class ResultMessageBean {

 private  boolean result;

 private  String message;

public ResultMessageBean(boolean result) {
    this.result = result;
    this.message = "Success";
}public ResultMessageBean(boolean result, String message) {
    this.result = result;
    this.message = message;
}
}