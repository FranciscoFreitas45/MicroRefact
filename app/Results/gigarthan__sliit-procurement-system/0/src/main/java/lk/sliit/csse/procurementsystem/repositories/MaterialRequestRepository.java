package lk.sliit.csse.procurementsystem.repositories;
 import java.util.List;
import lk.sliit.csse.procurementsystem.models.Items;
import lk.sliit.csse.procurementsystem.models.MaterialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface MaterialRequestRepository extends JpaRepository<T, Long>{


public List<MaterialRequest> findByReqOrderNo(long reqOrderNo)
;

}