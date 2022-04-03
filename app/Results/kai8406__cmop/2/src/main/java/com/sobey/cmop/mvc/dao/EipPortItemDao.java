package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.EipPortItem;
public interface EipPortItemDao extends PagingAndSortingRepository<EipPortItem, Integer>, JpaSpecificationExecutor<EipPortItem>{


public List<EipPortItem> findByNetworkEipItemId(Integer networkEipItemId)
;

}