public interface QmsProductRepository {

   public List<QmsProduct> findByProductNumAndMaterielId(String productNum,Integer materielId);
   public Object save(Object Object);
}