package org.jeecgframework.web.system.service.impl;
 import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSNotice;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;
import org.jeecgframework.web.system.pojo.base.TSNoticeReadUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.NoticeService;
import org.springframework.stereotype.Service;
@Service("noticeService")
public class NoticeServiceImpl extends CommonServiceImplimplements NoticeService{


public void addNoticeAuthorityUser(String noticeId,String userid){
    if (noticeId != null && userid != null) {
        TSNoticeAuthorityUser entity = new TSNoticeAuthorityUser();
        entity.setNoticeId(noticeId);
        TSUser tsuser = new TSUser();
        tsuser.setId(userid);
        entity.setUser(tsuser);
        this.saveOrUpdate(entity);
    }
}


public String addNotice(String noticeTitle,String noticeContent,String noticeType,String noticeLevel,Date noticeTerm,String createUser){
    String noticeId = null;
    TSNotice notice = new TSNotice();
    notice.setNoticeTitle(noticeTitle);
    notice.setNoticeContent(noticeContent);
    notice.setNoticeType(noticeType);
    notice.setNoticeLevel(noticeLevel);
    notice.setNoticeTerm(noticeTerm);
    notice.setCreateUser(createUser);
    notice.setCreateTime(new Date());
    this.save(notice);
    noticeId = notice.getId();
    return noticeId;
}


public Serializable save(T entity){
    Serializable t = super.save(entity);
    // 执行新增操作配置的sql增强
    this.doAddSql((TSNotice) entity);
    return t;
}


public boolean doDelSql(TSNotice t){
    return true;
}


public boolean doUpdateSql(TSNotice t){
    return true;
}


public boolean doAddSql(TSNotice t){
    return true;
}


public String replaceVal(String sql,TSNotice t){
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{notice_title}", String.valueOf(t.getNoticeTitle()));
    sql = sql.replace("#{notice_content}", String.valueOf(t.getNoticeContent()));
    sql = sql.replace("#{notice_type}", String.valueOf(t.getNoticeType()));
    sql = sql.replace("#{notice_level}", String.valueOf(t.getNoticeLevel()));
    sql = sql.replace("#{notice_term}", String.valueOf(t.getNoticeTerm()));
    sql = sql.replace("#{create_user}", String.valueOf(t.getCreateUser()));
    sql = sql.replace("#{create_time}", String.valueOf(t.getCreateTime()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    return sql;
}


public void delete(T entity){
    // update-begin--Author:xuguojie  Date:20160406 for：#1020 【平台bug】通知公告删除时，没有考虑t_s_notice_read_user表的记
    TSNotice notice = (TSNotice) entity;
    super.deleteAllEntitie(super.findByProperty(TSNoticeReadUser.class, "noticeId", notice.getId()));
    super.deleteAllEntitie(super.findByProperty(TSNoticeAuthorityUser.class, "noticeId", notice.getId()));
    super.deleteAllEntitie(super.findByProperty(TSNoticeAuthorityRole.class, "noticeId", notice.getId()));
    super.delete(notice);
    // 执行删除操作配置的sql增强
    this.doDelSql(notice);
// update-begin--Author:xuguojie  Date:20160406 for：#1020 【平台bug】通知公告删除时，没有考虑t_s_notice_read_user表的记
}


public void saveOrUpdate(T entity){
    super.saveOrUpdate(entity);
    // 执行更新操作配置的sql增强
    this.doUpdateSql((TSNotice) entity);
}


}