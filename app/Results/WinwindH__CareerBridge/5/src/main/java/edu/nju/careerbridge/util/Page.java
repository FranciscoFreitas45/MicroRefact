package edu.nju.careerbridge.util;
 import java.util.List;
public class Page {

 private  int page;

 private  int size;

 private  String order;

 private  int totalCount;

 private  List<T> result;

public Page() {
}public Page(int page, int size, String order, int totalCount, List<T> result) {
    this.page = page;
    this.size = size;
    this.order = order;
    this.totalCount = totalCount;
    this.result = result;
}
public void setOrder(String order){
    this.order = order;
}


public void setTotalCount(int totalCount){
    this.totalCount = totalCount;
}


public int getSize(){
    return size;
}


public void setSize(int size){
    this.size = size;
}


public void setResult(List<T> result){
    this.result = result;
}


public int getPage(){
    return page;
}


public String getOrder(){
    return order;
}


public List<T> getResult(){
    return result;
}


public int getTotalCount(){
    return totalCount;
}


public void setPage(int page){
    this.page = page;
}


}