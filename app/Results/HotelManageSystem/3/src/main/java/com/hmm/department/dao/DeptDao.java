package com.hmm.department.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.hmm.department.entity.Department;
public interface DeptDao extends JpaSpecificationExecutor<Department>, PagingAndSortingRepository<Department, Integer>{


@Query("from Department o where o.deptNo = ?1 and o.deptName =?1")
public Department findByDeptNoAndDeptName(String deptNo,String deptName)
;

@Query("from Department o where o.deptNo = ?1")
public Department findByDeptNo(String deptNo)
;

@Query("from Department o where o.deptName = ?1")
public Department findByDeptName(String deptName)
;

}