package com.sobey.framework.utils.DateProvider;
 import java.util.Date;
public class ConfigurableDateProvider implements DateProvider{

 private  Date date;

public ConfigurableDateProvider(Date date) {
    this.date = date;
}
@Override
public Date getDate(){
    return date;
}


}