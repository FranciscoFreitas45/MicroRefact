package cn.com.cnc.fcc.repository;
 import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import cn.com.cnc.fcc.domain.QmsQualityControlDetails;
@SuppressWarnings("unused")
@Repository
public interface QmsQualityControlDetailsRepository extends JpaRepository<QmsQualityControlDetails, Long>{


public List<QmsQualityControlDetails> findByBomTechnologyId(Integer bomTechnologyId)
;

public QmsQualityControlDetails findByIdAndFlagStatus(Long id,String string)
;

@Transactional
public Integer deleteByBomTechnologyId(Integer valueOf)
;

public List<QmsQualityControlDetails> findByBomTechnologyIdAndFlagStatus(Integer id,String flagStatus)
;

}