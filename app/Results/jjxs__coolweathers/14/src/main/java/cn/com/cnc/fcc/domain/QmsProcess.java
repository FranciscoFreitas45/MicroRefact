package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsProcess implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "process_cd", length = 10)
 private  String processCd;

@Size(max = 100)
@Column(name = "process_name", length = 100)
 private  String processName;

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


public String getProcessName(){
    return processName;
}


public Long getId(){
    return id;
}


public QmsProcess remark(String remark){
    this.remark = remark;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsProcess processCd(String processCd){
    this.processCd = processCd;
    return this;
}


public QmsProcess modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public QmsProcess modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public QmsProcess processName(String processName){
    this.processName = processName;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getProcessCd(){
    return processCd;
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


public void setProcessCd(String processCd){
    this.processCd = processCd;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsProcess makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProcess makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsProcess flagStatus(String flagStatus){
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
    QmsProcess qmsProcess = (QmsProcess) o;
    if (qmsProcess.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsProcess.getId());
}


@Override
public String toString(){
    return "QmsProcess{" + "id=" + getId() + ", processCd='" + getProcessCd() + "'" + ", processName='" + getProcessName() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsProcess compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}