import cn.com.cnc.fcc.domain.QmsSupplierClass;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsSupplierClassRepository extends JpaRepository<QmsSupplierClass, Long> {


public List<QmsSupplierClass> findBySuppkierClass(String s)


public List<QmsSupplierClass> findByIdAndFlagStatus(Long id,String flag)


}