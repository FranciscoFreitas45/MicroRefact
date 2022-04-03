package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Group;
public interface GroupDao extends JpaSpecificationExecutor<Group>, PagingAndSortingRepository<Group, Integer>{


public Group findByName(String name)
;

}