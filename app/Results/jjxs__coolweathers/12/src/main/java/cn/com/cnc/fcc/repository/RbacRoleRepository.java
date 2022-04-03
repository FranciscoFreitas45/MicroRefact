package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.RbacRole;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface RbacRoleRepository extends JpaRepository<RbacRole, Long>{


@Query(value = "select r from RbacRole r where delFlag = 0 and roleName like %:roleName% and roleCode like %:roleCode% ")
public Page<RbacRole> getRoleInfoForCodeName(Pageable pageable,String roleName,String roleCode)
;

public List<RbacRole> findByDelFlag(Integer delFlag)
;

@Query(value = "select r from RbacRole r where delFlag = 0 and roleName like %:roleName% ")
public Page<RbacRole> getRoleInfo(Pageable pageable,String roleName)
;

public List<RbacRole> findByIdAndDelFlag(Long id,Integer flag)
;

public List<RbacRole> findByRoleCode(String s)
;

}