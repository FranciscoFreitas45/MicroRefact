package DTO;
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
public Class getClass1(){
    return class1;
}


public int getCurPage(){
    return curPage;
}


public String getMyform(){
    return myform;
}


public Object[] getParam(){
    return param;
}


public String getMyaction(){
    return myaction;
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


public DataGrid getDataGrid(){
    return dataGrid;
}


public int getPageSize(){
    return pageSize;
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


}