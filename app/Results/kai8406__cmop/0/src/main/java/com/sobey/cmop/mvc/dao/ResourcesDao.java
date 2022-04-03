package com.sobey.cmop.mvc.dao;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Resources;
public interface ResourcesDao extends PagingAndSortingRepository<Resources, Integer>, JpaSpecificationExecutor<Resources>{


public List<Resources> findByServiceTypeAndUserId(Integer serviceType,Integer userId)
;

public List<Resources> findByServiceTagId(Integer serviceTagId)
;

public List<Resources> findByServiceType(Integer serviceType)
;

public List<Resources> findByServiceTagIdAndStatusInOrderByIdDesc(Integer serviceTagId,Collection<Integer> status)
;

}