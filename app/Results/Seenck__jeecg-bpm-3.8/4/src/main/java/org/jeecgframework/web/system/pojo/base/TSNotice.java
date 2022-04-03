package org.jeecgframework.web.system.pojo.base;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_notice")
@SuppressWarnings("serial")
public class TSNotice extends IdEntity{

 private  String noticeTitle;

 private  String noticeContent;

 private  String noticeType;

 private  String noticeLevel;

 private  Date noticeTerm;

 private  String createUser;

 private  Date createTime;

 private  String isRead;


@Column(name = "create_user", nullable = true)
public String getCreateUser(){
    return createUser;
}


@Column(name = "notice_term", nullable = true)
public Date getNoticeTerm(){
    return noticeTerm;
}


@Column(name = "create_time", nullable = true)
public Date getCreateTime(){
    return createTime;
}


@Column(name = "notice_level", nullable = true)
public String getNoticeLevel(){
    return noticeLevel;
}


public void setNoticeType(String noticeType){
    this.noticeType = noticeType;
}


@Transient
public String getIsRead(){
    return isRead;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setCreateUser(String createUser){
    this.createUser = createUser;
}


@Column(name = "notice_title", nullable = true)
public String getNoticeTitle(){
    return noticeTitle;
}


@Column(name = "notice_type", nullable = true)
public String getNoticeType(){
    return noticeType;
}


public void setIsRead(String isRead){
    this.isRead = isRead;
}


public void setNoticeTitle(String noticeTitle){
    this.noticeTitle = noticeTitle;
}


public void setNoticeContent(String noticeContent){
    this.noticeContent = noticeContent;
}


@Column(name = "notice_content", nullable = true)
public String getNoticeContent(){
    return noticeContent;
}


public void setNoticeTerm(Date noticeTerm){
    this.noticeTerm = noticeTerm;
}


public void setNoticeLevel(String noticeLevel){
    this.noticeLevel = noticeLevel;
}


}