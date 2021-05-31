import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhOrderExpressDetailService;
import cn.offway.athena.domain.PhOrderExpressDetail;
import cn.offway.athena.repository.PhOrderExpressDetailRepository;
@Service
public class PhOrderExpressDetailServiceImpl implements PhOrderExpressDetailService,cn.offway.athena.service.PhOrderExpressDetailService{

 private  Logger logger;

@Autowired
 private  PhOrderExpressDetailRepository phOrderExpressDetailRepository;


@Override
public PhOrderExpressDetail save(PhOrderExpressDetail phOrderExpressDetail){
    return phOrderExpressDetailRepository.save(phOrderExpressDetail);
}


@Override
public PhOrderExpressDetail findOne(Long id){
    return phOrderExpressDetailRepository.findOne(id);
}


}