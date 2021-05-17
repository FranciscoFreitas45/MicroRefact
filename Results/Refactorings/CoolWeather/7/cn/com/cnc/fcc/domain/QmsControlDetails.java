import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_control_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsControlDetails implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "inspection_cd", length = 10)
 private  String inspectionCd;

@Size(max = 400)
@Column(name = "inspection_item", length = 400)
 private  String inspectionItem;

@Size(max = 1000)
@Column(name = "technical_requirement", length = 1000)
 private  String technicalRequirement;

@Size(max = 100)
@Column(name = "inspection_instrument", length = 100)
 private  String inspectionInstrument;

@Size(max = 1)
@Column(name = "inspection_result_diff", length = 1)
 private  String inspectionResultDiff;

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


public void setInspectionInstrument(String inspectionInstrument){
    this.inspectionInstrument = inspectionInstrument;
}


public QmsControlDetails inspectionItem(String inspectionItem){
    this.inspectionItem = inspectionItem;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsControlDetails modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsControlDetails modifyTime(ZonedDateTime modifyTime){
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


public void setInspectionItem(String inspectionItem){
    this.inspectionItem = inspectionItem;
}


public void setTechnicalRequirement(String technicalRequirement){
    this.technicalRequirement = technicalRequirement;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getInspectionItem(){
    return inspectionItem;
}


public QmsControlDetails inspectionInstrument(String inspectionInstrument){
    this.inspectionInstrument = inspectionInstrument;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public QmsControlDetails inspectionCd(String inspectionCd){
    this.inspectionCd = inspectionCd;
    return this;
}


public String getInspectionResultDiff(){
    return inspectionResultDiff;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsControlDetails makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsControlDetails technicalRequirement(String technicalRequirement){
    this.technicalRequirement = technicalRequirement;
    return this;
}


public QmsControlDetails makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsControlDetails flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public String getInspectionCd(){
    return inspectionCd;
}


public QmsControlDetails compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public QmsControlDetails inspectionResultDiff(String inspectionResultDiff){
    this.inspectionResultDiff = inspectionResultDiff;
    return this;
}


public Long getId(){
    return id;
}


public QmsControlDetails remark(String remark){
    this.remark = remark;
    return this;
}


public QmsControlDetails reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsControlDetails reserveSecond(String reserveSecond){
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


public String getInspectionInstrument(){
    return inspectionInstrument;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsControlDetails reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setInspectionResultDiff(String inspectionResultDiff){
    this.inspectionResultDiff = inspectionResultDiff;
}


public void setInspectionCd(String inspectionCd){
    this.inspectionCd = inspectionCd;
}


public String getModifyUser(){
    return modifyUser;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsControlDetails qmsControlDetails = (QmsControlDetails) o;
    if (qmsControlDetails.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsControlDetails.getId());
}


@Override
public String toString(){
    return "QmsControlDetails{" + "id=" + getId() + ", inspectionCd='" + getInspectionCd() + "'" + ", inspectionItem='" + getInspectionItem() + "'" + ", technicalRequirement='" + getTechnicalRequirement() + "'" + ", inspectionInstrument='" + getInspectionInstrument() + "'" + ", inspectionResultDiff='" + getInspectionResultDiff() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getTechnicalRequirement(){
    return technicalRequirement;
}


public String getFlagStatus(){
    return flagStatus;
}


}