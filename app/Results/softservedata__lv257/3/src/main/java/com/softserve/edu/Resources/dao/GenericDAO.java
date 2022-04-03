package com.softserve.edu.Resources.dao;
 import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface GenericDAO {


public Optional<T> querySingleResult(String queryWithNamedParams,Map<String,Object> params)
;

public Optional<T> findReferenceById(ID id)
;

public void flush()
;

public List<T> queryResultList(String queryWithNamedParams,Map<String,Object> params)
;

public Optional<T> findById(ID id,LockModeType lockModeType)
;

public void makeTransient(T entity)
;

public void checkVersion(T entity,boolean forceUpdate)
;

public T makePersistent(T entity)
;

public List<T> findAll()
;

public Long getCount()
;

}