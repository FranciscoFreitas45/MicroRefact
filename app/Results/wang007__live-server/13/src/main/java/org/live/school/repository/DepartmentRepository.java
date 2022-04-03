package org.live.school.repository;
 import org.live.common.base.BaseRepository;
import org.live.school.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
public interface DepartmentRepository extends BaseRepository<Department, String>{


public Page<Department> find(PageRequest pageRequest,Map<String,Object> filter)
;

@Query("select d from Department d where d.enableFlag = :enableFlag")
public List<Department> findByEnableFlag(boolean enableFlag)
;

}