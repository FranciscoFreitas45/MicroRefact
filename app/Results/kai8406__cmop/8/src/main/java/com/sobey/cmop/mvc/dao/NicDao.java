package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Nic;
public interface NicDao extends JpaSpecificationExecutor<Nic>, PagingAndSortingRepository<Nic, Integer>{


public List<Nic> findByHostServerId(Integer hostServerId)
;

}