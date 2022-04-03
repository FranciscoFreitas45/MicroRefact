package com.uec.imonitor.es.bean.params;
 public class RangeParams {

 private  String field;

 private  Object start;

 private  Object end;

/**
 * <br/>Description:构造函数，默认字段间采用and连接
 * <p>Author:jlchen/陈金梁</p>
 * @param field 字段名
 * @param start 区间开始值
 * @param end 区间结束值
 */
public RangeParams(String field, Object start, Object end) {
    this.field = field;
    this.start = start;
    this.end = end;
}
public void setField(String field){
    this.field = field;
}


public Object getStart(){
    return start;
}


public void setStart(Object start){
    this.start = start;
}


public String getField(){
    return field;
}


public void setEnd(Object end){
    this.end = end;
}


public Object getEnd(){
    return end;
}


}