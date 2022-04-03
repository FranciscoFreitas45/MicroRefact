package org.live.Interface;
public interface PermissionRepository {

   public List<Permission> findPermissionsByUsername(String username);
}