import cn.com.cnc.fcc.domain.QmsInspectionInfo;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsInspectionInfoRepository extends JpaRepository<QmsInspectionInfo, Long> {


}