package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.MonitorMail;
public interface MonitorMailDao extends JpaSpecificationExecutor<MonitorMail>, PagingAndSortingRepository<MonitorMail, Integer>{


public List<MonitorMail> findByApplyId(Integer applyId)
;

}