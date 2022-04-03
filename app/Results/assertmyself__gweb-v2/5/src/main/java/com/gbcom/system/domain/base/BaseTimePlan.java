package com.gbcom.system.domain.base;
 import com.gbcom.system.domain.TimePlan;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.regex.Pattern;
public class BaseTimePlan implements Serializable{

 private  Long id;

 private  Integer ownerType;

 private  Integer type;

 private  Integer year;

 private  Integer month;

 private  Integer day;

 private  Integer hour;

 private  Integer min;

 private  Integer sec;

 private  Timestamp beginTime;

 private  String repeatTime;

 private  byte[] repeatBits;

 private  String selectWeek;

 private  String selectDay;

 private  Integer intervalTime;

 private  Boolean state;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

// constructors
public BaseTimePlan() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseTimePlan(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public void setOwnerType(Integer ownerType){
    this.ownerType = ownerType;
}


public void setSelectDay(String selectDay){
    this.selectDay = selectDay;
    if (null == repeatBits) {
        repeatBits = new byte[4];
        repeatBits[0] = 0x00;
        repeatBits[1] = 0x00;
        repeatBits[2] = 0x00;
        repeatBits[3] = 0x00;
    }
    if (TimePlan.TIME_PLAN_TYPE_MONTH == this.type) {
        if (null != selectDay && !selectDay.isEmpty()) {
            String[] list = selectDay.split(",");
            repeatBits[0] = 0x00;
            repeatBits[1] = 0x00;
            repeatBits[2] = 0x00;
            repeatBits[3] = 0x00;
            int index = 0;
            int offset = 0;
            int i = 0;
            for (i = 0; i < list.length; i++) {
                int day = Integer.parseInt(list[i]);
                if (day >= 1 && day <= 31) {
                    if (day % 8 == 0) {
                        index = day / 8 - 1;
                        offset = 8 - 1;
                    } else {
                        index = day / 8;
                        offset = day % 8 - 1;
                    }
                    repeatBits[index] += java.lang.Math.pow(2, (7 - offset));
                }
            }
        }
    }
}


public Integer getIntervalTime(){
    return this.intervalTime;
}


public void setRepeatTime(String repeatTime){
    this.repeatTime = repeatTime;
    String rep = "(([0-1][0-9])|2[0-3]):[0-5][0-9]:[0-5][0-9]";
    if (Pattern.matches(rep, repeatTime)) {
        String[] splits = repeatTime.split(":");
        if (splits[0].startsWith("0")) {
            this.hour = Integer.parseInt(splits[0].substring(1));
        } else {
            this.hour = Integer.parseInt(splits[0]);
        }
        if (splits[1].startsWith("0")) {
            this.min = Integer.parseInt(splits[1].substring(1));
        } else {
            this.min = Integer.parseInt(splits[1]);
        }
        if (splits[2].startsWith("0")) {
            this.sec = Integer.parseInt(splits[2].substring(1));
        } else {
            this.sec = Integer.parseInt(splits[2]);
        }
    } else {
    // 匹配失败
    }
}


public String getSelectWeek(){
    return this.selectWeek;
}


public java.lang.Long getId(){
    return id;
}


public void setSec(Integer sec){
    this.sec = sec;
}


public void setMin(Integer min){
    this.min = min;
}


public Integer getSec(){
    return this.sec;
}


public Integer getMin(){
    return this.min;
}


public void setDay(Integer day){
    this.day = day;
}


public void setId(java.lang.Long id){
    this.id = id;
}


public Integer getHour(){
    return this.hour;
}


public void setBeginTime(Timestamp beginTime){
    this.beginTime = beginTime;
    if (TimePlan.TIME_PLAN_TYPE_ONE == this.type || TimePlan.TIME_PLAN_TYPE_MINUTE == this.type) {
        // SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(df.parse(beginTime));
        calendar.setTime(beginTime);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.min = calendar.get(Calendar.MINUTE);
        this.sec = calendar.get(Calendar.SECOND);
    }
}


public Integer getMonth(){
    return this.month;
}


public Timestamp getBeginTime(){
    /*if(null == beginTime) {
            if (TimePlan.TIME_PLAN_TYPE_ONE == this.type||
                TimePlan.TIME_PLAN_TYPE_MINUTE == this.type) {
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.YEAR, this.year);
                calendar.set(Calendar.MONTH, this.month-1);
                calendar.set(Calendar.DAY_OF_MONTH, this.day);
                calendar.set(Calendar.HOUR, this.hour);
                calendar.set(Calendar.MINUTE, this.min);
                calendar.set(Calendar.SECOND, this.sec);

                ////SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //this.beginTime = sdf.format(calendar);
                this.beginTime = new java.sql.Timestamp(calendar.getTimeInMillis());
            }
        } */
    return this.beginTime;
}


public void setIntervalTime(Integer intervalTime){
    this.intervalTime = intervalTime;
}


public String getRepeatTime(){
    /*if (null == repeatTime ||
            repeatTime.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            if (this.hour<10) {
                buffer.append("0");
            }
            buffer.append(this.hour);
            buffer.append(":");
            if (this.min<10) {
                buffer.append("0");
            }
            buffer.append(this.min);
            buffer.append(":");
            if (this.sec<10) {
                buffer.append("0");
            }
            buffer.append(this.sec);

            this.repeatTime = buffer.toString();
        }*/
    return this.repeatTime;
}


public void setSelectWeek(String selectWeek){
    this.selectWeek = selectWeek;
    if (null == repeatBits) {
        repeatBits = new byte[4];
        repeatBits[0] = 0x00;
        repeatBits[1] = 0x00;
        repeatBits[2] = 0x00;
        repeatBits[3] = 0x00;
    }
    if (TimePlan.TIME_PLAN_TYPE_WEEK == this.type) {
        if (null != selectWeek && !selectWeek.isEmpty()) {
            String[] list = selectWeek.split(",");
            repeatBits[0] = 0x00;
            repeatBits[1] = 0x00;
            repeatBits[2] = 0x00;
            repeatBits[3] = 0x00;
            int index = 0;
            int offset = 0;
            int i = 0;
            for (i = 0; i < list.length; i++) {
                int week = Integer.parseInt(list[i]);
                if (week >= 1 && week <= 7) {
                    index = 0;
                    offset = week - 1;
                    repeatBits[index] += java.lang.Math.pow(2, (7 - offset));
                }
            }
        }
    }
}


public void setMonth(Integer month){
    this.month = month;
}


public byte[] getRepeatBits(){
    return repeatBits;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public void setType(Integer type){
    this.type = type;
}


public Integer getOwnerType(){
    return this.ownerType;
}


public void setYear(Integer year){
    this.year = year;
}


public void setHour(Integer hour){
    this.hour = hour;
}


public Integer getDay(){
    return this.day;
}


public String getSelectDay(){
    return this.selectDay;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public Boolean getState(){
    return this.state;
}


public Integer getYear(){
    return this.year;
}


public Integer getType(){
    return this.type;
}


public void setRepeatBits(byte[] repeatBits){
    this.repeatBits = repeatBits;
}


public void setState(Boolean state){
    this.state = state;
}


public void setUpdateTime(java.sql.Timestamp updateTime){
    this.updateTime = updateTime;
}


public void initialize(){
}


}