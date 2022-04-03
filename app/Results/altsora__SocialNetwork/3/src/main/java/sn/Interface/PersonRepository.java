package sn.Interface;
public interface PersonRepository {

   public Object existsById(Object Object);
   public Optional<Person> findByEmail(String email);
   public Object save(Object Object);
   public Object saveAndFlush(Object Object);
   public Object deleteById(Object Object);
   public Object findById(Object Object);
   public int getTotalCountUsers();
}