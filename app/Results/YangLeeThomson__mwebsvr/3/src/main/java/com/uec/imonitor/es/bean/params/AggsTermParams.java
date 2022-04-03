package com.uec.imonitor.es.bean.params;
 import com.uec.imonitor.common.util.ESConstantUtil;
public class AggsTermParams {

 private  String aggName;

 private  String field;

 private  String sort;

 private  int size;

/**
 * <br/>Description: 聚合构造函数，默认使用降序排列
 * <p>Author:jlchen/陈金梁</p>
 * @param aggName 聚合查询名称
 * @param field 聚合字段
 */
public AggsTermParams(String aggName, String field) {
    this.aggName = aggName;
    this.field = field;
    this.size = ESConstantUtil.DEFAULT_AGGS_NUM;
    this.sort = ESConstantUtil.SORT_DESC;
}/**
 * <br/>Description:聚合参数构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param aggName 聚合查询名称
 * @param field 聚合字段名
 * @param sort 聚合结果排序方式
 * @param size 聚合结果展示个数
 */
public AggsTermParams(String aggName, String field, String sort, int size) {
    this.aggName = aggName;
    this.field = field;
    this.sort = sort;
    this.size = size;
}
public void setSort(String sort){
    this.sort = sort;
}


public String getSort(){
    return sort;
}


public void setField(String field){
    this.field = field;
}


public int getSize(){
    return size;
}


public void setSize(int size){
    this.size = size;
}


public void setAggName(String aggName){
    this.aggName = aggName;
}


public String getField(){
    return field;
}


public String getAggName(){
    return aggName;
}


}