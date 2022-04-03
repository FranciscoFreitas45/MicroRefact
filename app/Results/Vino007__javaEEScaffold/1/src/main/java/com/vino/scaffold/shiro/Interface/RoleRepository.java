package com.vino.scaffold.shiro.Interface;
public interface RoleRepository {

   public Object findOne(Object Object);
   public Role findByName(String name);
   public Object save(Object Object);
   public Object findAll(Object Object);
   public void deleteAssociateById(Long roleIds);
}