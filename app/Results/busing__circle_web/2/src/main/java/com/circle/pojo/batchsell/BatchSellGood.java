package com.circle.pojo.batchsell;
 import java.text.ParseException;
import com.xwtec.xwserver.util.DateUtils;
import com.xwtec.xwserver.util.StringUtil;
public class BatchSellGood {

 private  int id;

 private  Integer batchId;

 private  Integer goodId;


public Integer getGoodId(){
    return goodId;
}


public void setGoodId(Integer goodId){
    this.goodId = goodId;
}


public Integer getBatchId(){
    return batchId;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


public void setBatchId(Integer batchId){
    this.batchId = batchId;
}


}