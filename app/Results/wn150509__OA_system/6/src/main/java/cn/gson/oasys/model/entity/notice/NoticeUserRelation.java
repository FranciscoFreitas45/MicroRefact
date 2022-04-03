package cn.gson.oasys.model.entity.notice;
 import javax.persistence;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_notice_user_relation")
public class NoticeUserRelation {

@Id
@Column(name = "relatin_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long noticeUserRelatinId;

@ManyToOne
@JoinColumn(name = "relatin_notice_id")
 private  NoticesList noticeId;

@Transient
 private  User userId;

@Column(name = "is_read")
 private  Boolean read;

@Column(name = "userIdv2")
 private Long userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public NoticeUserRelation() {
}public NoticeUserRelation(NoticesList noticeId, User userId, Boolean read) {
    super();
    this.noticeId = noticeId;
    this.userId = userId;
    this.read = read;
}
public void setRead(Boolean read){
    this.read = read;
}


public Boolean getRead(){
    return read;
}


public void setNoticeUserRelatinId(Long noticeUserRelatinId){
    this.noticeUserRelatinId = noticeUserRelatinId;
}


public void setNoticeId(NoticesList noticeId){
    this.noticeId = noticeId;
}


@Override
public String toString(){
    return "NoticeUserRelation [noticeUserRelatinId=" + noticeUserRelatinId + ", read=" + read + "]";
}


public Long getNoticeUserRelatinId(){
    return noticeUserRelatinId;
}


public NoticesList getNoticeId(){
    return noticeId;
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}