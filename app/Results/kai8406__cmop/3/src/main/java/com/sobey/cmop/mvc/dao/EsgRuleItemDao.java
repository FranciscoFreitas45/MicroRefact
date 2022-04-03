package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.EsgRuleItem;
public interface EsgRuleItemDao extends PagingAndSortingRepository<EsgRuleItem, Integer>, JpaSpecificationExecutor<EsgRuleItem>{


public List<EsgRuleItem> findByNetworkEsgItemId(Integer esgId)
;

}