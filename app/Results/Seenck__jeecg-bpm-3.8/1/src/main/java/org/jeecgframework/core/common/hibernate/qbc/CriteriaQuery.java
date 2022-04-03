package org.jeecgframework.core.common.hibernate.qbc;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.datatable.SortInfo;
import Interface.DataGrid;
public class CriteriaQuery {

 private  long serialVersionUID;

 private  int curPage;

 private  int pageSize;

 private  String myAction;

 private  String myForm;

 private  CriterionList criterionList;

 private  CriterionList jqcriterionList;

 private  int isUseimage;

 private  DetachedCriteria detachedCriteria;

 private  Map<String,Object> map;

 private  Map<String,Object> ordermap;

 private  boolean flag;

 private  String field;

 private  Class<?> entityClass;

 private  List<?> results;

 private  int total;

 private  List<String> alias;

 private  DataGrid dataGrid;

 private  DataTables dataTables;

public CriteriaQuery() {
}public CriteriaQuery(Class<?> c) {
    this.detachedCriteria = DetachedCriteria.forClass(c);
    this.map = new HashMap<String, Object>();
    // update--begin--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
    this.ordermap = new LinkedHashMap<String, Object>();
// update--end--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
}public CriteriaQuery(Class<?> c, int curPage, String myAction, String myForm) {
    this.curPage = curPage;
    this.myAction = myAction;
    this.myForm = myForm;
    this.detachedCriteria = DetachedCriteria.forClass(c);
}public CriteriaQuery(Class<?> c, int curPage, String myAction) {
    this.myAction = myAction;
    this.curPage = curPage;
    this.detachedCriteria = DetachedCriteria.forClass(c);
    this.map = new HashMap<String, Object>();
    // update--begin--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
    this.ordermap = new LinkedHashMap<String, Object>();
// update--end--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
}public CriteriaQuery(Class<?> entityClass, int curPage) {
    this.curPage = curPage;
    this.detachedCriteria = DetachedCriteria.forClass(entityClass);
    this.map = new HashMap<String, Object>();
}public CriteriaQuery(Class<?> entityClass, DataGrid dg) {
    this.curPage = dg.getPage();
    // String[] fieldstring=dg.getField().split(",");
    // this.detachedCriteria = DetachedCriteriaUtil
    // .createDetachedCriteria(c, "start", "_table",fieldstring);
    this.detachedCriteria = DetachedCriteria.forClass(entityClass);
    // Criteria criteria = null;
    this.field = dg.getField();
    this.entityClass = entityClass;
    this.dataGrid = dg;
    this.pageSize = dg.getRows();
    this.map = new HashMap<String, Object>();
    // update--begin--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
    this.ordermap = new LinkedHashMap<String, Object>();
// update--end--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
}// 【scott 20180526 删除无用代码|xwork-core】
public CriteriaQuery(Class entityClass, DataTables dataTables) {
    this.curPage = dataTables.getDisplayStart();
    String[] fieldstring = dataTables.getsColumns().split(",");
    // update-begin--Author:scott ---- Date:20170320 ----------- for：暂时注册掉，目前DataTableReturn未用，DetachedCriteriaUtil也只有此处用到------
    this.detachedCriteria = DetachedCriteria.forClass(entityClass);
    // this.detachedCriteria = DetachedCriteriaUtil.createDetachedCriteria(entityClass, "start", "_table",fieldstring);
    // update-end--Author:scott ---- Date:20170320 ----------- for：暂时注册掉，目前DataTableReturn未用，DetachedCriteriaUtil也只有此处用到------
    this.field = dataTables.getsColumns();
    this.entityClass = entityClass;
    this.dataTables = dataTables;
    this.pageSize = dataTables.getDisplayLength();
    this.map = new HashMap<String, Object>();
    // update--begin--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
    this.ordermap = new LinkedHashMap<String, Object>();
    // update--end--Author:zhangjiaqiang date:20171031 for:ordermap修订有有序列表
    addJqCriteria(dataTables);
}public CriteriaQuery(Class c, int pageSize, int curPage, String myAction, String myForm) {
    this.pageSize = pageSize;
    this.curPage = curPage;
    this.myAction = myAction;
    this.myForm = myForm;
    this.detachedCriteria = DetachedCriteria.forClass(c);
}
public void setEntityClass(Class<?> entityClass){
    this.entityClass = entityClass;
}


public void setMyForm(String myForm){
    this.myForm = myForm;
}


public void lt(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        criterionList.addPara(Restrictions.lt(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public void put(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        map.put(keyname, keyvalue);
    }
}


public CriterionList getJqcriterionList(){
    return jqcriterionList;
}


public void setResultTransformer(Class<?> class1){
    detachedCriteria.setResultTransformer(Transformers.aliasToBean(class1));
}


public void setJqcriterionList(CriterionList jqcriterionList){
    this.jqcriterionList = jqcriterionList;
}


public boolean isFlag(){
    return flag;
}


public void setDataTables(DataTables dataTables){
    this.dataTables = dataTables;
}


public void setMap(Map<String,Object> map){
    this.map = map;
}


public void setResults(List<?> results){
    this.results = results;
}


public void notEq(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        criterionList.addPara(Restrictions.ne(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public void between(String keyname,Object keyvalue1,Object keyvalue2){
    // 写入between查询条件
    Criterion c = null;
    if (oConvertUtils.isNotEmpty(keyvalue1) && oConvertUtils.isNotEmpty(keyvalue2)) {
        c = Restrictions.between(keyname, keyvalue1, keyvalue2);
    } else if (oConvertUtils.isNotEmpty(keyvalue1)) {
        c = Restrictions.ge(keyname, keyvalue1);
    } else if (oConvertUtils.isNotEmpty(keyvalue2)) {
        c = Restrictions.le(keyname, keyvalue2);
    }
    criterionList.add(c);
}


public void add(){
    for (int i = 0; i < getCriterionList().size(); i++) {
        add(getCriterionList().getParas(i));
    }
    getCriterionList().removeAll(getCriterionList());
}


public void isNotNull(String keyname){
    criterionList.addPara(Restrictions.isNotNull(keyname));
}


public CriterionList getCriterionList(){
    return criterionList;
}


public void setFlag(boolean flag){
    this.flag = flag;
}


public void like(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        // criterionList.addPara(Restrictions.like(keyname, "%" + keyvalue+ "%"));
        criterionList.addPara(Restrictions.like(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public void in(String keyname,Object[] keyvalue){
    if (keyvalue != null && keyvalue.length > 0 && keyvalue[0] != "") {
        criterionList.addPara(Restrictions.in(keyname, keyvalue));
    }
}


public void setProjection(Property property){
    detachedCriteria.setProjection(property);
}


public Map<String,Object> getOrdermap(){
    return ordermap;
}


public String getField(){
    return field;
}


public void createCriteria(String name,String value){
    detachedCriteria.createCriteria(name, value);
}


public void eq(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        criterionList.addPara(Restrictions.eq(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public Map<String,Object> getMap(){
    return map;
}


public Class<?> getEntityClass(){
    return entityClass;
}


public int getPageSize(){
    return pageSize;
}


public void judgecreateAlias(String entitys){
    String[] aliass = entitys.split("\\.");
    for (int i = 0; i < aliass.length - 1; i++) {
        createAlias(aliass[i], aliass[i]);
    }
}


public int getTotal(){
    return total;
}


public void setDataGrid(DataGrid dataGrid){
    this.dataGrid = dataGrid;
}


public void setTotal(int total){
    this.total = total;
}


public Criterion getOrCriterion(CriterionList list){
    Criterion c1 = null;
    Criterion c2 = null;
    Criterion c3 = null;
    c1 = list.getParas(0);
    for (int i = 1; i < list.size(); i++) {
        c2 = list.getParas(i);
        c3 = getor(c1, c2);
        c1 = c3;
    }
    return c3;
}


public void setField(String field){
    this.field = field;
}


public void addJqCriteria(DataTables dataTables){
    // 查询关键字
    String search = dataTables.getSearch();
    // 排序字段
    SortInfo[] sortInfo = dataTables.getSortColumns();
    // 字段
    String[] sColumns = dataTables.getsColumns().split(",");
    if (StringUtil.isNotEmpty(search)) {
        for (String string : sColumns) {
            if (string.indexOf("_") == -1) {
                jqcriterionList.addPara(Restrictions.like(string, "%" + search + "%"));
            }
        }
        add(getOrCriterion(jqcriterionList));
    }
    if (sortInfo.length > 0) {
        for (SortInfo sortInfo2 : sortInfo) {
            addOrder("" + sColumns[sortInfo2.getColumnId()] + "", sortInfo2.getSortOrder());
        }
    }
}


public void setIsUseimage(int isUseimage){
    this.isUseimage = isUseimage;
}


public void sql(String sql,Object objects,Type type){
    Restrictions.sqlRestriction(sql, objects, type);
}


public Integer getCurPage(){
    return curPage;
}


public Criterion and(Criterion c1,Criterion c2){
    return Restrictions.and(c1, c2);
}


public int getIsUseimage(){
    return isUseimage;
}


public String getMyAction(){
    return myAction;
}


public void addOrder(String ordername,SortDirection ordervalue){
    ordermap.put(ordername, ordervalue);
}


public DataTables getDataTables(){
    return dataTables;
}


public void setMyAction(String myAction){
    this.myAction = myAction;
}


public void createAlias(String name,String value){
    if (!alias.contains(name)) {
        detachedCriteria.createAlias(name, value);
        alias.add(name);
    }
}


public void ge(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        criterionList.addPara(Restrictions.ge(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public Criterion getor(Criterion c1,Criterion c2){
    return Restrictions.or(c1, c2);
}


public void or(Criterion c1,Criterion c2){
    this.detachedCriteria.add(Restrictions.or(c1, c2));
}


public String getMyForm(){
    return myForm;
}


public void clear(){
    criterionList.clear();
    jqcriterionList.clear();
    alias.clear();
    if (map != null) {
        map.clear();
    }
    if (ordermap != null) {
        ordermap.clear();
    }
    entityClass = null;
    // update-begin--Author:scott  Date:20170830 for：TASK #1756 【性能优化】分页查询存在写法问题，性能 CriteriaQuery cq 清空---
    dataGrid = null;
    dataTables = null;
    detachedCriteria = null;
    // update-end--Author:scott  Date:20170830 for：TASK #1756 【性能优化】分页查询存在写法问题，性能 CriteriaQuery cq 清空---
    criterionList = null;
    jqcriterionList = null;
    jqcriterionList = null;
    map = null;
    ordermap = null;
    alias = null;
    field = null;
}


public DetachedCriteria getDetachedCriteria(){
    return detachedCriteria;
}


public void gt(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        criterionList.addPara(Restrictions.gt(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public DataGrid getDataGrid(){
    return dataGrid;
}


public void setOrder(Map<String,Object> map){
    for (Map.Entry<String, Object> entry : map.entrySet()) {
        judgecreateAlias(entry.getKey());
        if (SortDirection.asc.equals(entry.getValue())) {
            detachedCriteria.addOrder(Order.asc(entry.getKey()));
        } else {
            detachedCriteria.addOrder(Order.desc(entry.getKey()));
        }
    }
}


public void setOrdermap(Map<String,Object> ordermap){
    this.ordermap = ordermap;
}


public void isNull(String keyname){
    criterionList.addPara(Restrictions.isNull(keyname));
}


public void setCurPage(Integer curPage){
    this.curPage = curPage;
}


public List<?> getResults(){
    return results;
}


public void le(String keyname,Object keyvalue){
    if (keyvalue != null && keyvalue != "") {
        criterionList.addPara(Restrictions.le(keyname, keyvalue));
        if (flag) {
            this.put(keyname, keyvalue);
        }
        flag = true;
    }
}


public void setDetachedCriteria(DetachedCriteria detachedCriteria){
    this.detachedCriteria = detachedCriteria;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


public void setCriterionList(CriterionList criterionList){
    this.criterionList = criterionList;
}


}