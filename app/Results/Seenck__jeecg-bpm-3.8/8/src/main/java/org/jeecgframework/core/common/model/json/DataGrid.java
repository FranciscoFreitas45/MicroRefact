package org.jeecgframework.core.common.model.json;
 import java.util.List;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
public class DataGrid {

 private  int page;

 private  int rows;

 private  String sort;

 private  String order;

 private  String field;

 private  String treefield;

 private  List results;

 private  int total;

 private  String footer;

 private  String sqlbuilder;

 private  String dataStyle;


public void setTotal(int total){
    this.total = total;
}


public void setField(String field){
    this.field = field;
}


public String getFooter(){
    return footer;
}


public int getPage(){
    return page;
}


public String getField(){
    return field;
}


public void clear(){
    if (results != null) {
        results.clear();
        results = null;
    }
    // update-begin--Author:scott  Date:20170830 for：TASK #1756 【性能优化】分页查询存在写法问题，性能 CriteriaQuery cq 清空---
    field = null;
    treefield = null;
// update-end--Author:scott  Date:20170830 for：TASK #1756 【性能优化】分页查询存在写法问题，性能 CriteriaQuery cq 清空---
}


public String getDataStyle(){
    return dataStyle;
}


public void setDataStyle(String dataStyle){
    this.dataStyle = dataStyle;
}


public int getRows(){
    if (ContextHolderUtils.getRequest() != null && ResourceUtil.getParameter("rows") != null) {
        return rows;
    }
    return 10000;
}


public void setSort(String sort){
    this.sort = sort;
}


public String getSort(){
    return sort;
}


public void setOrder(String order){
    this.order = order;
}


public void setRows(int rows){
    this.rows = rows;
}


public String getSqlbuilder(){
    return sqlbuilder;
}


public void setSqlbuilder(String sqlbuilder){
    // update-begin-Author:LiShaoQing -- date:20171026 -- for:多条件动态查询判断小于等于----
    if (sqlbuilder.indexOf("≤") > 0) {
        sqlbuilder = sqlbuilder.replace("≤", "<=");
    }
    // update-end-Author:LiShaoQing -- date:20171026 -- for:多条件动态查询判断小于等于----
    this.sqlbuilder = sqlbuilder;
}


public String getTreefield(){
    return treefield;
}


public void setFooter(String footer){
    this.footer = footer;
}


public String getOrder(){
    return order;
}


public List getResults(){
    return results;
}


public int getTotal(){
    return total;
}


public void setTreefield(String treefield){
    this.treefield = treefield;
}


public void setResults(List results){
    this.results = results;
}


public void setPage(int page){
    this.page = page;
}


}