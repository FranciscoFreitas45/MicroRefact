package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsEntryControlDetails;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsEntryControlDetailsRepository extends JpaRepository<QmsEntryControlDetails, Long>{


public List<QmsEntryControlDetails> findAllByMaterielIdAndFlagStatusAndIsValidOrderByItemNumber(Integer materielId,String flagStatus,String isValid)
;

public List<QmsEntryControlDetails> findByMaterielIdAndInspectionItemAndFlagStatus(Integer materielCd,String inspectionItem,String flagStatus)
;

}