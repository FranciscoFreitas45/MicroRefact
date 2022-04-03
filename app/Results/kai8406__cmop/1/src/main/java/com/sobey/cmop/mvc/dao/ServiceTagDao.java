package com.sobey.cmop.mvc.dao;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.ServiceTag;
public interface ServiceTagDao extends PagingAndSortingRepository<ServiceTag, Integer>, JpaSpecificationExecutor<ServiceTag>{


public List<ServiceTag> findByUserId(Integer userId)
;

public List<ServiceTag> findByUserIdAndStatusInOrderByIdDesc(Integer userId,Collection<Integer> status)
;

public ServiceTag findByNameAndUserId(String name,Integer userId)
;

}