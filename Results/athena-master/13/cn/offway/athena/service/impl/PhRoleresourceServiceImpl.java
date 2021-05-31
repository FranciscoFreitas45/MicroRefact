import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.domain.PhRoleresource;
import cn.offway.athena.repository.PhRoleresourceRepository;
import cn.offway.athena.service.PhRoleresourceService;
@Service
public class PhRoleresourceServiceImpl implements cn.offway.athena.service.PhRoleresourceService,PhRoleresourceService{

 private  Logger logger;

@Autowired
 private  PhRoleresourceRepository phRoleresourceRepository;


@Override
public List<Long> findSourceIdByRoleId(Long roleId){
    return phRoleresourceRepository.findSourceIdByRoleId(roleId);
}


@Override
public PhRoleresource save(PhRoleresource phRoleresource){
    return phRoleresourceRepository.save(phRoleresource);
}


@Override
public PhRoleresource findOne(Long id){
    return phRoleresourceRepository.findOne(id);
}


}