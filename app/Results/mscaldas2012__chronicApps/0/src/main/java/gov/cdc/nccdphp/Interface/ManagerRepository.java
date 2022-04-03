package gov.cdc.nccdphp.Interface;
public interface ManagerRepository {

   public List<Manager> findByActiveTrue();
   public Object findAll(Object Object);
}