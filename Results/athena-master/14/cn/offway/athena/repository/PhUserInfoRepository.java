import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhUserInfo;
public interface PhUserInfoRepository extends JpaRepository<PhUserInfo, Long> {


public PhUserInfo findByUnionid(String unionid)


}