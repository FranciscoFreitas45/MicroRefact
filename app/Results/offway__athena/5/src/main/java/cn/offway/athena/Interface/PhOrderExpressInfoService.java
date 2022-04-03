package cn.offway.athena.Interface;
public interface PhOrderExpressInfoService {

   public PhOrderExpressInfo findByExpressOrderNo(String expressOrderNo);
   public PhOrderExpressInfo save(PhOrderExpressInfo phOrderExpressInfo);
}