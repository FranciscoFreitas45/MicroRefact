package com.wxcrm.website;
 import java.util.List;
import com.wxcrm.util.Page;
public interface IShopRoleService {


public void updShopRole(WcShopRole role)
;

public List<WcShopRole> queryShopRoleForAdminAdd()
;

public void delShopRole(String[] roleIds)
;

public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId)
;

public void addShopRole(WcShopRole role)
;

public Page queryShopRole(WcShopRole role)
;

public List<String> queryShopRoleMenusById(Integer roleId)
;

public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId)
;

public WcShopRole getShopRoleById(Integer roleId)
;

}