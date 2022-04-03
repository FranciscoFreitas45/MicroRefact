package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_approve_flow")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsApproveFlow implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 20)
@Column(name = "approve_flow_cd", length = 20)
 private  String approveFlowCd;

@Column(name = "step_num")
 private  Integer stepNum;

@Column(name = "step_diff")
 private  Integer stepDiff;

@Column(name = "principal_user_id")
 private  Integer principalUserId;

@Size(max = 10)
@Column(name = "control_level", length = 10)
 private  String controlLevel;

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


public Integer getStepNum(){
    return stepNum;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsApproveFlow modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsApproveFlow modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public QmsApproveFlow controlLevel(String controlLevel){
    this.controlLevel = controlLevel;
    return this;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public Integer getStepDiff(){
    return stepDiff;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setApproveFlowCd(String approveFlowCd){
    this.approveFlowCd = approveFlowCd;
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


public QmsApproveFlow makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsApproveFlow makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsApproveFlow flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsApproveFlow stepNum(Integer stepNum){
    this.stepNum = stepNum;
    return this;
}


public void setStepNum(Integer stepNum){
    this.stepNum = stepNum;
}


public QmsApproveFlow compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public String getControlLevel(){
    return controlLevel;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public String getApproveFlowCd(){
    return approveFlowCd;
}


public void setStepDiff(Integer stepDiff){
    this.stepDiff = stepDiff;
}


public void setPrincipalUserId(Integer principalUserId){
    this.principalUserId = principalUserId;
}


public void setControlLevel(String controlLevel){
    this.controlLevel = controlLevel;
}


public Long getId(){
    return id;
}


public QmsApproveFlow remark(String remark){
    this.remark = remark;
    return this;
}


public QmsApproveFlow reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsApproveFlow principalUserId(Integer principalUserId){
    this.principalUserId = principalUserId;
    return this;
}


public QmsApproveFlow reserveSecond(String reserveSecond){
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


public QmsApproveFlow approveFlowCd(String approveFlowCd){
    this.approveFlowCd = approveFlowCd;
    return this;
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


public Integer getPrincipalUserId(){
    return principalUserId;
}


public QmsApproveFlow reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getModifyUser(){
    return modifyUser;
}


public QmsApproveFlow stepDiff(Integer stepDiff){
    this.stepDiff = stepDiff;
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
    QmsApproveFlow qmsApproveFlow = (QmsApproveFlow) o;
    if (qmsApproveFlow.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsApproveFlow.getId());
}


@Override
public String toString(){
    return "QmsApproveFlow{" + "id=" + getId() + ", approveFlowCd='" + getApproveFlowCd() + "'" + ", stepNum=" + getStepNum() + ", stepDiff=" + getStepDiff() + ", principalUserId=" + getPrincipalUserId() + ", controlLevel='" + getControlLevel() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}