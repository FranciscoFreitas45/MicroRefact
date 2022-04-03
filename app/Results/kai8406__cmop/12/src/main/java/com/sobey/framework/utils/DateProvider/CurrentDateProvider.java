package com.sobey.framework.utils.DateProvider;
 import java.util.Date;
public class CurrentDateProvider implements DateProvider{


@Override
public Date getDate(){
    return new Date();
}


}