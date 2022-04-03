package com.lingxiang2014.dao;
 import java.io.Serializable;
import java.util.List;
import javax.persistence.LockModeType;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
public interface BaseDao {


public long count(Filter filters)
;

public void clear()
;

public void refresh(T entity,LockModeType lockModeType)
;

public Page<T> findPage(Pageable pageable)
;

public void remove(T entity)
;

public boolean isManaged(T entity)
;

public void flush()
;

public T find(ID id,LockModeType lockModeType)
;

public ID getIdentifier(T entity)
;

public List<T> findList(Integer first,Integer count,List<Filter> filters,List<Order> orders)
;

public T merge(T entity)
;

public void detach(T entity)
;

public void lock(T entity,LockModeType lockModeType)
;

public void persist(T entity)
;

}