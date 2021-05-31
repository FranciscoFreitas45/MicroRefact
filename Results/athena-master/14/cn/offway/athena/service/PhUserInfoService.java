import cn.offway.athena.domain.PhUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PhUserInfoService {


public PhUserInfo findByUnionid(String unionid)


public PhUserInfo save(PhUserInfo phUserInfo)


public PhUserInfo findOne(Long id)


public Page<PhUserInfo> findByPage(String nickname,String unionid,String phone,String id,String real_name,String isAuth,Pageable page)


}