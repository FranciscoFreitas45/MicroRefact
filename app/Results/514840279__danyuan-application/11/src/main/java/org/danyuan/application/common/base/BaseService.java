package org.danyuan.application.common.base;
 import java.util.List;
import org.springframework.data.domain.Page;
public interface BaseService {


public void saveAll(List<T> entities)
;

public void trunc()
;

public List<T> findAllById(List<String> list)
;

public T findById(String uuid)
;

public T findOne(T entity)
;

public T save(T entity)
;

public void deleteAll(List<T> entities)
;

public Long count(T info)
;

public void deleteById(String uuid)
;

public Page<T> page(Pagination<T> vo)
;

public List<T> findAll(Pagination<T> vo)
;

public void delete(T entity)
;

}