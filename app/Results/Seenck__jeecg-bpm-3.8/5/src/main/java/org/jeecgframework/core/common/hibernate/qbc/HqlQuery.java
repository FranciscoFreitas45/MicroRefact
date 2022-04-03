package org.jeecgframework.core.common.hibernate.qbc;
 import java.util.List;
import java.util.Map;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.model.json.DataGrid;
import Interface.DataGrid;
public class HqlQuery {

 private  int curPage;

 private  int pageSize;

 private  String myaction;

 private  String myform;

 private  String queryString;

 private  Object[] param;

 private  Type[] types;

 private  Map<String,Object> map;

 private  DataGrid dataGrid;

 private  String field;

 private  Class class1;

 private  List results;

 private  int total;

public HqlQuery(String queryString, Object[] param, Map<String, Object> map) {
    this.queryString = queryString;
    this.param = param;
    this.map = map;
}public HqlQuery(String queryString, Map<String, Object> map) {
    this.queryString = queryString;
    this.map = map;
}public HqlQuery(String myaction) {
    this.myaction = myaction;
}public HqlQuery(String myaction, String queryString, Object[] param, Type[] types) {
    this.myaction = myaction;
    this.queryString = queryString;
    this.param = param;
    this.types = types;
}public HqlQuery(Class class1, String hqlString, DataGrid dataGrid) {
    this.dataGrid = dataGrid;
    this.queryString = hqlString;
    this.pageSize = dataGrid.getRows();
    this.field = dataGrid.getField();
    this.class1 = class1;
}
public void setTotal(int total){
    this.total = total;
}


public void setField(String field){
    this.field = field;
}


public void setParam(Object[] param){
    this.param = param;
}


public Class getClass1(){
    return class1;
}


public int getCurPage(){
    return curPage;
}


public void setMyaction(String myaction){
    this.myaction = myaction;
}


public String getMyform(){
    return myform;
}


public void setMyform(String myform){
    this.myform = myform;
}


public void setMap(Map<String,Object> map){
    this.map = map;
}


public void setResults(List rsults){
    this.results = results;
}


public void setClass1(Class class1){
    this.class1 = class1;
}


public Object[] getParam(){
    return param;
}


public String getMyaction(){
    return myaction;
}


public void setQueryString(String queryString){
    this.queryString = queryString;
}


public String getField(){
    return field;
}


public String getQueryString(){
    return queryString;
}


public Map<String,Object> getMap(){
    return map;
}


public void setTypes(Type[] types){
    this.types = types;
}


public DataGrid getDataGrid(){
    return dataGrid;
}


public int getPageSize(){
    return pageSize;
}


public void setCurPage(int curPage){
    this.curPage = curPage;
}


public List getResults(){
    return results;
}


public int getTotal(){
    return total;
}


public Type[] getTypes(){
    return types;
}


public void setDataGrid(DataGrid dataGrid){
    this.dataGrid = dataGrid;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


}