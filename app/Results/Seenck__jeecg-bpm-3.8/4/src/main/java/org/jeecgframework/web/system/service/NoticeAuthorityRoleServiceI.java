package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;
public interface NoticeAuthorityRoleServiceI extends CommonService{


public boolean checkAuthorityRole(String noticeId,String roleid)
;

public void saveTSNoticeAuthorityRole(TSNoticeAuthorityRole noticeAuthorityRole)
;

public void doDelTSNoticeAuthorityRole(TSNoticeAuthorityRole noticeAuthorityRole)
;

public Serializable save(T entity)
;

public boolean doDelSql(TSNoticeAuthorityRole t)
;

public boolean doUpdateSql(TSNoticeAuthorityRole t)
;

public boolean doAddSql(TSNoticeAuthorityRole t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}