package org.gliderwiki.web.common.dao;
 import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.orm.sql.EntityManager;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("entityDao")
public class EntityDaoImpl extends SqlSessionDaoSupportimplements EntityDao<T>{

 private Logger logger;

 private EntityManager entityMgr;


@Override
public int updateEntity(T domain){
    int result = 0;
    try {
        result = entityMgr.update(domain);
    } catch (Exception e) {
        result = -1;
        throw new DBHandleException("[SQL Update Error]", e.getCause());
    }
    return result;
}


@Override
public int getCountEntity(T domain){
    int count = 0;
    try {
        count = entityMgr.count(domain);
    } catch (Exception e) {
        count = -1;
        throw new DBHandleException("[SQL Count Error]", e.getCause());
    }
    return count;
}


@Override
public int deleteEntity(T domain){
    int result = 0;
    try {
        result = entityMgr.delete(domain);
    } catch (Exception e) {
        result = -1;
        throw new DBHandleException("[SQL delete Error]", e.getCause());
    }
    return result;
}


@Override
public int insertEntity(T domain){
    int result = 0;
    try {
        entityMgr.insert(domain);
        result = 1;
    } catch (Exception e) {
        result = -1;
        throw new DBHandleException("[SQL Insert Error]", e.getCause());
    }
    return result;
}


@Override
public T getRowEntity(T domain){
    return (T) entityMgr.load(domain);
}


@Override
public List<T> getListEntityOrdered(T domain,String orderQuery){
    return (List<T>) entityMgr.list(domain, orderQuery);
}


@Override
public List<T> getListEntity(T domain){
    return (List<T>) entityMgr.list(domain);
}


@Autowired
public void setEntityMgr(){
    entityMgr = new EntityManager(getSqlSession());
}


}