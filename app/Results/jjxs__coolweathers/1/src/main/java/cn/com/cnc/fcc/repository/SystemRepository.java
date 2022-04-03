package cn.com.cnc.fcc.repository;
 import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cn.com.cnc.fcc.domain.SystemBean;
@Repository
public interface SystemRepository extends JpaRepository<SystemBean, Long>{


@Query(value = "SELECT NOW()", nativeQuery = true)
public Date getSysteTime()
;

}