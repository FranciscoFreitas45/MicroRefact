import java.util.List;
import cn.offway.athena.domain.PhBrandadmin;
public interface PhBrandadminService {


public PhBrandadmin save(PhBrandadmin phBrandadmin)


public PhBrandadmin findOne(Long id)


public List<Long> findBrandIdByAdminId(Long adminId)


}