import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhAddressService;
import cn.offway.athena.domain.PhAddress;
import cn.offway.athena.repository.PhAddressRepository;
@Service
public class PhAddressServiceImpl implements PhAddressService,cn.offway.athena.service.PhAddressService{

 private  Logger logger;

@Autowired
 private  PhAddressRepository phAddressRepository;


@Override
public PhAddress save(PhAddress phAddress){
    return phAddressRepository.save(phAddress);
}


@Override
public PhAddress findOne(Long id){
    return phAddressRepository.findOne(id);
}


@Override
public void delete(Long id){
    phAddressRepository.delete(id);
}


}