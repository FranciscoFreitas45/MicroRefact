package Interface;
public interface OrganRepository {

   public List<Organ> findByOrgi(String orgi);
   public List<Organ> findAll(Specification<Organ> spec);
   public Organ findByIdAndOrgi(String paramString,String orgi);
}