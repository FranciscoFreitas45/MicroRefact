package org.jeecgframework.core.common.service.impl;
 import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DTO.Autocomplete;
@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService{

 public  ICommonDao commonDao;


@Transactional(readOnly = true)
public Map<String,Object> findOneForJdbc(String sql,Object objs){
    return commonDao.findOneForJdbc(sql, objs);
}


@Transactional(readOnly = true)
public List<T> findByQueryString(String hql){
    return commonDao.findByQueryString(hql);
}


@Transactional(readOnly = true)
public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel){
    return commonDao.treegrid(all, treeGridModel);
}


@Transactional(readOnly = true)
public Integer getAllDbTableSize(){
    return commonDao.getAllDbTableSize();
}


@Transactional(readOnly = true)
public List<T> findByPropertyisOrder(Class<T> entityClass,String propertyName,Object value,boolean isAsc){
    return commonDao.findByPropertyisOrder(entityClass, propertyName, value, isAsc);
}


public Serializable save(T entity){
    return commonDao.save(entity);
}


@Transactional(readOnly = true)
public List<Map<String,Object>> findForJdbc(String sql,Object objs){
    return commonDao.findForJdbc(sql, objs);
}


@Transactional(readOnly = true)
public void getDataGridReturn(CriteriaQuery cq,boolean isOffset){
    commonDao.getDataGridReturn(cq, isOffset);
// update-end--Author:scott  Date:20170908 for：TASK #1756 【性能优化】目前分页方法返回对象没有使用，创建对象无用还占内存，暂时注释---
}


@Transactional(readOnly = true)
public List<T> loadAll(Class<T> entityClass){
    return commonDao.loadAll(entityClass);
}


@Transactional(readOnly = true)
public List<T> getListByCriteriaQuery(CriteriaQuery cq,Boolean ispage){
    return commonDao.getListByCriteriaQuery(cq, ispage);
}


public Object executeSqlReturnKey(String sql,Map<String,Object> param){
    return commonDao.executeSqlReturnKey(sql, param);
}


public void deleteEntityById(Class entityName,Serializable id){
    commonDao.deleteEntityById(entityName, id);
}


@Transactional(readOnly = true)
public List<Map<String,Object>> findForJdbcParam(String sql,int page,int rows,Object objs){
    return commonDao.findForJdbcParam(sql, page, rows, objs);
}


public void delete(T entity){
    commonDao.delete(entity);
}


@Transactional(readOnly = true)
public List<T> findListbySql(String query){
    return commonDao.findListbySql(query);
}


@Transactional(readOnly = true)
public T findUniqueByProperty(Class<T> entityClass,String propertyName,Object value){
    return commonDao.findUniqueByProperty(entityClass, propertyName, value);
}


@Transactional(readOnly = true)
public PageList getPageListBySql(HqlQuery hqlQuery,boolean isToEntity){
    return commonDao.getPageListBySql(hqlQuery, isToEntity);
}


public HttpServletResponse createXml(ImportFile importFile){
    return commonDao.createXml(importFile);
}


@Transactional(readOnly = true)
public List<T> findByProperty(Class<T> entityClass,String propertyName,Object value){
    return commonDao.findByProperty(entityClass, propertyName, value);
}


@Transactional(readOnly = true)
public PageList getPageList(HqlQuery hqlQuery,boolean needParameter){
    return commonDao.getPageList(hqlQuery, needParameter);
}


@Resource
public void setCommonDao(ICommonDao commonDao){
    this.commonDao = commonDao;
}


@Transactional(readOnly = true)
public List<T> getList(Class clas){
    return commonDao.loadAll(clas);
}


public List<T> executeProcedure(String procedureSql,Object params){
    return this.commonDao.executeProcedure(procedureSql, params);
}


@Transactional(readOnly = true)
public T get(Class<T> class1,Serializable id){
    return commonDao.get(class1, id);
}


public int updateBySqlString(String sql){
    return commonDao.updateBySqlString(sql);
}


@Transactional(readOnly = true)
public Long getCountForJdbc(String sql){
    return commonDao.getCountForJdbc(sql);
}


public void updateEntitie(T pojo){
    commonDao.updateEntitie(pojo);
}


public void saveOrUpdate(T entity){
    commonDao.saveOrUpdate(entity);
}


public T uploadFile(UploadFile uploadFile){
    return commonDao.uploadFile(uploadFile);
}


@Transactional(readOnly = true)
public List<T> findObjForJdbc(String sql,int page,int rows,Class<T> clazz){
    return commonDao.findObjForJdbc(sql, page, rows, clazz);
}


@Transactional(readOnly = true)
public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree){
    return commonDao.comTree(all, comboTree);
}


@Transactional(readOnly = true)
public List<T> findByDetached(DetachedCriteria dc){
    return this.commonDao.findByDetached(dc);
}


@Transactional(readOnly = true)
public T getEntity(Class entityName,Serializable id){
    return commonDao.getEntity(entityName, id);
}


@Transactional(readOnly = true)
public List findByExample(String entityName,Object exampleEntity){
    return commonDao.findByExample(entityName, exampleEntity);
}


@Transactional(readOnly = true)
public Long getCountForJdbcParam(String sql,Object objs){
    return commonDao.getCountForJdbcParam(sql, objs);
}


@Transactional(readOnly = true)
public List<DBTable> getAllDbTableName(){
    return commonDao.getAllDbTableName();
}


@Transactional(readOnly = true)
public List<T> findHql(String hql,Object param){
    return this.commonDao.findHql(hql, param);
}


public void batchSave(List<T> entitys){
    this.commonDao.batchSave(entitys);
}


public void parserXml(String fileName){
    commonDao.parserXml(fileName);
}


@Transactional(readOnly = true)
public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile){
    return commonDao.viewOrDownloadFile(uploadFile);
}


@Transactional(readOnly = true)
public List<T> getAutoList(Autocomplete autocomplete){
    StringBuffer sb = new StringBuffer("");
    for (String searchField : autocomplete.getSearchField().split(",")) {
        sb.append("  or " + searchField + " like '%" + autocomplete.getTrem() + "%' ");
    }
    String hql = "from " + autocomplete.getEntityName() + " where 1!=1 " + sb.toString();
    return commonDao.getSession().createQuery(hql).setFirstResult(autocomplete.getCurPage() - 1).setMaxResults(autocomplete.getMaxRows()).list();
}


@Transactional(readOnly = true)
public DataTableReturn getDataTableReturn(CriteriaQuery cq,boolean isOffset){
    return commonDao.getDataTableReturn(cq, isOffset);
}


@Transactional(readOnly = true)
public List<T> pageList(DetachedCriteria dc,int firstResult,int maxResult){
    return this.commonDao.pageList(dc, firstResult, maxResult);
}


@Transactional(readOnly = true)
public T singleResult(String hql){
    return commonDao.singleResult(hql);
}


public Session getSession(){
    return commonDao.getSession();
}


@Transactional(readOnly = true)
public List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in,boolean recursive){
    return commonDao.ComboTree(all, comboTreeModel, in, recursive);
}


public void deleteAllEntitie(Collection<T> entities){
    commonDao.deleteAllEntitie(entities);
}


public Integer executeSql(String sql,Map<String,Object> param){
    return commonDao.executeSql(sql, param);
}


}