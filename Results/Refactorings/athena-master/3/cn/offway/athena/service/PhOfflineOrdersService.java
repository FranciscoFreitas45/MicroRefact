import java.util.Date;
import java.util.List;
import cn.offway.athena.domain.PhOfflineOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PhOfflineOrdersService {


public List<PhOfflineOrders> save(List<PhOfflineOrders> entities)


public PhOfflineOrders findOne(Long id)


public Page<PhOfflineOrders> findByPage(String realName,String users,String state,String ordersNo,Date sTime,Date eTime,String brandName,Pageable page)


public void delete(Long id)


}