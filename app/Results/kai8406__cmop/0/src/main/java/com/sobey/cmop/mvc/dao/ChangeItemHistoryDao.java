package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.ChangeItemHistory;
public interface ChangeItemHistoryDao extends PagingAndSortingRepository<ChangeItemHistory, Integer>, JpaSpecificationExecutor<ChangeItemHistory>{


}