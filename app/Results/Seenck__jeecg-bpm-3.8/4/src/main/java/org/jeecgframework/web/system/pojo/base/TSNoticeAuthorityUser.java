package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_notice_authority_user")
@SuppressWarnings("serial")
public class TSNoticeAuthorityUser extends IdEntity{

 private  String noticeId;

 private  TSUser user;


@ManyToOne
@JoinColumn(name = "user_id")
public TSUser getUser(){
    return user;
}


public void setNoticeId(String noticeId){
    this.noticeId = noticeId;
}


public void setUser(TSUser user){
    this.user = user;
}


@Column(name = "notice_id", nullable = true)
public String getNoticeId(){
    return noticeId;
}


}