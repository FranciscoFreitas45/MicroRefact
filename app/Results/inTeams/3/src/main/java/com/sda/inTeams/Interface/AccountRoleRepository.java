package com.sda.inTeams.Interface;
public interface AccountRoleRepository {

   public Optional<AccountRole> findByName(String name);
   public Object save(Object Object);
}