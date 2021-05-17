import cn.com.cnc.fcc.domain.QmsEntryInspection;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsEntryInspectionRepository extends JpaRepository<QmsEntryInspection, Long> {


}