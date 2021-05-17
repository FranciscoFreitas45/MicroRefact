public interface RoleRepository {

   public Optional<Role> findByRoleName(String roleName);
}