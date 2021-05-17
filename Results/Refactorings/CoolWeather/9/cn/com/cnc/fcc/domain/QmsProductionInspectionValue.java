import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_production_inspection_value")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsProductionInspectionValue implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "inspection_id")
 private  Integer inspectionId;

@Size(max = 20)
@Column(name = "check_number", length = 20)
 private  String checkNumber;

@Size(max = 1)
@Column(name = "inspection_diff", length = 1)
 private  String inspectionDiff;

@Size(max = 1)
@Column(name = "is_ok", length = 1)
 private  String isOk;

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


public QmsProductionInspectionValue inspectionDiff(String inspectionDiff){
    this.inspectionDiff = inspectionDiff;
    return this;
}


public Long getId(){
    return id;
}


public QmsProductionInspectionValue remark(String remark){
    this.remark = remark;
    return this;
}


public QmsProductionInspectionValue reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsProductionInspectionValue reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public String getCheckNumber(){
    return checkNumber;
}


public QmsProductionInspectionValue modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsProductionInspectionValue modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public String getReserveThird(){
    return reserveThird;
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


public QmsProductionInspectionValue isOk(String isOk){
    this.isOk = isOk;
    return this;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public String getIsOk(){
    return isOk;
}


public void setCheckNumber(String checkNumber){
    this.checkNumber = checkNumber;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public QmsProductionInspectionValue checkNumber(String checkNumber){
    this.checkNumber = checkNumber;
    return this;
}


public void setIsOk(String isOk){
    this.isOk = isOk;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setInspectionId(Integer inspectionId){
    this.inspectionId = inspectionId;
}


public QmsProductionInspectionValue reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
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


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsProductionInspectionValue makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProductionInspectionValue inspectionId(Integer inspectionId){
    this.inspectionId = inspectionId;
    return this;
}


public QmsProductionInspectionValue makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public Integer getInspectionId(){
    return inspectionId;
}


public void setInspectionDiff(String inspectionDiff){
    this.inspectionDiff = inspectionDiff;
}


public QmsProductionInspectionValue flagStatus(String flagStatus){
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
    QmsProductionInspectionValue qmsProductionInspectionValue = (QmsProductionInspectionValue) o;
    if (qmsProductionInspectionValue.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsProductionInspectionValue.getId());
}


public String getInspectionDiff(){
    return inspectionDiff;
}


@Override
public String toString(){
    return "QmsProductionInspectionValue{" + "id=" + getId() + ", inspectionId=" + getInspectionId() + ", checkNumber='" + getCheckNumber() + "'" + ", inspectionDiff='" + getInspectionDiff() + "'" + ", isOk='" + getIsOk() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsProductionInspectionValue compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}