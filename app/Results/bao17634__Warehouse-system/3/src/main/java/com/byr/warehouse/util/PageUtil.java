package com.byr.warehouse.util;
 public class PageUtil {


public int getTotalPage(int totalsize,int pagesize){
    int total;
    if (totalsize % pagesize == 0) {
        total = totalsize / pagesize;
    } else {
        total = (totalsize / pagesize) + 1;
    }
    return total;
}


}