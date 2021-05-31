import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.offway.athena.domain.PhOrderInfo;
import java.lang.String;
import java.util.List;
public interface PhOrderInfoRepository extends JpaSpecificationExecutor<PhOrderInfo> {


public PhOrderInfo findByOrderNo(String orderNo)


@Query(nativeQuery = true, value = "select count(*) from sequence where name=CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d'))")
public int hasOrder(String prefix)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "insert into sequence values(CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d')),0,1)")
public int insert(String prefix)


@Query(nativeQuery = true, value = "select CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextval(CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d'))), 5, 0))")
public String generateOrderNo(String prefix)


}