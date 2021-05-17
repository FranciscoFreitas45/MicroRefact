import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhOrderExpressInfoService;
import cn.offway.athena.domain.PhOrderExpressInfo;
import cn.offway.athena.repository.PhOrderExpressInfoRepository;
@Service
public class PhOrderExpressInfoServiceImpl implements cn.offway.athena.service.PhOrderExpressInfoService,PhOrderExpressInfoService{

 private  Logger logger;

@Autowired
 private  PhOrderExpressInfoRepository phOrderExpressInfoRepository;


@Override
public PhOrderExpressInfo findByExpressOrderNo(String expressOrderNo){
    return phOrderExpressInfoRepository.findByExpressOrderNo(expressOrderNo);
}


@Override
public PhOrderExpressInfo findByMailNo(String mailNo){
    return phOrderExpressInfoRepository.findByMailNo(mailNo);
}


@Override
public PhOrderExpressInfo findByOrderNoAndType(String orderNo,String type){
    return phOrderExpressInfoRepository.findByOrderNoAndType(orderNo, type);
}


@Override
public PhOrderExpressInfo save(PhOrderExpressInfo phOrderExpressInfo){
    return phOrderExpressInfoRepository.save(phOrderExpressInfo);
}


@Override
public PhOrderExpressInfo findOne(Long id){
    return phOrderExpressInfoRepository.findOne(id);
}


}