import cn.com.cnc.fcc.domain.QmsUnqualifiedProduct;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsUnqualifiedProductRepository extends JpaRepository<QmsUnqualifiedProduct, Long> {


public List<QmsUnqualifiedProduct> findByInspectionValueId(Integer id)


public List<QmsUnqualifiedProduct> findByInspectionValueIdAndApproveUserIdIsNotNull(Integer inspectionValueId)


}