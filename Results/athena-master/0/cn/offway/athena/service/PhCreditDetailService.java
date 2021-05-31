import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhCreditDetail;
public interface PhCreditDetailService {


public PhCreditDetail save(PhCreditDetail phCreditDetail)


public PhCreditDetail findOne(Long id)


public Page<PhCreditDetail> findByPage(String orderNo,String unionid,String type,Pageable page)


}