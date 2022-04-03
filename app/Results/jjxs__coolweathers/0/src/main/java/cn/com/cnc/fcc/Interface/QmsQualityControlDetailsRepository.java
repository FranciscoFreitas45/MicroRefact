package cn.com.cnc.fcc.Interface;
public interface QmsQualityControlDetailsRepository {

   public Object saveAll(Object Object);
   public Object save(Object Object);
   public QmsQualityControlDetails findByIdAndFlagStatus(Long id,String string);
   public List<QmsQualityControlDetails> findByBomTechnologyIdAndFlagStatus(Integer id,String flagStatus);
}