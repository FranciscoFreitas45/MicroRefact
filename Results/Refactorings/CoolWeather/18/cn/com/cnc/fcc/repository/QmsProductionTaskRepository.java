import cn.com.cnc.fcc.domain.QmsProductionTask;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsProductionTaskRepository extends JpaRepository<QmsProductionTask, Long> {


}