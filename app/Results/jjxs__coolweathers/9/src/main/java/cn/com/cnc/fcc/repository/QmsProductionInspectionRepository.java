package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsProductionInspection;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsProductionInspectionRepository extends JpaRepository<QmsProductionInspection, Long>{


public List<QmsProductionInspection> findByMaterielId(Integer s)
;

public List<QmsProductionInspection> findByBomTechnologyIdAndSerialNumber(Integer bomTechnologyId,String serialNumber)
;

public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(Integer s,String m)
;

public List<QmsProductionInspection> findByMaterielIdAndWorkno(Integer materielId,String workno)
;

public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(Integer BomTechnologyId,String FlagStatus)
;

}