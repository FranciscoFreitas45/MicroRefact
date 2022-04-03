package com.uec.imonitor.es.bean.params;
 import java.util.Date;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
public class AggsHistogramParams {

 private  String aggsName;

 private  String aggsField;

 private  DateHistogramInterval interval;

 private  String timeZone;

 private  String format;

 private  Date minDate;

 private  Date maxDate;


public DateHistogramInterval getInterval(){
    return interval;
}


public String getTimeZone(){
    return timeZone;
}


public Date getMinDate(){
    return minDate;
}


public Date getMaxDate(){
    return maxDate;
}


public String getAggsField(){
    return aggsField;
}


public void setInterval(DateHistogramInterval interval){
    this.interval = interval;
}


public void setAggsField(String aggsField){
    this.aggsField = aggsField;
}


public void setFormat(String format){
    this.format = format;
}


public void setMaxDate(Date maxDate){
    this.maxDate = maxDate;
}


public void setTimeZone(String timeZone){
    this.timeZone = timeZone;
}


public String getAggsName(){
    return aggsName;
}


public String getFormat(){
    return format;
}


public void setAggsName(String aggsName){
    this.aggsName = aggsName;
}


public void setMinDate(Date minDate){
    this.minDate = minDate;
}


}