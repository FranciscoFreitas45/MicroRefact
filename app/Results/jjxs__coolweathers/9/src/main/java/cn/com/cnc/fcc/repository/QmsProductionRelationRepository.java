package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsProductionRelation;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsProductionRelationRepository extends JpaRepository<QmsProductionRelation, Long>{


public List<QmsProductionRelation> findAllByAssemblyMaterielIdAndIdIsNot(Integer assemblyMaterielId,Long id)
;

public List<QmsProductionRelation> findAllByUseProductIdAndIdIsNot(Integer useProductId,Long id)
;

public List<QmsProductionRelation> findByAssemblyMaterielId(Integer assemblyMaterielId)
;

public List<QmsProductionRelation> findByProductionInspectionId(Integer productionInspectionId)
;

}