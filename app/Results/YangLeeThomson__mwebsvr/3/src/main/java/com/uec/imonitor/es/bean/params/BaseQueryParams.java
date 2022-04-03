package com.uec.imonitor.es.bean.params;
 import java.util.List;
public class BaseQueryParams {

 private  String[] indexArray;

 private  String[] typeArray;

 private  int start;

 private  int size;

 private  List<SortParams> sortList;

/**
 * <br/>Description:无参构造函数
 * <p>Author:jlchen/陈金梁</p>
 */
public BaseQueryParams() {
}/**
 * <br/>Description: 带参构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param start 起始条
 * @param size 返回条数
 * @param sortField 排序字段
 * @param sort 排序方式，desc，降序、asc，升序
 */
public BaseQueryParams(int start, int size, List<SortParams> sortList) {
    this.size = size;
    this.start = start;
    this.sortList = sortList;
}
public int getSize(){
    return size;
}


public void setSortList(List<SortParams> sortList){
    this.sortList = sortList;
}


public void setSize(int size){
    this.size = size;
}


public String[] getTypeArray(){
    return typeArray;
}


public void setTypeArray(String[] typeArray){
    this.typeArray = typeArray;
}


public int getStart(){
    return start;
}


public void setStart(int start){
    this.start = start;
}


public List<SortParams> getSortList(){
    return sortList;
}


public String[] getIndexArray(){
    return indexArray;
}


public void setIndexArray(String[] indexArray){
    this.indexArray = indexArray;
}


}