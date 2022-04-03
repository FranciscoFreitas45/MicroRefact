package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
public interface NetworkEipItemDao extends JpaSpecificationExecutor<NetworkEipItem>, PagingAndSortingRepository<NetworkEipItem, Integer>{


public List<NetworkEipItem> findByApplyId(Integer applyId)
;

public List<NetworkEipItem> findByApplyUserId(Integer userId)
;

public List<NetworkEipItem> findByComputeItemId(Integer computeItemId)
;

}