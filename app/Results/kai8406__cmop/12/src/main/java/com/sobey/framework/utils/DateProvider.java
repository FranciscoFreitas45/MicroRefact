package com.sobey.framework.utils;
 import java.util.Date;
public interface DateProvider {

 public  DateProvider DEFAULT;

 private  Date date;


@Override
public Date getDate(){
    return date;
}
;

}