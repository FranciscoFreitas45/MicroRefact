package org.jeecgframework.core.common.dao;
 import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.springframework.dao.DataAccessException;
public interface IGenericBaseCommonDao {


public Map<String,Object> findOneForJdbc(String sql,Object objs)
;

public List<T> findByQueryString(String hql)
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

public void delete(T entitie)
;

public List<T> findListbySql(String query)
;

public T findUniqueByProperty(Class<T> entityClass,String propertyName,Object value)
;

public PageList getPageListBySql(HqlQuery hqlQuery,boolean needParameter)
;

public List<T> findByProperty(Class<T> entityClass,String propertyName,Object value)
;

public PageList getPageList(HqlQuery hqlQuery,boolean needParameter)
;

public List<T> executeProcedure(String procedureSql,Object params)
;

public T get(Class<T> entityName,Serializable id)
;

public int updateBySqlString(String sql)
;

public Long getCountForJdbc(String sql)
;

public void updateEntitie(T pojo)
;

public void saveOrUpdate(T entity)
;

public List<T> findObjForJdbc(String sql,int page,int rows,Class<T> clazz)
;

public Map<Object,Object> getHashMapbyQuery(String query)
;

public List<T> findByDetached(DetachedCriteria dc)
;

public Integer executeHql(String hql)
;

public T getEntity(Class entityName,Serializable id)
;

public List findByExample(String entityName,Object exampleEntity)
;

public Long getCountForJdbcParam(String sql,Object[] objs)
;

public List<DBTable> getAllDbTableName()
;

public List<T> findHql(String hql,Object param)
;

public void batchSave(List<T> entitys)
;

public void updateEntityById(Class entityName,Serializable id)
;

public DataTableReturn getDataTableReturn(CriteriaQuery cq,boolean isOffset)
;

public List<T> pageList(DetachedCriteria dc,int firstResult,int maxResult)
;

public T singleResult(String hql)
;

public Session getSession()
;

public void deleteAllEntitie(Collection<T> entities)
;

public Integer executeSql(String sql,Map<String,Object> param)
;

}