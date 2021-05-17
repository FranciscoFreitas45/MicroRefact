import java.util.List;
import cn.offway.athena.domain.PhRoleadmin;
public interface PhRoleadminService {


public PhRoleadmin save(PhRoleadmin phRoleadmin)


public PhRoleadmin findOne(Long id)


public List<Long> findRoleIdByAdminId(Long adminId)


}