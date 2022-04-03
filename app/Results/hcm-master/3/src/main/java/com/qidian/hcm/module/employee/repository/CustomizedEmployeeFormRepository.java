package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.CustomizedEmployeeForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface CustomizedEmployeeFormRepository extends JpaRepository<CustomizedEmployeeForm, Long>{


@Modifying
@Query("update CustomizedEmployeeForm u set  u.enable=:enable  where u.id=:id")
public void enabledCustomizedEmployeeForm(Long id,Boolean enable)
;

@Query(value = "select  ifnull(max(b.idx),0)  from customized_employee_form b where b.type=:type", nativeQuery = true)
public Integer findMaxIdxByType(String type)
;

public List<CustomizedEmployeeForm> findCustomizedEmployeeFormByTitle(String title)
;

public List<CustomizedEmployeeForm> findByEnableOrderByTypeAscIdxAsc(Boolean enable)
;

}