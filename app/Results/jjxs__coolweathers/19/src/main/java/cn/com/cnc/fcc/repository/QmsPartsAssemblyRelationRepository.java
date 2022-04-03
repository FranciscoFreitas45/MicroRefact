package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsPartsAssemblyRelation;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsPartsAssemblyRelationRepository extends JpaRepository<QmsPartsAssemblyRelation, Long>{


public Optional<QmsPartsAssemblyRelation> findAllByBomTechnologyIdAndFlagStatus(Integer bomTechnologyId,String FlagStatus)
;

public List<QmsPartsAssemblyRelation> findAllByFlagStatusAndBomTechnologyId(String FlagStatus,Integer bomTechnologyId)
;

public QmsPartsAssemblyRelation findByIdAndFlagStatus(Long id,String string)
;

@Transactional
public Integer deleteByBomTechnologyId(Integer valueOf)
;

}