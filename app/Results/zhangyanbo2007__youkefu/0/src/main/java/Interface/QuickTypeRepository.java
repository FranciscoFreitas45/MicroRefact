package Interface;
public interface QuickTypeRepository {

   public List<QuickType> findByOrgiAndQuicktype(String orgi,String quicktype);
   public List<QuickType> findByOrgiAndQuicktypeAndCreater(String orgi,String quicktype,String creater);
   public QuickType findByIdAndOrgi(String id,String orgi);
   public int countByOrgiAndNameAndParentid(String orgi,String name,String parentid);
   public Object save(Object Object);
   public Object delete(Object Object);
}