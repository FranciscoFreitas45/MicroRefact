package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsBomTechnology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsBomTechnologyRepository extends JpaRepository<QmsBomTechnology, Long>{


public List<QmsBomTechnology> findByMaterielId(Integer s)
;

public List<QmsBomTechnology> findByMaterielIdAndTechnologyCdAndFlagStatus(Integer materielId,String technologyCd,String flagStatus)
;

@Query(value = "update QmsBomTechnology set isDefault = 1  where materielId = ?1 and technologyCd = ?2")
@Modifying
public Integer updateMaterielIdTechnologyCd(Integer hiddenRightMaterielId,String technologyCd)
;

public List<QmsBomTechnology> findByMaterielIdAndIsDefaultAndFlagStatus(Integer materielId,String isDefault,String flagStatus)
;

public List<QmsBomTechnology> findByMaterielIdAndTechnologyCdAndIsDefaultAndFlagStatus(Integer materielId,String technologyCd,String string,String string2)
;

public List<QmsBomTechnology> findByMaterielIdAndTechnologyCdAndOrderNoAndFlagStatus(Integer materielId,String technologyCd,Integer processId,String flagStatus)
;

public List<QmsBomTechnology> findByProcessId(Integer s)
;

public QmsBomTechnology findByIdAndFlagStatus(Long id,String flagStatus)
;

public List<QmsBomTechnology> findByMaterielIdAndTechnologyCdAndOrderNoAndFlagStatusAndIdNot(Integer materielId,String technologyCd,Integer processId,String string,Long long1)
;

@Query(value = "update QmsBomTechnology set isDefault = 0  where materielId = ?1")
@Modifying
public Integer updatehiddenRightMaterielId(Integer hiddenRightMaterielId)
;

}