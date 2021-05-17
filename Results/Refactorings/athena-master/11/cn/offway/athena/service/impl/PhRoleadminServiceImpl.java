import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.domain.PhRoleadmin;
import cn.offway.athena.repository.PhRoleadminRepository;
import cn.offway.athena.service.PhRoleadminService;
@Service
public class PhRoleadminServiceImpl implements PhRoleadminService,cn.offway.athena.service.PhRoleadminService{

 private  Logger logger;

@Autowired
 private  PhRoleadminRepository phRoleadminRepository;


@Override
public PhRoleadmin save(PhRoleadmin phRoleadmin){
    return phRoleadminRepository.save(phRoleadmin);
}


@Override
public PhRoleadmin findOne(Long id){
    return phRoleadminRepository.findOne(id);
}


@Override
public List<Long> findRoleIdByAdminId(Long adminId){
    return phRoleadminRepository.findRoleIdByAdminId(adminId);
}


}