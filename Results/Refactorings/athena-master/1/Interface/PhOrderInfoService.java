public interface PhOrderInfoService {

   public PhOrderInfo findByOrderNo(String orderNo);
   public PhOrderInfo save(PhOrderInfo phOrderInfo);
}