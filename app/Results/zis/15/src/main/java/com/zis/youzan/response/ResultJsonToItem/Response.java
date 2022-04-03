package com.zis.youzan.response.ResultJsonToItem;
 import com.alibaba.fastjson.annotation.JSONField;
public class Response {

@JSONField(name = "item")
 private  Item item;


public Item getItem(){
    return item;
}


@Override
public String toString(){
    return "Response [item=" + item + "]";
}


public void setItem(Item item){
    this.item = item;
}


}