public interface PhGoodsImageService {

   public int deleteByGoodsIds(List<Long> goodsIds);
   public List<PhGoodsImage> findByGoodsId(Long goodsId);
   public void delete(List<PhGoodsImage> phGoodsImages);
   public List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages);
   public PhGoodsImage findOne(Long id);
}