import cn.com.cnc.fcc.domain.RbacMenu;
import cn.com.cnc.fcc.domain.RbacRight;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface RbacMenuRepository extends JpaRepository<RbacMenu, Long> {


public List<RbacMenu> findByDelFlag(Integer delFlag)


@Query(value = "select r from RbacMenu r where delFlag = 0")
public Page<RbacMenu> getMenuInfo(Pageable pageable)


}