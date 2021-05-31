import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhCode;
public interface PhCodeService {


public PhCode save(PhCode phCode)


public PhCode findOne(Long id)


public void coddel(Long id)


public Page<PhCode> findByPage(String status,String code,String phone,Pageable page)


public PhCode findByCodeAndStatusAndPhoneAndPositionAndRealName(String code,String status,String phone,String position,String realName)


}