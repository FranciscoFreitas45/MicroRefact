package com.yalcin.Interface;
public interface RoleRepository {

   public Optional<Role> findByRole(Roles role);
   public Object orElseThrow(Object Object);
}