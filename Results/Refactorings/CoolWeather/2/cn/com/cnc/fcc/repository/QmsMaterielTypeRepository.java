import cn.com.cnc.fcc.domain.QmsMaterielType;
import java.util.List;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsMaterielTypeRepository extends JpaRepository<QmsMaterielType, Long> {


public List<QmsMaterielType> findByIdAndFlagStatus(Long id,String flag)


public List<QmsMaterielType> findByMaterielTypeCdAndFlagStatus(String materielTypeCd,String string)


}