package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Location;
public interface LocationDao extends PagingAndSortingRepository<Location, Integer>, JpaSpecificationExecutor<Location>{


public Location findByAlias(String alias)
;

public Location findByName(String name)
;

}