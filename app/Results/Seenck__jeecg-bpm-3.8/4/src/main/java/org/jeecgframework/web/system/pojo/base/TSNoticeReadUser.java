package org.jeecgframework.web.system.pojo.base;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_notice_read_user")
@SuppressWarnings("serial")
public class TSNoticeReadUser extends IdEntity{

 private  String noticeId;

 private  String userId;

 private  Integer isRead;

 private  Integer delFlag;

 private  Date createTime;


@Column(name = "create_time", nullable = true)
public Date getCreateTime(){
    return createTime;
}


public void setIsRead(Integer isRead){
    this.isRead = isRead;
}


@Column(name = "del_flag", nullable = false)
public Integer getDelFlag(){
    return delFlag;
}


@Column(name = "is_read", nullable = false)
public Integer getIsRead(){
    return isRead;
}


public void setNoticeId(String noticeId){
    this.noticeId = noticeId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "notice_id", nullable = true)
public String getNoticeId(){
    return noticeId;
}


@Column(name = "user_id", nullable = true)
public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setDelFlag(Integer delFlag){
    this.delFlag = delFlag;
}


}