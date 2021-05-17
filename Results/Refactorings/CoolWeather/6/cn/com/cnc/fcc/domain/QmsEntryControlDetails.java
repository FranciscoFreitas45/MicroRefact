import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_entry_control_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsEntryControlDetails implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "materiel_id")
 private  Integer materielId;

@Size(max = 200)
@Column(name = "inspection_item", length = 200)
 private  String inspectionItem;

@Size(max = 1000)
@Column(name = "technical_requirement", length = 1000)
 private  String technicalRequirement;

@Size(max = 100)
@Column(name = "inspection_instrument", length = 100)
 private  String inspectionInstrument;

@Column(name = "standard")
 private  Double standard;

@Column(name = "upper_deviation")
 private  Double upperDeviation;

@Column(name = "lower_deviation")
 private  Double lowerDeviation;

@Column(name = "item_number")
 private  Integer itemNumber;

@Size(max = 1)
@Column(name = "inspection_result_diff", length = 1)
 private  String inspectionResultDiff;

@Size(max = 1)
@Column(name = "is_valid", length = 1)
 private  String isValid;

@Column(name = "lose_time")
 private  ZonedDateTime loseTime;

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


public QmsEntryControlDetails standard(Double standard){
    this.standard = standard;
    return this;
}


public QmsEntryControlDetails isUse(String isUse){
    this.isUse = isUse;
    return this;
}


public QmsEntryControlDetails itemNumber(Integer itemNumber){
    this.itemNumber = itemNumber;
    return this;
}


public QmsEntryControlDetails inspectionItem(String inspectionItem){
    this.inspectionItem = inspectionItem;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public QmsEntryControlDetails materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsEntryControlDetails modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setIsUse(String isUse){
    this.isUse = isUse;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsEntryControlDetails modifyTime(ZonedDateTime modifyTime){
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


public void setItemNumber(Integer itemNumber){
    this.itemNumber = itemNumber;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getInspectionItem(){
    return inspectionItem;
}


public QmsEntryControlDetails inspectionInstrument(String inspectionInstrument){
    this.inspectionInstrument = inspectionInstrument;
    return this;
}


public Double getStandard(){
    return standard;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public String getInspectionResultDiff(){
    return inspectionResultDiff;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public Double getLowerDeviation(){
    return lowerDeviation;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsEntryControlDetails makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsEntryControlDetails technicalRequirement(String technicalRequirement){
    this.technicalRequirement = technicalRequirement;
    return this;
}


public QmsEntryControlDetails makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsEntryControlDetails flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public Double getUpperDeviation(){
    return upperDeviation;
}


public QmsEntryControlDetails compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public QmsEntryControlDetails lowerDeviation(Double lowerDeviation){
    this.lowerDeviation = lowerDeviation;
    return this;
}


public void setUpperDeviation(Double upperDeviation){
    this.upperDeviation = upperDeviation;
}


public QmsEntryControlDetails inspectionResultDiff(String inspectionResultDiff){
    this.inspectionResultDiff = inspectionResultDiff;
    return this;
}


public QmsEntryControlDetails loseTime(ZonedDateTime loseTime){
    this.loseTime = loseTime;
    return this;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsEntryControlDetails remark(String remark){
    this.remark = remark;
    return this;
}


public QmsEntryControlDetails reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public QmsEntryControlDetails upperDeviation(Double upperDeviation){
    this.upperDeviation = upperDeviation;
    return this;
}


public Integer getItemNumber(){
    return itemNumber;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public void setLowerDeviation(Double lowerDeviation){
    this.lowerDeviation = lowerDeviation;
}


public QmsEntryControlDetails reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public ZonedDateTime getLoseTime(){
    return loseTime;
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


public void setLoseTime(ZonedDateTime loseTime){
    this.loseTime = loseTime;
}


public String getIsUse(){
    return isUse;
}


public String getIsValid(){
    return isValid;
}


public void setIsValid(String isValid){
    this.isValid = isValid;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsEntryControlDetails isValid(String isValid){
    this.isValid = isValid;
    return this;
}


public QmsEntryControlDetails reserveThird(String reserveThird){
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


public String getModifyUser(){
    return modifyUser;
}


public void setStandard(Double standard){
    this.standard = standard;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsEntryControlDetails qmsEntryControlDetails = (QmsEntryControlDetails) o;
    if (qmsEntryControlDetails.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsEntryControlDetails.getId());
}


@Override
public String toString(){
    return "QmsEntryControlDetails{" + "id=" + getId() + ", materielId=" + getMaterielId() + ", inspectionItem='" + getInspectionItem() + "'" + ", technicalRequirement='" + getTechnicalRequirement() + "'" + ", inspectionInstrument='" + getInspectionInstrument() + "'" + ", standard=" + getStandard() + ", upperDeviation=" + getUpperDeviation() + ", lowerDeviation=" + getLowerDeviation() + ", itemNumber=" + getItemNumber() + ", inspectionResultDiff='" + getInspectionResultDiff() + "'" + ", isValid='" + getIsValid() + "'" + ", loseTime='" + getLoseTime() + "'" + ", isUse='" + getIsUse() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getTechnicalRequirement(){
    return technicalRequirement;
}


public String getFlagStatus(){
    return flagStatus;
}


}