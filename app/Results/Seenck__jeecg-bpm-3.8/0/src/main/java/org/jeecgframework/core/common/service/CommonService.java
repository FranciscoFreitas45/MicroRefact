package org.jeecgframework.core.common.service;
 import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.springframework.dao.DataAccessException;
public interface CommonService {


public Map<String,Object> findOneForJdbc(String sql,Object objs)
;

public List<T> findByQueryString(String hql)
;

public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel)
;

public Integer getAllDbTableSize()
;

public List<T> findByPropertyisOrder(Class<T> entityClass,String propertyName,Object value,boolean isAsc)
;

public Serializable save(T entity)
;

public List<Map<String,Object>> findForJdbc(String sql,int page,int rows)
;

public void getDataGridReturn(CriteriaQuery cq,boolean isOffset)
;

public List<T> loadAll(Class<T> entityClass)
;

public List<T> getListByCriteriaQuery(CriteriaQuery cq,Boolean ispage)
;

public Object executeSqlReturnKey(String sql,Map<String,Object> param)
;

public void deleteEntityById(Class entityName,Serializable id)
;

public List<Map<String,Object>> findForJdbcParam(String sql,int page,int rows,Object objs)
;

public void delete(T entity)
;

public List<T> findListbySql(String query)
;

public T findUniqueByProperty(Class<T> entityClass,String propertyName,Object value)
;

public PageList getPageListBySql(HqlQuery hqlQuery,boolean isToEntity)
;

public HttpServletResponse createXml(ImportFile importFile)
;

public List<T> findByProperty(Class<T> entityClass,String propertyName,Object value)
;

public PageList getPageList(HqlQuery hqlQuery,boolean needParameter)
;

public List<T> getList(Class clas)
;

public List<T> executeProcedure(String procedureSql,Object params)
;

public T get(Class<T> class1,Serializable id)
;

public int updateBySqlString(String sql)
;

public Long getCountForJdbc(String sql)
;

public void updateEntitie(T pojo)
;

public void saveOrUpdate(T entity)
;

public T uploadFile(UploadFile uploadFile)
;

public List<T> findObjForJdbc(String sql,int page,int rows,Class<T> clazz)
;

public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree)
;

public List<T> findByDetached(DetachedCriteria dc)
;

public T getEntity(Class entityName,Serializable id)
;

public List findByExample(String entityName,Object exampleEntity)
;

public Long getCountForJdbcParam(String sql,Object objs)
;

public List<DBTable> getAllDbTableName()
;

public List<T> findHql(String hql,Object param)
;

public void batchSave(List<T> entitys)
;

public void parserXml(String fileName)
;

public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)
;

public List<T> getAutoList(Autocomplete autocomplete)
;

public DataTableReturn getDataTableReturn(CriteriaQuery cq,boolean isOffset)
;

public List<T> pageList(DetachedCriteria dc,int firstResult,int maxResult)
;

public T singleResult(String hql)
;

public Session getSession()
;

public List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in,boolean recursive)
;

public void deleteAllEntitie(Collection<T> entities)
;

public Integer executeSql(String sql,Map<String,Object> param)
;

}