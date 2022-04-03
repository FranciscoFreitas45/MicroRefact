package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsMaterielSupplier;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsMaterielSupplierRepository extends JpaRepository<QmsMaterielSupplier, Long>{


public List<QmsMaterielSupplier> findByMaterielId(Integer s)
;

public List<QmsMaterielSupplier> findBySupplierId(Integer s)
;

public Optional<QmsMaterielSupplier> findByMaterielIdAndSupplierIdAndIdNot(Integer materielId,Integer supplierId,Long id)
;

}