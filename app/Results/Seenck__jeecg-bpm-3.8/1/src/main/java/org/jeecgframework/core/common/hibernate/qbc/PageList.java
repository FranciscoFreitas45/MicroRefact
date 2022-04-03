package org.jeecgframework.core.common.hibernate.qbc;
 import java.util.List;
@SuppressWarnings("unchecked")
public class PageList {

 private  int curPageNO;

 private  int offset;

 private  String toolBar;

 private  int count;

 private  List resultList;

public PageList() {
}/**
 * 不使用分页标签的初始化构造方法
 * @param resultList
 * @param toolBar
 * @param offset
 * @param curPageNO
 * @param count
 */
public PageList(List resultList, String toolBar, int offset, int curPageNO, int count) {
    this.curPageNO = curPageNO;
    this.offset = offset;
    this.toolBar = toolBar;
    this.resultList = resultList;
    this.count = count;
}/**
 * 使用分页标签的初始化构造方法
 * @param resultList
 * @param toolBar
 * @param offset
 * @param curPageNO
 * @param count
 */
public PageList(CriteriaQuery cq, List resultList, int offset, int curPageNO, int count) {
    this.curPageNO = curPageNO;
    this.offset = offset;
    this.resultList = resultList;
    this.count = count;
}public PageList(HqlQuery cq, List resultList, int offset, int curPageNO, int count) {
    this.curPageNO = curPageNO;
    this.offset = offset;
    this.resultList = resultList;
    this.count = count;
}
public int getCurPageNO(){
    return curPageNO;
}


public List<T> getResultList(){
    return resultList;
}


public void setResultList(List resultList){
    this.resultList = resultList;
}


public void setOffset(int offset){
    this.offset = offset;
}


public int getOffset(){
    return offset;
}


public void setCurPageNO(int curPageNO){
    this.curPageNO = curPageNO;
}


public String getToolBar(){
    return toolBar;
}


public int getCount(){
    return count;
}


}