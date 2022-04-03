package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Change;
public interface ChangeDao extends PagingAndSortingRepository<Change, Integer>, JpaSpecificationExecutor<Change>{


public Change findByResourcesIdAndSubResourcesIdIsNull(Integer resourcesId)
;

public List<Change> findByResources_Id(Integer resourcesId)
;

public Change findByResourcesIdAndSubResourcesId(Integer resourcesId,Integer subResourcesId)
;

}