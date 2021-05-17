import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_notice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsNotice implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 1)
@Column(name = "notice_type", length = 1)
 private  String noticeType;

@Size(max = 20)
@Column(name = "notice_role", length = 20)
 private  String noticeRole;

@Size(max = 20)
@Column(name = "notice_user", length = 20)
 private  String noticeUser;

@Size(max = 400)
@Column(name = "notice_info", length = 400)
 private  String noticeInfo;

@Size(max = 1)
@Column(name = "read_flag", length = 1)
 private  String readFlag;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

@Size(max = 200)
@Column(name = "remark", length = 200)
 private  String remark;

@Size(max = 20)
@Column(name = "reserve_first", length = 20)
 private  String reserveFirst;

@Size(max = 20)
@Column(name = "reserve_second", length = 20)
 private  String reserveSecond;

@Size(max = 20)
@Column(name = "reserve_third", length = 20)
 private  String reserveThird;

@Size(max = 10)
@Column(name = "make_user", length = 10)
 private  String makeUser;

@Column(name = "make_time")
 private  ZonedDateTime makeTime;

@Size(max = 10)
@Column(name = "modify_user", length = 10)
 private  String modifyUser;

@Column(name = "modify_time")
 private  ZonedDateTime modifyTime;


public String getReadFlag(){
    return readFlag;
}


public void setNoticeType(String noticeType){
    this.noticeType = noticeType;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeType"));

.queryParam("noticeType",noticeType);
restTemplate.put(builder.toUriString(),null);


public String getMakeUser(){
    return makeUser;
}


public String getNoticeInfo(){
    return noticeInfo;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReserveFirst"));

.queryParam("reserveFirst",reserveFirst);
restTemplate.put(builder.toUriString(),null);


public void setNoticeInfo(String noticeInfo){
    this.noticeInfo = noticeInfo;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeInfo"));

.queryParam("noticeInfo",noticeInfo);
restTemplate.put(builder.toUriString(),null);


public QmsNotice modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsNotice modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public QmsNotice noticeUser(String noticeUser){
    this.noticeUser = noticeUser;
    return this;
}


public String getNoticeUser(){
    return noticeUser;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public String getNoticeType(){
    return noticeType;
}


public QmsNotice makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsNotice makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsNotice flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsNotice noticeRole(String noticeRole){
    this.noticeRole = noticeRole;
    return this;
}


public QmsNotice compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Long getId(){
    return id;
}


public QmsNotice remark(String remark){
    this.remark = remark;
    return this;
}


public QmsNotice reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setNoticeRole(String noticeRole){
    this.noticeRole = noticeRole;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeRole"));

.queryParam("noticeRole",noticeRole);
restTemplate.put(builder.toUriString(),null);


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsNotice reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsNotice readFlag(String readFlag){
    this.readFlag = readFlag;
    return this;
}


public QmsNotice reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public QmsNotice noticeType(String noticeType){
    this.noticeType = noticeType;
    return this;
}


public String getModifyUser(){
    return modifyUser;
}


public void setReadFlag(String readFlag){
    this.readFlag = readFlag;
}


public QmsNotice noticeInfo(String noticeInfo){
    this.noticeInfo = noticeInfo;
    return this;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsNotice qmsNotice = (QmsNotice) o;
    if (qmsNotice.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsNotice.getId());
}


public void setNoticeUser(String noticeUser){
    this.noticeUser = noticeUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeUser"));

.queryParam("noticeUser",noticeUser);
restTemplate.put(builder.toUriString(),null);


@Override
public String toString(){
    return "QmsNotice{" + "id=" + getId() + ", noticeType='" + getNoticeType() + "'" + ", noticeRole='" + getNoticeRole() + "'" + ", noticeUser='" + getNoticeUser() + "'" + ", noticeInfo='" + getNoticeInfo() + "'" + ", readFlag='" + getReadFlag() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getNoticeRole(){
    return noticeRole;
}


public String getFlagStatus(){
    return flagStatus;
}


}