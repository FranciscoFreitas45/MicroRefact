package Interface;
public interface OrganRepository {

   public List<Organ> findByOrgi(String orgi);
   public Object save(Object Object);
   public Organ findByIdAndOrgi(String paramString,String orgi);
   public Object delete(Object Object);
   public List<Organ> findAll(Specification<Organ> spec);
   public List<Organ> findByOrgiAndOrgid(String orgi,String orgid);
}