package com.vino.scaffold.shiro.Interface;
public interface ResourceRepository {

   public Object findOne(Object Object);
   public Resource findByName(String name);
   public Object save(Object Object);
   public Object findAll(Object Object);
   public void deleteAssociateById(Long resourceIds);
}