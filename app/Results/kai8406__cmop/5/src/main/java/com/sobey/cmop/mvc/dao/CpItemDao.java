package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.CpItem;
public interface CpItemDao extends JpaSpecificationExecutor<CpItem>, PagingAndSortingRepository<CpItem, Integer>{


}