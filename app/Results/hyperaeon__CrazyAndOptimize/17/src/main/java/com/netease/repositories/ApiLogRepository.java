package com.netease.repositories;
 import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.netease.domain.ApiLog;
public interface ApiLogRepository extends CrudRepository<ApiLog, Long>{


public List<ApiLog> findAll(Pageable pageable)
;

}