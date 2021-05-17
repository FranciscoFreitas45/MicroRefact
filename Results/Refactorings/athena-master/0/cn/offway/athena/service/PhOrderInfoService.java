import cn.offway.athena.domain.PhOrderInfo;
import cn.offway.athena.utils.JsonResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
public interface PhOrderInfoService {


public void cancel(String orderNo)


public PhOrderInfo findByOrderNo(String orderNo)


public PhOrderInfo save(PhOrderInfo phOrderInfo)


public PhOrderInfo findOne(Long id)


public Page<PhOrderInfo> findByPage(String realName,String position,String orderNo,String unionid,Long brandId,String isOffway,Pageable page)


public String generateOrderNo(String prefix)


}