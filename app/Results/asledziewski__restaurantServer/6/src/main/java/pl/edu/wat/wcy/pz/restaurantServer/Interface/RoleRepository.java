package pl.edu.wat.wcy.pz.restaurantServer.Interface;
public interface RoleRepository {

   public Optional<Role> findByRoleName(String roleName);
}