package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_approve_result")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsApproveResult implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "approve_flow_id")
 private  Integer approveFlowId;

@Column(name = "unqualified_product_id")
 private  Integer unqualifiedProductId;

@Column(name = "step_num")
 private  Integer stepNum;

@Column(name = "principal_user_id")
 private  Integer principalUserId;

@Column(name = "approve_time")
 private  ZonedDateTime approveTime;

@Size(max = 1)
@Column(name = "approve_result", length = 1)
 private  String approveResult;

@Size(max = 1)
@Column(name = "approve_status", length = 1)
 private  String approveStatus;

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


public QmsApproveResult approveStatus(String approveStatus){
    this.approveStatus = approveStatus;
    return this;
}


public Integer getApproveFlowId(){
    return approveFlowId;
}


public void setApproveStatus(String approveStatus){
    this.approveStatus = approveStatus;
}


public Integer getStepNum(){
    return stepNum;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsApproveResult modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsApproveResult approveFlowId(Integer approveFlowId){
    this.approveFlowId = approveFlowId;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsApproveResult modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getApproveStatus(){
    return approveStatus;
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


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsApproveResult makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsApproveResult makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsApproveResult flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsApproveResult stepNum(Integer stepNum){
    this.stepNum = stepNum;
    return this;
}


public void setStepNum(Integer stepNum){
    this.stepNum = stepNum;
}


public ZonedDateTime getApproveTime(){
    return approveTime;
}


public QmsApproveResult unqualifiedProductId(Integer unqualifiedProductId){
    this.unqualifiedProductId = unqualifiedProductId;
    return this;
}


public QmsApproveResult compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setPrincipalUserId(Integer principalUserId){
    this.principalUserId = principalUserId;
}


public Long getId(){
    return id;
}


public QmsApproveResult remark(String remark){
    this.remark = remark;
    return this;
}


public QmsApproveResult reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsApproveResult principalUserId(Integer principalUserId){
    this.principalUserId = principalUserId;
    return this;
}


public QmsApproveResult reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public void setUnqualifiedProductId(Integer unqualifiedProductId){
    this.unqualifiedProductId = unqualifiedProductId;
}


public String getReserveThird(){
    return reserveThird;
}


public QmsApproveResult approveResult(String approveResult){
    this.approveResult = approveResult;
    return this;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public void setApproveTime(ZonedDateTime approveTime){
    this.approveTime = approveTime;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public QmsApproveResult approveTime(ZonedDateTime approveTime){
    this.approveTime = approveTime;
    return this;
}


public void setApproveResult(String approveResult){
    this.approveResult = approveResult;
}


public String getReserveSecond(){
    return reserveSecond;
}


public Integer getPrincipalUserId(){
    return principalUserId;
}


public void setApproveFlowId(Integer approveFlowId){
    this.approveFlowId = approveFlowId;
}


public QmsApproveResult reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getApproveResult(){
    return approveResult;
}


public String getModifyUser(){
    return modifyUser;
}


public Integer getUnqualifiedProductId(){
    return unqualifiedProductId;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsApproveResult qmsApproveResult = (QmsApproveResult) o;
    if (qmsApproveResult.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsApproveResult.getId());
}


@Override
public String toString(){
    return "QmsApproveResult{" + "id=" + getId() + ", approveFlowId=" + getApproveFlowId() + ", unqualifiedProductId=" + getUnqualifiedProductId() + ", stepNum=" + getStepNum() + ", principalUserId=" + getPrincipalUserId() + ", approveTime='" + getApproveTime() + "'" + ", approveResult='" + getApproveResult() + "'" + ", approveStatus='" + getApproveStatus() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}