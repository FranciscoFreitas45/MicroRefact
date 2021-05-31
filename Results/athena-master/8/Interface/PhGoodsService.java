public interface PhGoodsService {

   public PhGoods findOne(Long id);
   public List<PhGoods> findAll(String name,Object value,String brandId);
}