package io.delivery.dao;
 import org.hibernate.Session;
import java.util.List;
public interface BasicDao {


public List<T> getList()
;

public Session getCurrentSession()
;

public T findById(long id)
;

public T create(T entity)
;

public T update(T entity)
;

public T delete(T entity)
;

}