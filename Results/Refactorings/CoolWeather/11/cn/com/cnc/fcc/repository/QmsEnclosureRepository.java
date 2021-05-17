import cn.com.cnc.fcc.domain.QmsEnclosure;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsEnclosureRepository extends JpaRepository<QmsEnclosure, Long> {


public List<QmsEnclosure> findByInspectionInfoIdIn(List groutId)


@Query(value = "select e.id, e.enclosureAddress from QmsEnclosure e where e.inspectionKbn = 2 AND e.inspectionInfoId = :inspectionInfoId")
public List<QmsEnclosure> getEnclosureList(Integer inspectionInfoId)


@Transactional
public Integer deleteByInspectionInfoId(Integer valueOf)


public List<QmsEnclosure> findAllByInspectionInfoIdAndInspectionKbn(Integer inspectionInfoId,String inpectionKbn)


public List<QmsEnclosure> findByInspectionInfoId(Integer bomTechnologyId)


@Modifying
@Transactional
public void deleteByInspectionInfoIdAndInspectionKbnAndEnclosureAddress(Integer inspectionInfoId,String inspectionKbn,String enclosureAddress)


}