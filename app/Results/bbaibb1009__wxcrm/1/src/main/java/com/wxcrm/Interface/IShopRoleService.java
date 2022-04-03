package com.wxcrm.Interface;
public interface IShopRoleService {

   public List<WcShopRole> queryShopRoleForAdminAdd();
   public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId);
   public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId);
}