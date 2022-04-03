package cn.offway.athena.Interface;
public interface PhOrderGoodsService {

   public Page<PhOrderGoods> findByBrandId(String brandId,Pageable page);
}