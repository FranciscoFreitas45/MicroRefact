public interface PhOrderGoodsService {

   public List<PhOrderGoods> findByOrderNo(String orderNo);
   public PhOrderGoods findOne(Long id);
   public PhOrderGoods save(PhOrderGoods phOrderGoods);
   public List<PhOrderGoods> findNormalByOrderNo(String orderNo);
}