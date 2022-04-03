package cn.offway.athena.Interface;
public interface PhOrderGoodsService {

   public List<PhOrderGoods> findByOrderNo(String orderNo);
}