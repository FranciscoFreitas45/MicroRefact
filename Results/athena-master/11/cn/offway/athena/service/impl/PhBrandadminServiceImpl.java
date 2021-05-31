import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.domain.PhBrandadmin;
import cn.offway.athena.repository.PhBrandadminRepository;
import cn.offway.athena.service.PhBrandadminService;
@Service
public class PhBrandadminServiceImpl implements cn.offway.athena.service.PhBrandadminService,PhBrandadminService{

 private  Logger logger;

@Autowired
 private  PhBrandadminRepository phBrandadminRepository;


@Override
public PhBrandadmin save(PhBrandadmin phBrandadmin){
    return phBrandadminRepository.save(phBrandadmin);
}


@Override
public PhBrandadmin findOne(Long id){
    return phBrandadminRepository.findOne(id);
}


@Override
public List<Long> findBrandIdByAdminId(Long adminId){
    return phBrandadminRepository.findBrandIdByAdminId(adminId);
}


}