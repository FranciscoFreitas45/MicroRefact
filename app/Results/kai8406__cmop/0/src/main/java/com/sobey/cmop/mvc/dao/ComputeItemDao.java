package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.ComputeItem;
public interface ComputeItemDao extends PagingAndSortingRepository<ComputeItem, Integer>, JpaSpecificationExecutor<ComputeItem>{


public List<ComputeItem> findByApplyId(Integer applyId)
;

public List<ComputeItem> findByApplyUserId(Integer userId)
;

}