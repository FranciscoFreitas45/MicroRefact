package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsSupplier;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsSupplierRepository extends JpaSpecificationExecutor<QmsSupplier>, JpaRepository<QmsSupplier, Long>{


public Optional<QmsSupplier> findQmsSupplierBySupplierCdAndFlagStatus(String supplierCd,String flagStatus)
;

public List<QmsSupplier> findBySupplierCd(String s)
;

public List<QmsSupplier> findBySupplierClassId(Integer s)
;

}