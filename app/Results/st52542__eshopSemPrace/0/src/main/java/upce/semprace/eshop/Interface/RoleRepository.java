package upce.semprace.eshop.Interface;
public interface RoleRepository {

   public Optional<Role> findByName(RoleName roleName);
   public Object orElseThrow(Object Object);
}