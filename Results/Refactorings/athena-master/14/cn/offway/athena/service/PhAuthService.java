import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import cn.offway.athena.domain.PhAuth;
public interface PhAuthService {


public boolean authUpdate(Long id,String status,String approvalContent,Authentication authentication)


public PhAuth save(PhAuth phAuth)


public PhAuth findOne(Long id)


public Page<PhAuth> findByPage(String status,String nickName,String unionid,Pageable page)


}