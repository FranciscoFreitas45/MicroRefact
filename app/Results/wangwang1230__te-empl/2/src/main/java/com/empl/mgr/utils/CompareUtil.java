package com.empl.mgr.utils;
 import java.io.Serializable;
public class CompareUtil implements Serializable{

 private  long serialVersionUID;


public boolean isEmpty(Object obj){
    if (obj == null)
        return true;
    return false;
}


public long isEmptyLong(Long val){
    if (val == null)
        return 0;
    return val;
}


public boolean isNotEmpty(Object obj){
    if (obj != null)
        return true;
    return false;
}


public int isEmptyInteger(Integer val){
    if (val == null)
        return 0;
    return val;
}


}