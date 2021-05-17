import cn.com.cnc.fcc.domain.QmsSupplier;
import cn.com.cnc.fcc.domain.QmsUnit;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsUnitRepository extends JpaRepository<QmsUnit, Long> {


public List<QmsUnit> findByIdAndFlagStatus(Long id,String flag)


public List<QmsUnit> findByUnitCd(String s)


}