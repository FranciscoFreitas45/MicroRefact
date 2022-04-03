package pl.edu.wat.wcy.pz.restaurantServer.Interface;
public interface UserRepository {

   public Object findAll(Object Object);
   public Object findById(Object Object);
   public Object save(Object Object);
   public Object deleteById(Object Object);
}