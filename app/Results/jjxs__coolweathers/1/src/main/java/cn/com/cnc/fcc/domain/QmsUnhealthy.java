package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_unhealthy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsUnhealthy implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "unhealthy_cd", length = 10)
 private  String unhealthyCd;

@Size(max = 100)
@Column(name = "unhealthy_name", length = 100)
 private  String unhealthyName;

@Size(max = 10)
@Column(name = "parent_cd", length = 10)
 private  String parentCd;

@Size(max = 1)
@Column(name = "is_use", length = 1)
 private  String isUse;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

@Size(max = 200)
@Column(name = "remark", length = 200)
 private  String remark;

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


public QmsUnhealthy isUse(String isUse){
    this.isUse = isUse;
    return this;
}


public String getParentCd(){
    return parentCd;
}


public String getUnhealthyCd(){
    return unhealthyCd;
}


public Long getId(){
    return id;
}


public QmsUnhealthy remark(String remark){
    this.remark = remark;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsUnhealthy unhealthyName(String unhealthyName){
    this.unhealthyName = unhealthyName;
    return this;
}


public QmsUnhealthy modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setIsUse(String isUse){
    this.isUse = isUse;
}


public QmsUnhealthy modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getRemark(){
    return remark;
}


public void setUnhealthyName(String unhealthyName){
    this.unhealthyName = unhealthyName;
}


public void setId(Long id){
    this.id = id;
}


public String getIsUse(){
    return isUse;
}


public QmsUnhealthy parentCd(String parentCd){
    this.parentCd = parentCd;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public String getUnhealthyName(){
    return unhealthyName;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setUnhealthyCd(String unhealthyCd){
    this.unhealthyCd = unhealthyCd;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsUnhealthy makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setParentCd(String parentCd){
    this.parentCd = parentCd;
}


public QmsUnhealthy makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsUnhealthy unhealthyCd(String unhealthyCd){
    this.unhealthyCd = unhealthyCd;
    return this;
}


public QmsUnhealthy flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
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
    QmsUnhealthy qmsUnhealthy = (QmsUnhealthy) o;
    if (qmsUnhealthy.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsUnhealthy.getId());
}


@Override
public String toString(){
    return "QmsUnhealthy{" + "id=" + getId() + ", unhealthyCd='" + getUnhealthyCd() + "'" + ", unhealthyName='" + getUnhealthyName() + "'" + ", parentCd='" + getParentCd() + "'" + ", isUse='" + getIsUse() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsUnhealthy compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}