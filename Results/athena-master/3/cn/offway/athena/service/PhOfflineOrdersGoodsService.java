import java.util.List;
import cn.offway.athena.domain.PhOfflineOrdersGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PhOfflineOrdersGoodsService {


public List<PhOfflineOrdersGoods> findByordersNo(String ordersNo)


public List<PhOfflineOrdersGoods> findByBrandName(String brandName)


public List<PhOfflineOrdersGoods> save(List<PhOfflineOrdersGoods> entities)


public PhOfflineOrdersGoods findOne(Long id)


public void delbyOrdersNo(String ordersNo)


public Page<PhOfflineOrdersGoods> findByPage(String ordersNo,Pageable page)


public void delete(Long id)


}