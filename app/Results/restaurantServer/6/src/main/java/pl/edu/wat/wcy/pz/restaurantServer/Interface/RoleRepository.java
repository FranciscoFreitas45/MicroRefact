package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Role;
import java.util.*;
public interface RoleRepository {

   public Optional<Role> findByRoleName(String roleName);
}