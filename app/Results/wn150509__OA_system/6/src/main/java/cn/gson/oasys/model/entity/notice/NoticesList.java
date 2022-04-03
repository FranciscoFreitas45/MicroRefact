package cn.gson.oasys.model.entity.notice;
 import java.util.Date;
import javax.persistence;
@Entity
@Table(name = "aoa_notice_list")
public class NoticesList {

@Id
@Column(name = "notice_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long noticeId;

@Column(name = "user_id")
 private  Long userId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "status_id")
 private  Long statusId;

 private  String title;

 private  String content;

 private  String url;

@Column(name = "is_top")
 private  Boolean top;

@Column(name = "notice_time")
 private  Date noticeTime;

@Column(name = "modify_time")
 private  Date modifyTime;

public NoticesList() {
}
public void setContent(String content){
    this.content = content;
}


public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public Date getNoticeTime(){
    return noticeTime;
}


public String getContent(){
    return content;
}


public Date getModifyTime(){
    return modifyTime;
}


public void setTitle(String title){
    this.title = title;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Long getTypeId(){
    return typeId;
}


public void setUrl(String url){
    this.url = url;
}


public Long getStatusId(){
    return statusId;
}


public void setModifyTime(Date modifyTime){
    this.modifyTime = modifyTime;
}


public String getUrl(){
    return url;
}


public void setTop(Boolean top){
    this.top = top;
}


public String getTitle(){
    return title;
}


public Boolean getTop(){
    return top;
}


public void setNoticeId(Long noticeId){
    this.noticeId = noticeId;
}


public void setNoticeTime(Date noticeTime){
    this.noticeTime = noticeTime;
}


@Override
public String toString(){
    return "NoticesList [noticeId=" + noticeId + ", userId=" + userId + ", typeId=" + typeId + ", statusId=" + statusId + ", title=" + title + ", content=" + content + ", url=" + url + ", top=" + top + ", noticeTime=" + noticeTime + ", modifyTime=" + modifyTime + "]";
}


public Long getNoticeId(){
    return noticeId;
}


public Long getUserId(){
    return userId;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}