package ar.com.veterinaria.app.entities.dao;
 import java.util.List;
import java.util.Map;
public interface BaseDao {


public T add(T t)
;

public boolean exist(T t)
;

public T findById(int id)
;

public T update(int id,T t)
;

public List<Map<String,Object>> findAll()
;

public void remove(int id)
;

}