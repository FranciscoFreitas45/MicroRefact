package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsProduct;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
@SuppressWarnings("unused")
@Repository
public interface QmsProductRepository extends JpaRepository<QmsProduct, Long>{


public List<QmsProduct> findByProductNumAndMaterielId(String productNum,Integer materielId)
;

public List<QmsProduct> findByProductNum(String productNum)
;

}