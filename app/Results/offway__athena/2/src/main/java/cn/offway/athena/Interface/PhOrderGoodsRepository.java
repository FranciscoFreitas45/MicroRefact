package cn.offway.athena.Interface;
public interface PhOrderGoodsRepository {

   public int countByGoodsIds(List<Long> goodsIds);
}