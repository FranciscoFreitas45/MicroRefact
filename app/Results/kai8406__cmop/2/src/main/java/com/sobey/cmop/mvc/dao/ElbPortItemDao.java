package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.ElbPortItem;
public interface ElbPortItemDao extends JpaSpecificationExecutor<ElbPortItem>, PagingAndSortingRepository<ElbPortItem, Integer>{


public List<ElbPortItem> findByNetworkElbItemId(Integer networkElbItemId)
;

}