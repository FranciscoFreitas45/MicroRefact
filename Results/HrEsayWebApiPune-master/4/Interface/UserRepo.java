public interface UserRepo {

   public Object saveAndFlush(Object Object);
   public User findByEmpId(int empId);
}