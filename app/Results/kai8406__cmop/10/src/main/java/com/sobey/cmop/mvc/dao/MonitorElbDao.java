package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.MonitorElb;
public interface MonitorElbDao extends PagingAndSortingRepository<MonitorElb, Integer>, JpaSpecificationExecutor<MonitorElb>{


}