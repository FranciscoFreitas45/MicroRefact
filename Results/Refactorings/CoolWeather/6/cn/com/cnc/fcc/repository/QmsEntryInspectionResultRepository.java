import cn.com.cnc.fcc.domain.QmsEntryInspectionResult;
import java.util.List;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsEntryInspectionResultRepository extends JpaRepository<QmsEntryInspectionResult, Long> {


public List<QmsEntryInspectionResult> findByIdAndFlagStatus(Long id,String string)


}