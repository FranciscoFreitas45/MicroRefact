package com.ushahidi.swiftriver.core.api.dao;
 public interface GenericDao {


public T findById(Object id)
;

public T create(T t)
;

public T update(T t)
;

public void delete(T t)
;

}