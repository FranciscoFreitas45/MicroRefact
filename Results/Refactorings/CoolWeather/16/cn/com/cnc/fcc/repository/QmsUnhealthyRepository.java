import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.com.cnc.fcc.domain.QmsUnhealthy;
@SuppressWarnings("unused")
@Repository
public interface QmsUnhealthyRepository extends JpaRepository<QmsUnhealthy, Long> {


public List<QmsUnhealthy> findByUnhealthyCdAndFlagStatus(String unhealthyCd,String string)


public List<QmsUnhealthy> findByIdAndFlagStatus(Long id,String flagStatus)


}