package com.gs.service;
 import com.gs.bean.User;
import com.gs.common.bean.Pager;
import java.io.Serializable;
import java.util.List;
public interface BaseService {


public List<T> queryByPagerDisable(Pager pager)
;

public int batchInsert(List<T> list)
;

public int batchUpdate(List<T> list)
;

public T query(T t)
;

public int count(User user)
;

public int insert(T t)
;

public int update(T t)
;

public int active(String id)
;

public List<T> queryAll(String status)
;

public List<T> blurredQuery(Pager pager,T t)
;

public int delete(T t)
;

public int batchDelete(List<T> list)
;

public int inactive(String id)
;

public int countByBlurred(T t,User user)
;

public int deleteById(PK id)
;

public int countByDisable(User user)
;

public T queryById(PK id)
;

public List<T> queryByPager(Pager pager)
;

}