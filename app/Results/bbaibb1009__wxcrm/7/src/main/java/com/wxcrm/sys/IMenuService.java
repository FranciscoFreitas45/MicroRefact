package com.wxcrm.sys;
 import java.util.List;
import java.util.Map;
import com.wxcrm.util.Page;
public interface IMenuService {


public List<Map<String,Object>> queryMenuToCache()
;

public WcMenu getMenuById(Integer menuId)
;

public void delMenu(String[] menuIds)
;

public void updMenu(WcMenu menu)
;

public void addMenu(WcMenu menu)
;

public List<Map<String,Object>> queryShopMenuToCache()
;

public Page queryMenu(WcMenu menu)
;

}