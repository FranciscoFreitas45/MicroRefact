package com.hmm.Interface;
public interface GroupRoleDao {

   public GroupRole findByGroupId(String groupId);
   public Object save(Object Object);
   public Object findById(Object Object);
   public Object existsById(Object Object);
   public Object deleteById(Object Object);
   public Object findAll(Object Object);
   public Object count(Object Object);
   public Object findAllById(Object Object);
   public Object deleteAll(Object Object);
   public GroupRole findByGroupName(String groupName);
}