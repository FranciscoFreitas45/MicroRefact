package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.ServerModel;
public interface ServerModelDao extends JpaSpecificationExecutor<ServerModel>, PagingAndSortingRepository<ServerModel, Integer>{


public ServerModel findByName(String name)
;

}