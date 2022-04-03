package Interface;
public interface OrganRepository {

   public List<Organ> findByOrgi(String orgi);
   public List<Organ> findByOrgiAndParent(String orgi,String parent);
   public List<Organ> findByOrgiAndOrgid(String orgi,String orgid);
}