public interface PhGoodsService {

   public Page<PhGoods> findByPage(String name,Long brandId,String isOffway,List<Long> brandIds,String status,String type,String category,Pageable page);
   public List<PhGoods> save(List<PhGoods> phGoods);
   public PhGoods findOne(Long id);
   public List<PhGoods> findAll(String name,Object value,String brandId);
   public boolean imagesDelete(Long goodsImageId);
   public List<PhGoods> findByBrandId(Long brandId);
   public String delete(List<Long> goodsIds);
}