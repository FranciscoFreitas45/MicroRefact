package com.cym.utils;
 import cn.craccd.sqlHelper.utils.SnowFlake;
public class SnowFlakeUtils {

 static  SnowFlake snowFlake;


public Long getId(){
    return Long.parseLong(snowFlake.nextId());
}


}