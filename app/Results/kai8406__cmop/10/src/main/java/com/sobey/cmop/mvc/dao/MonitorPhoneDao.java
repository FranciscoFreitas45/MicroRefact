package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.MonitorPhone;
public interface MonitorPhoneDao extends JpaSpecificationExecutor<MonitorPhone>, PagingAndSortingRepository<MonitorPhone, Integer>{


public List<MonitorPhone> findByApplyId(Integer applyId)
;

}