package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.Department;
public interface DepartmentDao extends JpaSpecificationExecutor<Department>, PagingAndSortingRepository<Department, Integer>{


public Department findByName(String name)
;

}