package com.empl.mgr.Interface;
public interface AccountRoleDao {

   public List<TeAccountRole> findByAcctNameAndRoleLabel(String account,String roleLabel);
   public void delByAcctNameAndRoleLabel(String account,String roleLabel);
   public Object save(Object Object);
}