package com.wxcrm.common;
 import java.util.List;
import java.util.Map;
public interface IMemcachedService {


public void setShopAdminNameAll(List<Map<String,Object>> adminNameList)
;

public List<Map<String,Object>> getShopAdminNameAll()
;

public void init()
;

public List<Map<String,Object>> getAdminNameAll()
;

public void setAdminNameAll(List<Map<String,Object>> adminNameList)
;

}