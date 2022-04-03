package com.gs.common.util;
 import com.gs.common.bean.Pager;
public class PagerUtil {

 public  String PAGE_SIZE;


public Pager getPager(String pageNo,String pageSize,int total){
    if (pageNo != null && pageSize != null) {
        return getPager(Integer.valueOf(pageNo), Integer.valueOf(pageSize), total);
    }
    return getPager(1, 0, total);
}


}