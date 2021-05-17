import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhOfflineOrdersGoods;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface PhOfflineOrdersGoodsRepository extends JpaRepository<PhOfflineOrdersGoods, Long> {


@Transactional
public void deleteByOrdersNo(String ordersNo)


public List<PhOfflineOrdersGoods> findByBrandName(String brandName)


public List<PhOfflineOrdersGoods> findByOrdersNo(String ordersNo)


}