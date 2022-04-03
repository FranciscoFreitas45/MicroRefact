package com.zis.youzan.response.ResultJsonToItemList;
 import java.util.List;
import com.alibaba.fastjson.annotation.JSONField;
public class Response {

@JSONField(name = "total_results")
 private  Long totalResults;

@JSONField(name = "items")
 private  List<Item> items;


public List<Item> getItems(){
    return items;
}


public void setItems(List<Item> items){
    this.items = items;
}


public void setTotalResults(Long totalResults){
    this.totalResults = totalResults;
}


@Override
public String toString(){
    return "Response [totalResults=" + totalResults + ", items=" + items + "]";
}


public Long getTotalResults(){
    return totalResults;
}


}