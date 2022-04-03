package com.DTO;
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


public int getId(){
    return id;
}


public String getStatus(){
    return status;
}


public String getEndTime(){
    return endTime;
}


public String getShipTime(){
    return shipTime;
}


public String getStartTime(){
    return startTime;
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


}