import cn.com.cnc.fcc.domain.RbacRight;
import cn.com.cnc.fcc.domain.RbacRole;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface RbacRightRepository extends JpaRepository<RbacRight, Long> {


@Query(value = "select r from RbacRight r where delFlag = 0 and rightName like %:rightName% and rightCode like %:rightCode%")
public Page<RbacRight> getRightInfo(Pageable pageable,String rightName,String rightCode)


public List<RbacRight> findByDelFlag(Integer delFlag)


public List<RbacRight> findByRightCode(String s)


}