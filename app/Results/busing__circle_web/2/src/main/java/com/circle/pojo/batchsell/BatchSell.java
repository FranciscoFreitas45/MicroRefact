package com.circle.pojo.batchsell;
 import java.text.ParseException;
import java.util.List;
import com.circle.pojo.good.GoodBean;
import com.xwtec.xwserver.util.DateUtils;
import com.xwtec.xwserver.util.StringUtil;
public class BatchSell {

 private  int id;

 private  String batchName;

 private  String startTime;

 private  String endTime;

 private  String shipTime;

 private  String status;

 private  int remainderSeconds;

 private  List<GoodBean> goods;


public void setBatchName(String batchName){
    this.batchName = batchName;
}


public int getId(){
    return id;
}


public void main(String[] args){
    BatchSell bs = new BatchSell();
    bs.setEndTime("2015/04/30 09:41:00");
    System.out.println(bs.getRemainderSeconds());
}


public void setShipTime(String shipTime){
    this.shipTime = shipTime;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public void setRemainderSeconds(int remainderSeconds){
    this.remainderSeconds = remainderSeconds;
}


public String getEndTime(){
    return endTime;
}


public void setEndTime(String endTime){
    this.endTime = endTime;
}


public String getShipTime(){
    return shipTime;
}


public void setId(int id){
    this.id = id;
}


public String getStartTime(){
    return startTime;
}


public void setStartTime(String startTime){
    this.startTime = startTime;
}


public String getBatchName(){
    return batchName;
}


public List<GoodBean> getGoods(){
    return goods;
}


public int getRemainderSeconds(){
    long cuurent = System.currentTimeMillis();
    long end = 0;
    try {
        if (!StringUtil.isEmpty(getEndTime())) {
            end = DateUtils.parse(getEndTime(), "yyyy-MM-dd HH:mm:ss").getTime();
        }
    } catch (ParseException e) {
        e.printStackTrace();
    }
    long remainder = end - cuurent;
    remainderSeconds = Integer.parseInt((remainder <= 0 ? 0 : remainder / 1000) + "");
    return remainderSeconds;
}


public void setGoods(List<GoodBean> goods){
    this.goods = goods;
}


}