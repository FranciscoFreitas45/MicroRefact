package org.live.common.base;
 import java.io.Serializable;
import java.util.List;
public interface BaseService {


public T get(ID id)
;

public T findOne(ID id)
;

public List<T> save(Iterable<T> entities)
;

public List<T> findAll()
;

public void delete(Iterable<T> entities)
;

}