package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_callcenter_routeritem")
@Proxy(lazy = true)
public class RouteItem {

 private  long serialVersionUID;

 private  String id;

 private  String type;

 private  boolean child;

 private  String name;

 private  String parentid;

 private  String orgi;

 private  Date createtime;

 private  Date updatetime;

 private  String creater;

 private  String action;

 private  int sortindex;

 private  String param;

 private  String value;

 private  String hostid;

 private  String routeid;

 private  String data;

 private  String field;

 private  String expression;

 private  String onbreak;

 private  String ctype;

 private  String year;

 private  String yday;

 private  String mon;

 private  String mday;

 private  String week;

 private  String mweek;

 private  String wday;

 private  String hour;

 private  String minute;

 private  String minuteofday;

 private  String timeofday;

public RouteItem() {
}public RouteItem(String type, String field, String expression, String onbreak) {
    this.type = type;
    this.field = field;
    this.expression = expression;
    this.onbreak = onbreak;
}public RouteItem(String type, String action, String data) {
    this.type = type;
    this.action = action;
    this.data = data;
    if (!StringUtils.isBlank(action) && action.equalsIgnoreCase("set") && !StringUtils.isBlank(data)) {
        this.param = data.substring(0, data.indexOf("="));
        this.value = data.substring(data.indexOf("=") + 1);
    }
}
public String getName(){
    return name;
}


public String getMinuteofday(){
    return minuteofday;
}


public String getMday(){
    return mday;
}


public String getRouteid(){
    return routeid;
}


public void setMinuteofday(String minuteofday){
    this.minuteofday = minuteofday;
}


public void setCtype(String ctype){
    this.ctype = ctype;
}


public void setChild(boolean child){
    this.child = child;
}


public void setWday(String wday){
    this.wday = wday;
}


public String getMinute(){
    return minute;
}


public void setId(String id){
    this.id = id;
}


public boolean isChild(){
    return child;
}


public String getCtype(){
    return ctype;
}


public String getParam(){
    return param;
}


public void setWeek(String week){
    this.week = week;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getField(){
    return field;
}


public void setHour(String hour){
    this.hour = hour;
}


public String getExpression(){
    return expression;
}


public String getYear(){
    return year;
}


public String getType(){
    return type;
}


public void setRouteid(String routeid){
    this.routeid = routeid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getOnbreak(){
    return onbreak;
}


public String getWday(){
    return wday;
}


public void setTimeofday(String timeofday){
    this.timeofday = timeofday;
}


public void setName(String name){
    this.name = name;
}


public void setField(String field){
    this.field = field;
}


public void setAction(String action){
    this.action = action;
}


public void setMon(String mon){
    this.mon = mon;
}


public void setParam(String param){
    this.param = param;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setMinute(String minute){
    this.minute = minute;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public Date getCreatetime(){
    return createtime;
}


public void setYday(String yday){
    this.yday = yday;
}


public String getHostid(){
    return hostid;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public String getHour(){
    return hour;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setMday(String mday){
    this.mday = mday;
}


public String getMon(){
    return mon;
}


public String getData(){
    return data;
}


public String getParentid(){
    return parentid;
}


public void setOnbreak(String onbreak){
    this.onbreak = onbreak;
}


public void setData(String data){
    this.data = data;
}


public String getAction(){
    return action;
}


public void setType(String type){
    this.type = type;
}


public void setYear(String year){
    this.year = year;
}


public String getWeek(){
    return week;
}


public String getValue(){
    return value;
}


public String getTimeofday(){
    return timeofday;
}


public void setValue(String value){
    this.value = value;
}


public String getOrgi(){
    return orgi;
}


public void setExpression(String expression){
    this.expression = expression;
}


public void setMweek(String mweek){
    this.mweek = mweek;
}


public String getYday(){
    return yday;
}


public String getMweek(){
    return mweek;
}


public int getSortindex(){
    return sortindex;
}


}