package com.wxcrm.website;
 import java.util.List;
import java.util.Map;
import com.wxcrm.util.Page;
public interface IShopMenuService {


public void delShopMenu(String[] menuIds)
;

public void addShopMenu(WcShopMenu menu)
;

public void updShopMenu(WcShopMenu menu)
;

public List<Map<String,Object>> queryShopMenuToCache()
;

public WcShopMenu getShopMenuById(Integer menuId)
;

public Page queryShopMenu(WcShopMenu menu)
;

}