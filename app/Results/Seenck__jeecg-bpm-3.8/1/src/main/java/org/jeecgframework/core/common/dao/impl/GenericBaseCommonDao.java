package org.jeecgframework.core.common.dao.impl;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.type.Type;
import org.jeecgframework.core.annotation.JeecgEntityTitle;
import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.hibernate.qbc.PagerUtil;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.ToEntityUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;
import DTO.HqlQuery;
@SuppressWarnings("hiding")
public class GenericBaseCommonDao implements IGenericBaseCommonDao{

 private  Logger logger;

@Autowired
@Qualifier("sessionFactory")
 private  SessionFactory sessionFactory;

@Autowired
@Qualifier("jdbcTemplate")
 private  JdbcTemplate jdbcTemplate;

@Autowired
@Qualifier("namedParameterJdbcTemplate")
 private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;


public List<T> findByQueryString(String query){
    Query queryObject = getSession().createQuery(query);
    List<T> list = queryObject.list();
    // if (list.size() > 0) {
    // getSession().flush();
    // }
    return list;
}


public Integer getAllDbTableSize(){
    SessionFactory factory = getSession().getSessionFactory();
    Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
    return metaMap.size();
}


public List<T> findByPropertyisOrder(Class<T> entityClass,String propertyName,Object value,boolean isAsc){
    Assert.hasText(propertyName);
    return createCriteria(entityClass, isAsc, Restrictions.eq(propertyName, value)).list();
}


public List<Map<String,Object>> findForJdbc(String sql,Object objs){
    return this.jdbcTemplate.queryForList(sql, objs);
}


public void getDataGridReturn(CriteriaQuery cq,boolean isOffset){
    // update-end--Author:scott  Date:20170908 for：TASK #1756 【性能优化】目前分页方法返回对象都没用，创建对象无用还占内存，暂时注释---
    Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
    CriteriaImpl impl = (CriteriaImpl) criteria;
    // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
    Projection projection = impl.getProjection();
    // update-begin--Author:zhoujf  Date:20180802 for：TASK #2988 【代码生成器报错】空指针问题
    // final int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    Object allCountsObj = criteria.setProjection(Projections.rowCount()).uniqueResult();
    final int allCounts;
    if (allCountsObj == null) {
        allCounts = 0;
    } else {
        allCounts = ((Long) allCountsObj).intValue();
    }
    // update-end--Author:zhoujf  Date:20180802 for：TASK #2988 【代码生成器报错】空指针问题
    criteria.setProjection(projection);
    if (projection == null) {
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    }
    // update-begin--Author:zhoujf  Date:20170320 for：TASK #1335 【扩展】列表支持多字段排序
    // update-begin--Author:zhoujf  Date:20170302 for：TASK #1335 【扩展】列表支持多字段排序
    // update--begin--Author:zhangjiaqiang Date:20171031 for:TASK #2396 【bug】查询排序问题，确认
    Map<String, Object> ordermap = cq.getOrdermap();
    if (ordermap == null) {
        ordermap = new LinkedHashMap<String, Object>();
    }
    String sort = cq.getDataGrid().getSort();
    if (StringUtils.isNotBlank(sort)) {
        String[] sortArr = sort.split(",");
        String[] orderArr = cq.getDataGrid().getOrder().split(",");
        if (sortArr.length != orderArr.length && orderArr.length > 0) {
            for (int i = 0; i < sortArr.length; i++) {
                if (SortDirection.asc.equals(SortDirection.toEnum(orderArr[0]))) {
                    ordermap.put(sortArr[i], SortDirection.asc);
                } else {
                    ordermap.put(sortArr[i], SortDirection.desc);
                }
            }
        } else if (sortArr.length == orderArr.length) {
            for (int i = 0; i < sortArr.length; i++) {
                if (SortDirection.asc.equals(SortDirection.toEnum(orderArr[i]))) {
                    ordermap.put(sortArr[i], SortDirection.asc);
                } else {
                    ordermap.put(sortArr[i], SortDirection.desc);
                }
            }
        }
    }
    if (!ordermap.isEmpty() && ordermap.size() > 0) {
        cq.setOrder(ordermap);
    }
    // update--end--Author:zhangjiaqiang Date:20171031 for:TASK #2396 【bug】查询排序问题，确认
    // update-end--Author:zhoujf  Date:20170302 for：TASK #1335 【扩展】列表支持多字段排序
    // update-end--Author:zhoujf  Date:20170320 for：TASK #1335 【扩展】列表支持多字段排序
    // 判断是否有排序字段
    // if (!cq.getOrdermap().isEmpty()) {
    // cq.setOrder(cq.getOrdermap());
    // }
    // 每页显示数
    int pageSize = cq.getPageSize();
    // 当前页
    int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(), pageSize);
    int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
    if (isOffset) {
        // 是否分页
        criteria.setFirstResult(offset);
        criteria.setMaxResults(cq.getPageSize());
    } else {
        pageSize = allCounts;
    }
    // DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(),
    // cq.getField().split(","), cq.getClass1(), false);
    List<?> list = criteria.list();
    cq.getDataGrid().setResults(list);
    cq.getDataGrid().setTotal(allCounts);
    // update-begin--Author:scott  Date:20170830 for：TASK #1756 【性能优化】分页查询存在写法问题，性能 CriteriaQuery cq 清空---
    cq.clear();
    cq = null;
// update-end--Author:scott  Date:20170830 for：TASK #1756 【性能优化】分页查询存在写法问题，性能 CriteriaQuery cq 清空---
// update-begin--Author:scott  Date:20170908 for：TASK #1756 【性能优化】目前分页方法返回对象没有使用，创建对象无用还占内存，暂时注释---
// return new DataGridReturn(allCounts, list);
// update-end--Author:scott  Date:20170908 for：TASK #1756 【性能优化】目前分页方法返回对象没有使用，创建对象无用还占内存，暂时注释---
}


public void deleteEntityById(Class entityName,Serializable id){
    delete(get(entityName, id));
// getSession().flush();
}


public List<Map<String,Object>> findForJdbcParam(String sql,int page,int rows,Object objs){
    // 封装分页SQL
    sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
    return this.jdbcTemplate.queryForList(sql, objs);
}


public T findUniqueByProperty(Class<T> entityClass,String propertyName,Object value){
    Assert.hasText(propertyName);
    return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
}


@SuppressWarnings("unchecked")
public PageList getPageListBySql(HqlQuery hqlQuery,boolean isToEntity){
    Query query = getSession().createSQLQuery(hqlQuery.getQueryString());
    // query.setParameters(hqlQuery.getParam(), (Type[])
    // hqlQuery.getTypes());
    int allCounts = query.list().size();
    int curPageNO = hqlQuery.getCurPage();
    int offset = PagerUtil.getOffset(allCounts, curPageNO, hqlQuery.getPageSize());
    query.setFirstResult(offset);
    query.setMaxResults(hqlQuery.getPageSize());
    List list = null;
    if (isToEntity) {
        list = ToEntityUtil.toEntityList(query.list(), hqlQuery.getClass1(), hqlQuery.getDataGrid().getField().split(","));
    } else {
        list = query.list();
    }
    return new PageList(hqlQuery, list, offset, curPageNO, allCounts);
}


public int updateBySqlString(String query){
    Query querys = getSession().createSQLQuery(query);
    return querys.executeUpdate();
}


public void updateEntitie(String className,Object id){
    getSession().update(className, id);
// getSession().flush();
}


public int getCount(Class<T> clazz){
    int count = DataAccessUtils.intResult(getSession().createQuery("select count(*) from " + clazz.getName()).list());
    return count;
}


public void saveOrUpdate(T entity){
    try {
        getSession().saveOrUpdate(entity);
        // getSession().flush();
        if (logger.isDebugEnabled()) {
            logger.debug("添加或更新成功," + entity.getClass().getName());
        }
    } catch (RuntimeException e) {
        logger.error("添加或更新异常", e);
        throw e;
    }
}


public List<T> findObjForJdbc(String sql,int page,int rows,Class<T> clazz){
    List<T> rsList = new ArrayList<T>();
    // 封装分页SQL
    sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
    List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
    T po = null;
    for (Map<String, Object> m : mapList) {
        try {
            po = clazz.newInstance();
            MyBeanUtils.copyMap2Bean_Nobig(po, m);
            rsList.add(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return rsList;
}


public Integer getRowCount(DetachedCriteria criteria){
    return oConvertUtils.getInt(((Criteria) criteria.setProjection(Projections.rowCount())).uniqueResult(), 0);
}


public Map<Object,Object> getHashMapbyQuery(String hql){
    Query query = getSession().createQuery(hql);
    List list = query.list();
    Map<Object, Object> map = new HashMap<Object, Object>();
    for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
        Object[] tm = (Object[]) iterator.next();
        map.put(tm[0].toString(), tm[1].toString());
    }
    return map;
}


public List<T> findByDetached(DetachedCriteria dc){
    return dc.getExecutableCriteria(getSession()).list();
}


public Criteria createCriteria(Class<T> entityClass){
    Criteria criteria = getSession().createCriteria(entityClass);
    return criteria;
}


public List findByExample(String entityName,Object exampleEntity){
    Assert.notNull(exampleEntity, "Example entity must not be null");
    Criteria executableCriteria = (entityName != null ? getSession().createCriteria(entityName) : getSession().createCriteria(exampleEntity.getClass()));
    executableCriteria.add(Example.create(exampleEntity));
    return executableCriteria.list();
}


public List<DBTable> getAllDbTableName(){
    List<DBTable> resultList = new ArrayList<DBTable>();
    SessionFactory factory = getSession().getSessionFactory();
    Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
    for (String key : (Set<String>) metaMap.keySet()) {
        DBTable dbTable = new DBTable();
        AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
        dbTable.setTableName(classMetadata.getTableName());
        dbTable.setEntityName(classMetadata.getEntityName());
        Class<?> c;
        try {
            c = Class.forName(key);
            JeecgEntityTitle t = c.getAnnotation(JeecgEntityTitle.class);
            dbTable.setTableTitle(t != null ? t.name() : "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resultList.add(dbTable);
    }
    return resultList;
}


public List<T> executeQuery(String hql,Object[] values){
    Query query = getSession().createQuery(hql);
    // query.setCacheable(true);
    for (int i = 0; values != null && i < values.length; i++) {
        query.setParameter(i, values[i]);
    }
    return query.list();
}


public List<T> findHql(String hql,Object param){
    Query q = getSession().createQuery(hql);
    if (param != null && param.length > 0) {
        for (int i = 0; i < param.length; i++) {
            q.setParameter(i, param[i]);
        }
    }
    return q.list();
}


public DataTableReturn getDataTableReturn(CriteriaQuery cq,boolean isOffset){
    Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
    CriteriaImpl impl = (CriteriaImpl) criteria;
    // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
    Projection projection = impl.getProjection();
    final int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    criteria.setProjection(projection);
    if (projection == null) {
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    }
    // 判断是否有排序字段
    if (cq.getOrdermap() != null) {
        cq.setOrder(cq.getOrdermap());
    }
    // 每页显示数
    int pageSize = cq.getPageSize();
    int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(), // 当前页
    pageSize);
    int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
    if (isOffset) {
        // 是否分页
        criteria.setFirstResult(offset);
        criteria.setMaxResults(cq.getPageSize());
    } else {
        pageSize = allCounts;
    }
    // update-begin--Author:scott ---- Date:20170320 ----------- for：暂时注册掉，目前DataTableReturn未用，DetachedCriteriaUtil也只有此处用到------
    // DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(), cq.getField().split(","), cq.getEntityClass(), false);
    // update-end--Author:scott ---- Date:20170320  -----------  for：暂时注册掉，目前DataTableReturn未用，DetachedCriteriaUtil也只有此处用到------
    return new DataTableReturn(allCounts, allCounts, cq.getDataTables().getEcho(), criteria.list());
}


public T singleResult(String hql){
    T t = null;
    Query queryObject = getSession().createQuery(hql);
    List<T> list = queryObject.list();
    if (list.size() == 1) {
        // getSession().flush();
        t = list.get(0);
    } else if (list.size() > 0) {
        throw new BusinessException("查询结果数:" + list.size() + "大于1");
    }
    return t;
}


public Integer executeSql(String sql,Map<String,Object> param){
    return this.namedParameterJdbcTemplate.update(sql, param);
}


public Map<String,Object> findOneForJdbc(String sql,Object objs){
    try {
        return this.jdbcTemplate.queryForMap(sql, objs);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}


public Serializable save(T entity){
    try {
        Serializable id = getSession().save(entity);
        // getSession().flush();
        if (logger.isDebugEnabled()) {
            logger.debug("保存实体成功," + entity.getClass().getName());
        }
        return id;
    } catch (RuntimeException e) {
        logger.error("保存实体异常", e);
        throw e;
    }
}


public List<T> loadAll(Class<T> entityClass){
    Criteria criteria = createCriteria(entityClass);
    return criteria.list();
}


@SuppressWarnings("unchecked")
public List<T> getListByCriteriaQuery(CriteriaQuery cq,Boolean ispage){
    Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
    // 判断是否有排序字段
    if (cq.getOrdermap() != null) {
        cq.setOrder(cq.getOrdermap());
    }
    if (ispage) {
        criteria.setFirstResult((cq.getCurPage() - 1) * cq.getPageSize());
        criteria.setMaxResults(cq.getPageSize());
    }
    return criteria.list();
}


public Object executeSqlReturnKey(String sql,Map<String,Object> param){
    Object keyValue = null;
    KeyHolder keyHolder = null;
    SqlParameterSource sqlp = new MapSqlParameterSource(param);
    // update-begin--Author:	jg_huangxg Date: 20150625 for：[bugfree号]oc时,录入数据在Oracle下 数据写入错误--------------------
    if (StringUtil.isNotEmpty(param.get("id"))) {
        // 表示已经生成过id(UUID),则表示是非序列或数据库自增的形式
        this.namedParameterJdbcTemplate.update(sql, sqlp);
    // --author：zhoujf---start------date:20170216--------for:自定义表单保存数据格sqlserver报错问题
    } else if (StringUtil.isNotEmpty(param.get("ID"))) {
        // 表示已经生成过id(UUID),则表示是非序列或数据库自增的形式
        this.namedParameterJdbcTemplate.update(sql, sqlp);
    // --author：zhoujf---end------date:20170216--------for:自定义表单保存数据格sqlserver报错问题
    } else {
        // NATIVE or SEQUENCE
        keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(sql, sqlp, keyHolder, new String[] { "id" });
        Number number = keyHolder.getKey();
        if (oConvertUtils.isNotEmpty(number)) {
            keyValue = keyHolder.getKey().longValue();
        }
    }
    // update-end--Author: jg_huangxg Date: 20150625 for：[bugfree号]oc时,录入数据在Oracle下 数据写入错误----------------------
    return keyValue;
}


public void delete(T entity){
    try {
        getSession().delete(entity);
        // getSession().flush();
        if (logger.isDebugEnabled()) {
            logger.debug("删除成功," + entity.getClass().getName());
        }
    } catch (RuntimeException e) {
        logger.error("删除异常", e);
        throw e;
    }
}


public T findUniqueBy(Class<T> entityClass,String propertyName,Object value){
    Assert.hasText(propertyName);
    return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
}


public List<T> findListbySql(String sql){
    Query querys = getSession().createSQLQuery(sql);
    return querys.list();
}


public List<T> findByProperty(Class<T> entityClass,String propertyName,Object value){
    Assert.hasText(propertyName);
    return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
}


@SuppressWarnings("unchecked")
public PageList getPageList(HqlQuery hqlQuery,boolean needParameter){
    Query query = getSession().createQuery(hqlQuery.getQueryString());
    if (needParameter) {
        query.setParameters(hqlQuery.getParam(), (Type[]) hqlQuery.getTypes());
    }
    int allCounts = query.list().size();
    int curPageNO = hqlQuery.getCurPage();
    int offset = PagerUtil.getOffset(allCounts, curPageNO, hqlQuery.getPageSize());
    String toolBar = PagerUtil.getBar(hqlQuery.getMyaction(), allCounts, curPageNO, hqlQuery.getPageSize(), hqlQuery.getMap());
    query.setFirstResult(offset);
    query.setMaxResults(hqlQuery.getPageSize());
    return new PageList(query.list(), toolBar, offset, curPageNO, allCounts);
}


@SuppressWarnings({ "unchecked" })
public List<T> executeProcedure(String executeSql,Object params){
    SQLQuery sqlQuery = getSession().createSQLQuery(executeSql);
    for (int i = 0; i < params.length; i++) {
        sqlQuery.setParameter(i, params[i]);
    }
    return sqlQuery.list();
}


public T get(Class<T> entityClass,Serializable id){
    return (T) getSession().get(entityClass, id);
}


public Long getCountForJdbc(String sql){
    return this.jdbcTemplate.queryForObject(sql, Long.class);
}


public int batchInsertsEntitie(List<T> entityList){
    int num = 0;
    for (int i = 0; i < entityList.size(); i++) {
        save(entityList.get(i));
        num++;
    }
    return num;
}


public void getProperty(Class entityName){
    ClassMetadata cm = sessionFactory.getClassMetadata(entityName);
    // 获得该类所有的属性名称
    String[] str = cm.getPropertyNames();
    for (int i = 0; i < str.length; i++) {
        String property = str[i];
        // 获得该名称的类型
        String type = cm.getPropertyType(property).getName();
        org.jeecgframework.core.util.LogUtil.info(property + "---&gt;" + type);
    }
}


public Query createQuery(Session session,String hql,Object objects){
    Query query = session.createQuery(hql);
    if (objects != null) {
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
    }
    return query;
}


public Integer executeHql(String hql){
    Query q = getSession().createQuery(hql);
    return q.executeUpdate();
}


public T getEntity(Class entityName,Serializable id){
    T t = (T) getSession().get(entityName, id);
    if (t != null) {
    // getSession().flush();
    }
    return t;
}


public Long getCountForJdbcParam(String sql,Object[] objs){
    // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
    return this.jdbcTemplate.queryForObject(sql, objs, Long.class);
// -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
}


public void batchSave(List<T> entitys){
    for (int i = 0; i < entitys.size(); i++) {
        getSession().save(entitys.get(i));
        if (i % 1000 == 0) {
            // 1000个对象批量写入数据库，后才清理缓存
            getSession().flush();
            getSession().clear();
        }
    }
    // 最后页面的数据，进行提交手工清理
    getSession().flush();
    getSession().clear();
}


public void updateEntityById(Class entityName,Serializable id){
    updateEntitie(get(entityName, id));
}


public Integer countByJdbc(String sql,Object param){
    // -- update-begin author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
    return this.jdbcTemplate.queryForObject(sql, param, Integer.class);
// -- update-end author： xugj date:20160103  for: #851 controller 单元测试升级spring 版本    -->
}


public void callableStatementByName(String proc){
}


public List<T> pageList(DetachedCriteria dc,int firstResult,int maxResult){
    Criteria criteria = dc.getExecutableCriteria(getSession());
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    criteria.setFirstResult(firstResult);
    criteria.setMaxResults(maxResult);
    return criteria.list();
}


public Session getSession(){
    // 事务必须是开启的(Required)，否则获取不到
    return sessionFactory.getCurrentSession();
}


public void deleteAllEntitie(Collection<T> entitys){
    for (Object entity : entitys) {
        getSession().delete(entity);
    // getSession().flush();
    }
}


}