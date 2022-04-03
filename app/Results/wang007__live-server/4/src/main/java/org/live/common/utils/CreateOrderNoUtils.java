package org.live.common.utils;
 import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class CreateOrderNoUtils {


public String getFixLenthString(int strLength){
    Random rm = new Random();
    // 获得随机数
    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
    // 将获得的获得随机数转化为字符串
    String fixLenthString = String.valueOf(pross);
    // 返回固定的长度的随机数
    return fixLenthString.substring(1, strLength + 1);
}


public String getCreateOrderNoByTime(){
    // 数字代表几位随机数
    return CreateOrderNoUtils.getTime() + CreateOrderNoUtils.getFixLenthString(3);
}


public String getTime(){
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    String time = format.format(date);
    return time;
}


public String getDate(){
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    String time = format.format(date);
    return time;
}


public void main(String[] args){
    // 20170421014800020 20170421014829586
    System.out.println(getCreateOrderNoByTime());
}


public String getCreateOrderNo(){
    // 数字代表几位随机数
    return CreateOrderNoUtils.getDate() + CreateOrderNoUtils.getFixLenthString(6);
}


}