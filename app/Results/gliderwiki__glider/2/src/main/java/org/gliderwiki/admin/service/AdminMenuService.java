package org.gliderwiki.admin.service;
 import java.util.List;
import org.gliderwiki.web.domain.WeMenu;
public interface AdminMenuService {


public List<WeMenu> getWeMenuTypeDWR(String menuType)
;

public String deleteMenuInfoDWR(Integer weMenuIdx)
;

public List<WeMenu> getSubMenuListDWR(Integer weMenuIdx)
;

public WeMenu getMenuInfo(Integer weMenuIdx)
;

public String updateMenuDWR(Integer weMenuIdx,String weMenuName,String weMenuType,String weMenuUrl,String weMenuOrderIdx,String weAccessLevel)
;

public String insertMenuDWR(String weMenuName,String weMenuType,String weMenuUrl,String weMenuOrderIdx,String weAccessLevel)
;

}