package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
public interface NetworkEsgItemDao extends PagingAndSortingRepository<NetworkEsgItem, Integer>, JpaSpecificationExecutor<NetworkEsgItem>{


public List<NetworkEsgItem> findByUserIdOrShare(Integer userId,Boolean shares)
;

}