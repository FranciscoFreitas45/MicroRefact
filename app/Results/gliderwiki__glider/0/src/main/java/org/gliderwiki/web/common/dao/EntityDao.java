package org.gliderwiki.web.common.dao;
 import java.io.Serializable;
import java.util.List;
public interface EntityDao {


public int updateEntity(T domain)
;

public int getCountEntity(T domain)
;

public int deleteEntity(T domain)
;

public int insertEntity(T domain)
;

public T getRowEntity(T domain)
;

public List<T> getListEntityOrdered(T domain,String orderQuery)
;

public List<T> getListEntity(T domain)
;

}