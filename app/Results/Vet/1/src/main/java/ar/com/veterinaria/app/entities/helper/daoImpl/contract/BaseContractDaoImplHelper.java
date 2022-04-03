package ar.com.veterinaria.app.entities.helper.daoImpl.contract;
 import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BaseContractDaoImplHelper {


public boolean deleted(JpaRepository<T,Integer> repository,Integer id)
;

public boolean isDuplicated(JpaRepository<T,Integer> repository,T t)
;

public T findByName(JpaRepository<T,Integer> repository,String t)
;

public T update(JpaRepository<T,Integer> repository,Integer id,T t)
;

public boolean existId(JpaRepository<T,Integer> repository,Integer id)
;

public List<Map<String,Object>> findAll(JpaRepository<T,Integer> repository)
;

}