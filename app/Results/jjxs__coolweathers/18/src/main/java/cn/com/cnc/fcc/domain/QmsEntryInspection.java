package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_entry_inspection")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsEntryInspection implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "materiel_id")
 private  Integer materielId;

@Column(name = "supplier_id")
 private  Integer supplierId;

@Column(name = "ok_number")
 private  Integer okNumber;

@Column(name = "ng_number")
 private  Integer ngNumber;

@Size(max = 10)
@Column(name = "serial_number", length = 10)
 private  String serialNumber;

@Size(max = 10)
@Column(name = "production_cd", length = 10)
 private  String productionCd;

@Column(name = "check_date")
 private  ZonedDateTime checkDate;

@Size(max = 10)
@Column(name = "file_number", length = 10)
 private  String fileNumber;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

@Column(name = "entry_id")
 private  Integer entryId;

@Size(max = 200)
@Column(name = "remark", length = 200)
 private  String remark;

@Size(max = 20)
@Column(name = "group_cd", length = 20)
 private  String groupCd;

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


public QmsEntryInspection fileNumber(String fileNumber){
    this.fileNumber = fileNumber;
    return this;
}


public String getProductionCd(){
    return productionCd;
}


public ZonedDateTime getCheckDate(){
    return checkDate;
}


public void setEntryId(Integer entryId){
    this.entryId = entryId;
}


public String getMakeUser(){
    return makeUser;
}


public QmsEntryInspection materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public QmsEntryInspection entryId(Integer entryId){
    this.entryId = entryId;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsEntryInspection modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsEntryInspection modifyTime(ZonedDateTime modifyTime){
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


public Integer getEntryId(){
    return entryId;
}


public void setId(Long id){
    this.id = id;
}


public void setSupplierId(Integer supplierId){
    this.supplierId = supplierId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getSerialNumber(){
    return serialNumber;
}


public Integer getNgNumber(){
    return ngNumber;
}


public QmsEntryInspection serialNumber(String serialNumber){
    this.serialNumber = serialNumber;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setFileNumber(String fileNumber){
    this.fileNumber = fileNumber;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsEntryInspection makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsEntryInspection makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsEntryInspection flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsEntryInspection compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public QmsEntryInspection groupCd(String groupCd){
    this.groupCd = groupCd;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public String getGroupCd(){
    return groupCd;
}


public QmsEntryInspection ngNumber(Integer ngNumber){
    this.ngNumber = ngNumber;
    return this;
}


public QmsEntryInspection supplierId(Integer supplierId){
    this.supplierId = supplierId;
    return this;
}


public void setProductionCd(String productionCd){
    this.productionCd = productionCd;
}


public Integer getMaterielId(){
    return materielId;
}


public void setGroupCd(String groupCd){
    this.groupCd = groupCd;
}


public Long getId(){
    return id;
}


public QmsEntryInspection remark(String remark){
    this.remark = remark;
    return this;
}


public QmsEntryInspection reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public Integer getOkNumber(){
    return okNumber;
}


public QmsEntryInspection reserveSecond(String reserveSecond){
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


public void setNgNumber(Integer ngNumber){
    this.ngNumber = ngNumber;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public QmsEntryInspection okNumber(Integer okNumber){
    this.okNumber = okNumber;
    return this;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
}


public QmsEntryInspection reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setOkNumber(Integer okNumber){
    this.okNumber = okNumber;
}


public QmsEntryInspection checkDate(ZonedDateTime checkDate){
    this.checkDate = checkDate;
    return this;
}


public String getModifyUser(){
    return modifyUser;
}


public void setCheckDate(ZonedDateTime checkDate){
    this.checkDate = checkDate;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsEntryInspection qmsEntryInspection = (QmsEntryInspection) o;
    if (qmsEntryInspection.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsEntryInspection.getId());
}


public Integer getSupplierId(){
    return supplierId;
}


public QmsEntryInspection productionCd(String productionCd){
    this.productionCd = productionCd;
    return this;
}


@Override
public String toString(){
    return "QmsEntryInspection{" + "id=" + getId() + ", materielId=" + getMaterielId() + ", supplierId=" + getSupplierId() + ", okNumber=" + getOkNumber() + ", ngNumber=" + getNgNumber() + ", serialNumber='" + getSerialNumber() + "'" + ", productionCd='" + getProductionCd() + "'" + ", checkDate='" + getCheckDate() + "'" + ", fileNumber='" + getFileNumber() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", entryId=" + getEntryId() + ", remark='" + getRemark() + "'" + ", groupCd='" + getGroupCd() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFileNumber(){
    return fileNumber;
}


public String getFlagStatus(){
    return flagStatus;
}


}