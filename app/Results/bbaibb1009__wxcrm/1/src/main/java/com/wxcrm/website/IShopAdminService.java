package com.wxcrm.website;
 import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import com.wxcrm.util.Page;
public interface IShopAdminService {


public void updShopLoginTime(Integer adminId)
;

public boolean chkShopUsernameUnique(String username)
;

public List<Map<String,Object>> queryShopAdminMenus(Integer adminId,String menuLevel)
;

public WcShopAdmin getShopAdminById(Integer id)
;

public Page queryShopAdmin(WcShopAdmin admin)
;

public void addShopAdmin(WcShopAdmin admin)
;

public void updShopAdmin(WcShopAdmin admin)
;

public List<Map<String,Object>> queryShopAdminNameToCache()
;

}