package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsMateriel;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsMaterielRepository extends JpaSpecificationExecutor<QmsMateriel>, JpaRepository<QmsMateriel, Long>{


public List<QmsMateriel> findByUseUnitId(Integer s)
;

public List<QmsMateriel> findByMaterielCdAndFlagStatus(String materielCd,String flagStatus)
;

public Optional<QmsMateriel> findQmsMaterielByMaterielCdAndFlagStatus(String materielCd,String flagStatus)
;

public List<QmsMateriel> findByPackgeUnitId(Integer s)
;

public List<QmsMateriel> findByIdAndFlagStatus(Long valueOf,String string)
;

public List<QmsMateriel> findByMaterielCd(String s)
;

}