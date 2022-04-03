package DTO;
 public class UKView {

 private  String page;

 private  String templet;

public UKView(String templet, String page) {
    this.templet = templet;
    this.page = page;
}public UKView(String page) {
    this.page = page;
}
public String getPage(){
    return page;
}


public String getTemplet(){
    return templet;
}


}