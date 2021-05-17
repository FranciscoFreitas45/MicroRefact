import cn.com.cnc.fcc.domain.QmsProductionInspectionResult;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsProductionInspectionResultRepository extends JpaRepository<QmsProductionInspectionResult, Long> {


public List<QmsProductionInspectionResult> findByInspectionIdAndInspectionValueIdAndInspectionDiff(Integer inspectionId,Integer inspectionValueId,String inspectionDiff)


}