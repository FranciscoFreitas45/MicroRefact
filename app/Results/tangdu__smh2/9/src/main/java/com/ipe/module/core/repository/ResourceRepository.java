package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.Resource;
import org.springframework.data.jpa.repository.Query;
public interface ResourceRepository extends CustomerRepository<Resource, String>{


@Query("select max(sno)+1 from Resource")
public Integer getMaxSno()
;

}