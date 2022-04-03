package com.easyshopping.service;
 import java.io.Serializable;
import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
public interface BaseService {


public T find(ID id)
;

public List<T> findList(Integer first,Integer count,List<Filter> filters,List<Order> orders)
;

public long count(Filter filters)
;

public void save(T entity)
;

public boolean exists(Filter filters)
;

public T update(T entity,String ignoreProperties)
;

public Page<T> findPage(Pageable pageable)
;

public List<T> findAll()
;

public void delete(T entity)
;

}