package com.csquard.mregister.Interface;
public interface RoleRepository {

   public Optional<Roles> findByName(RoleName roleName);
   public Object orElseThrow(Object Object);
}