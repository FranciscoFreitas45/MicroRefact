package com.wxcrm.sys;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import com.wxcrm.util.Page;
public interface IAdminService {


public List<Map<String,Object>> queryAdminMenus(Integer adminId,String menuLevel)
;

public List<Map<String,Object>> queryAdminNameToCache()
;

public void addAdmin(WcAdmin admin)
;

public WcAdmin getAdminById(Integer id)
;

public Page queryAdmin(WcAdmin admin)
;

public void updLoginTime(Integer adminId)
;

public void updAdmin(WcAdmin admin)
;

public boolean chkUsernameUnique(String username)
;

public List<Map<String,Object>> queryShopAdminNameToCache()
;

public WcAdmin adminLogin(WcAdmin admin)
;

}