package org.live.Interface;
public interface RoleRepository {

   public List<Role> findRolesByUsername(String username);
}