package org.gliderwiki.web.common.service;
 import java.io.Serializable;
import java.util.List;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.web.common.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("entityService")
@RemoteProxy(name = "EntityService")
public class EntityServiceImpl implements EntityService<T>{

@Autowired
 private  EntityDao<T> entityDao;


public int updateEntity(T domain){
    return entityDao.updateEntity(domain);
}


@Override
public int getCountEntity(T domain){
    return entityDao.getCountEntity(domain);
}


@Override
public int deleteEntity(T domain){
    return entityDao.deleteEntity(domain);
}


@Override
public int insertEntity(T domain){
    return entityDao.insertEntity(domain);
}


@Override
public T getRowEntity(T domain){
    return entityDao.getRowEntity(domain);
}


@Override
public List<T> getListEntityOrdered(T domain,String orderQuery){
    return entityDao.getListEntityOrdered(domain, orderQuery);
}


@Override
public List<T> getListEntity(T domain){
    return entityDao.getListEntity(domain);
}


}