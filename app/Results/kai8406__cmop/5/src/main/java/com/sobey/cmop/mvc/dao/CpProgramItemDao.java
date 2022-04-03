package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.CpProgramItem;
public interface CpProgramItemDao extends JpaSpecificationExecutor<CpProgramItem>, PagingAndSortingRepository<CpProgramItem, Integer>{


}