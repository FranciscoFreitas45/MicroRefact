package Interface;
public interface AreaTypeRepository {

   public List<AreaType> findByOrgi(String orgi);
   public AreaType findByIdAndOrgi(String id,String orgi);
}