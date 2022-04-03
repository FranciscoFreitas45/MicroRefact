package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_organization_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsOrganizationInfo implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "parent_cd", length = 10)
 private  String parentCd;

@Size(max = 10)
@Column(name = "organization_cd", length = 10)
 private  String organizationCd;

@Size(max = 100)
@Column(name = "organization_name", length = 100)
 private  String organizationName;

@Size(max = 1)
@Column(name = "attribute", length = 1)
 private  String attribute;

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


public String getParentCd(){
    return parentCd;
}


public String getOrganizationName(){
    return organizationName;
}


public Long getId(){
    return id;
}


public QmsOrganizationInfo organizationCd(String organizationCd){
    this.organizationCd = organizationCd;
    return this;
}


public QmsOrganizationInfo remark(String remark){
    this.remark = remark;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsOrganizationInfo modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setAttribute(String attribute){
    this.attribute = attribute;
}


public String getAttribute(){
    return attribute;
}


public QmsOrganizationInfo modifyTime(ZonedDateTime modifyTime){
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


public void setId(Long id){
    this.id = id;
}


public QmsOrganizationInfo attribute(String attribute){
    this.attribute = attribute;
    return this;
}


public QmsOrganizationInfo parentCd(String parentCd){
    this.parentCd = parentCd;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public QmsOrganizationInfo organizationName(String organizationName){
    this.organizationName = organizationName;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public String getModifyUser(){
    return modifyUser;
}


public void setOrganizationName(String organizationName){
    this.organizationName = organizationName;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsOrganizationInfo makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setParentCd(String parentCd){
    this.parentCd = parentCd;
}


public QmsOrganizationInfo makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsOrganizationInfo flagStatus(String flagStatus){
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
    QmsOrganizationInfo qmsOrganizationInfo = (QmsOrganizationInfo) o;
    if (qmsOrganizationInfo.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsOrganizationInfo.getId());
}


public void setOrganizationCd(String organizationCd){
    this.organizationCd = organizationCd;
}


@Override
public String toString(){
    return "QmsOrganizationInfo{" + "id=" + getId() + ", parentCd='" + getParentCd() + "'" + ", organizationCd='" + getOrganizationCd() + "'" + ", organizationName='" + getOrganizationName() + "'" + ", attribute='" + getAttribute() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getOrganizationCd(){
    return organizationCd;
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsOrganizationInfo compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}