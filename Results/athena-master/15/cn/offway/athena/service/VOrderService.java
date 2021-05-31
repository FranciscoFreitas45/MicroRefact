import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.VOrder;
public interface VOrderService {


public VOrder findByOrderNo(String orderNo)


public VOrder save(VOrder vOrder)


public VOrder findOne(Long id)


public Page<VOrder> findByPage(String realName,String position,String orderNo,String unionid,Long brandId,String isOffway,List<Long> brandIds,Pageable page)


}