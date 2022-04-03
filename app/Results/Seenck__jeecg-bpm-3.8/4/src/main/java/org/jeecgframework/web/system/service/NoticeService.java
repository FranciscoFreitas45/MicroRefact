package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import java.util.Date;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSNotice;
public interface NoticeService extends CommonService{


public void addNoticeAuthorityUser(String noticeId,String userid)
;

public String addNotice(String noticeTilte,String noticeContent,String noticeType,String noticeLevel,Date noticeTerm,String createUser)
;

public Serializable save(T entity)
;

public boolean doDelSql(TSNotice t)
;

public boolean doUpdateSql(TSNotice t)
;

public boolean doAddSql(TSNotice t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}