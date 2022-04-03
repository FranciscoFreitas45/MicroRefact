package com.zammc.domain.notice;
 import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "notice")
@DynamicInsert
@DynamicUpdate
public class NoticeEntity {

 private  long noticeId;

 private  String noticeName;

 private  String noticeUrl;

 private  byte noticeStatus;

 private  int noticeSort;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  int version;

 private  byte dataStatus;


@Basic
@Column(name = "version")
@Version
public int getVersion(){
    return version;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


public void setNoticeStatus(byte noticeStatus){
    this.noticeStatus = noticeStatus;
}


public void setNoticeSort(int noticeSort){
    this.noticeSort = noticeSort;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setVersion(int version){
    this.version = version;
}


@Basic
@Column(name = "notice_status")
public byte getNoticeStatus(){
    return noticeStatus;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "notice_name")
public String getNoticeName(){
    return noticeName;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


@Basic
@Column(name = "notice_sort")
public int getNoticeSort(){
    return noticeSort;
}


@Basic
@Column(name = "notice_url")
public String getNoticeUrl(){
    return noticeUrl;
}


public void setNoticeUrl(String noticeUrl){
    this.noticeUrl = noticeUrl;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


public void setNoticeId(long noticeId){
    this.noticeId = noticeId;
}


@Override
public String toString(){
    return "NoticeEntity{" + "noticeId=" + noticeId + ", noticeName='" + noticeName + '\'' + ", noticeUrl='" + noticeUrl + '\'' + ", noticeStatus=" + noticeStatus + ", noticeSort=" + noticeSort + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + '}';
}


@Id
@Column(name = "notice_id")
public long getNoticeId(){
    return noticeId;
}


public void setNoticeName(String noticeName){
    this.noticeName = noticeName;
}


}