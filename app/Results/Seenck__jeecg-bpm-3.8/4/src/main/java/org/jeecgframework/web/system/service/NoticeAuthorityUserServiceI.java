package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;
public interface NoticeAuthorityUserServiceI extends CommonService{


public boolean checkAuthorityUser(String noticeId,String userid)
;

public void doDelNoticeAuthorityUser(TSNoticeAuthorityUser noticeAuthorityUser)
;

public Serializable save(T entity)
;

public void saveNoticeAuthorityUser(TSNoticeAuthorityUser noticeAuthorityUser)
;

public boolean doDelSql(TSNoticeAuthorityUser t)
;

public boolean doUpdateSql(TSNoticeAuthorityUser t)
;

public boolean doAddSql(TSNoticeAuthorityUser t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}