package com.gs.Interface;
public interface RoleService {

   public Object insert(Object Object);
   public Object update(Object Object);
   public int updateStatus(Role role);
   public List<Role> queryAll(String rolestatus);
   public int count(String roleStatus);
   public List<Role> queryByPager(String roleStatus,Pager pager);
}