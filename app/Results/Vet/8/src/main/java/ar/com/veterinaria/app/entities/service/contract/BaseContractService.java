package ar.com.veterinaria.app.entities.service.contract;
 import java.util.List;
import java.util.Map;
public interface BaseContractService {


public T add(T t)
;

public boolean exist(T t)
;

public boolean isValidInputData(T t)
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