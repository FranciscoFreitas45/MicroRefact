package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsControlDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsControlDetailsRepository extends JpaSpecificationExecutor<QmsControlDetails>, JpaRepository<QmsControlDetails, Long>{


public List<QmsControlDetails> findByInspectionCd(String s)
;

public Page<QmsControlDetails> findAllByInspectionCdContainingAndInspectionItemContainingAndFlagStatus(String inspectionCd,String inspectionItem,String flagStatus,Pageable pageable)
;

}