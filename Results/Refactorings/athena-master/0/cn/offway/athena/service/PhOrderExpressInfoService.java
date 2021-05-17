import cn.offway.athena.domain.PhOrderExpressInfo;
public interface PhOrderExpressInfoService {


public PhOrderExpressInfo findByExpressOrderNo(String expressOrderNo)


public PhOrderExpressInfo findByMailNo(String mailNo)


public PhOrderExpressInfo findByOrderNoAndType(String orderNo,String type)


public PhOrderExpressInfo save(PhOrderExpressInfo phOrderExpressInfo)


public PhOrderExpressInfo findOne(Long id)


}