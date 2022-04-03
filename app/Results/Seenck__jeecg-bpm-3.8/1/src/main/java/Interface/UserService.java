package Interface;
public interface UserService {

   public Object save(Object Object);
   public void saveOrUpdate(TSUser user,String[] orgIds,String[] roleIds);
   public Object delete(Object Object);
}