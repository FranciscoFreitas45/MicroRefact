package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_unit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsUnit implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "unit_cd", length = 10)
 private  String unitCd;

@Size(max = 100)
@Column(name = "unit_name", length = 100)
 private  String unitName;

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


public String getUnitName(){
    return unitName;
}


public String getUnitCd(){
    return unitCd;
}


public Long getId(){
    return id;
}


public QmsUnit remark(String remark){
    this.remark = remark;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsUnit modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsUnit modifyTime(ZonedDateTime modifyTime){
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


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public QmsUnit unitCd(String unitCd){
    this.unitCd = unitCd;
    return this;
}


public QmsUnit unitName(String unitName){
    this.unitName = unitName;
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


public void setUnitName(String unitName){
    this.unitName = unitName;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsUnit makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsUnit makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public void setUnitCd(String unitCd){
    this.unitCd = unitCd;
}


public QmsUnit flagStatus(String flagStatus){
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
    QmsUnit qmsUnit = (QmsUnit) o;
    if (qmsUnit.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsUnit.getId());
}


@Override
public String toString(){
    return "QmsUnit{" + "id=" + getId() + ", unitCd='" + getUnitCd() + "'" + ", unitName='" + getUnitName() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsUnit compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}