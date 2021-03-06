package org.jeecgframework.web.system.service;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
public interface DepartAuthGroupService extends CommonService{


public MiniDaoPage<Map<String,Object>> getDepartAuthGroupByUserId(int page,int rows,String userId)
;

public MiniDaoPage<Map<String,Object>> getDepartAuthRole(int page,int rows,String userId)
;

public MiniDaoPage<Map<String,Object>> getDepartAuthGroupList(int page,int rows)
;

public List<Map<String,Object>> chkDepartAuthRole()
;

public List<Map<String,Object>> chkDepartAuthGroupList(String userId)
;

}