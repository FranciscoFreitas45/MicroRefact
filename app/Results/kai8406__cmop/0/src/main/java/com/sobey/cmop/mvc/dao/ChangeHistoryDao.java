package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Audit;
import com.sobey.cmop.mvc.entity.ChangeHistory;
public interface ChangeHistoryDao extends JpaSpecificationExecutor<ChangeHistory>, PagingAndSortingRepository<ChangeHistory, Integer>{


public List<ChangeHistory> findByAudit(Audit audit)
;

}