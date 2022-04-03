package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

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
public CriterionList getJqcriterionList(){
    return jqcriterionList;
}


public CriterionList getCriterionList(){
    return criterionList;
}


public Map<String,Object> getOrdermap(){
    return ordermap;
}


public String getField(){
    return field;
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


public int getTotal(){
    return total;
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


public Integer getCurPage(){
    return curPage;
}


public int getIsUseimage(){
    return isUseimage;
}


public String getMyAction(){
    return myAction;
}


public DataTables getDataTables(){
    return dataTables;
}


public Criterion getor(Criterion c1,Criterion c2){
    return Restrictions.or(c1, c2);
}


public String getMyForm(){
    return myForm;
}


public DetachedCriteria getDetachedCriteria(){
    return detachedCriteria;
}


public DataGrid getDataGrid(){
    return dataGrid;
}


public List<?> getResults(){
    return results;
}


public void add(){
    for (int i = 0; i < getCriterionList().size(); i++) {
        add(getCriterionList().getParas(i));
    }
    getCriterionList().removeAll(getCriterionList());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))

;
restTemplate.put(builder.toUriString(),null);
}


}