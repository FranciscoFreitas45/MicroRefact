import cn.com.cnc.fcc.domain.QmsProductionInspectionValue;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsProductionInspectionValueRepository extends JpaRepository<QmsProductionInspectionValue, Long> {


public Optional<QmsProductionInspectionValue> findByInspectionIdAndInspectionDiff(Integer inspectionId,String inspectionDiff)


public List<QmsProductionInspectionValue> findByInspectionId(Integer inspectionId)


}