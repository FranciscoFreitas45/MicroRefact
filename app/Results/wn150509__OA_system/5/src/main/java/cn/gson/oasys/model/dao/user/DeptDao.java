package cn.gson.oasys.model.dao.user;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.user.Dept;
public interface DeptDao extends PagingAndSortingRepository<Dept, Long>{


@Query("select de.deptName from Dept de where de.deptId=:id")
public String findname(Long id)
;

public List<Dept> findByDeptId(Long id)
;

public void setDept(Long deptId,Dept dept);

public Dept getDept(Long deptId);

}