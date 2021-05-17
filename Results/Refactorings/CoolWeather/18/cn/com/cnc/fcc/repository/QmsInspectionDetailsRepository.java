import cn.com.cnc.fcc.domain.QmsInspectionDetails;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsInspectionDetailsRepository extends JpaRepository<QmsInspectionDetails, Long> {


}