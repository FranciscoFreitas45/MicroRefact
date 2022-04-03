package com.netease.repositories;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.netease.domain.Basic;
public interface BasicRepository extends CrudRepository<Basic, Integer>{


public List<Basic> findByUserIdOrderByReportTimeDesc(Integer userId)
;

public Basic findById(Integer id)
;

public Basic save(Basic basic)
;

public List<Basic> findAll()
;

public Basic findByReportNumber(String reportNumber)
;

public void setCreate_time(Integer id,DateTime create_time);

public void setUpdate_time(Integer id,DateTime update_time);

public String toString(Integer id);

public void setIsDelete(Integer id,Integer isDelete);

}