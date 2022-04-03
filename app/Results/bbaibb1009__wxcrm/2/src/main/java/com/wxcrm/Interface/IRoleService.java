package com.wxcrm.Interface;
public interface IRoleService {

   public List<WcRole> queryRoleForAdminAdd();
   public List<WcRole> queryRoleForAdminUpd0(Integer adminId);
   public List<WcRole> queryRoleForAdminUpd1(Integer adminId);
}