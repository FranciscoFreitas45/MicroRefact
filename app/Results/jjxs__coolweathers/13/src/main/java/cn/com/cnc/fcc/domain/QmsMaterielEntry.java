package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_materiel_entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsMaterielEntry implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 50)
@Column(name = "materiel_entry_cd", length = 50)
 private  String materielEntryCd;

@Column(name = "materiel_id")
 private  Integer materielId;

@Size(max = 100)
@Column(name = "specification_type", length = 100)
 private  String specificationType;

@Size(max = 30)
@Column(name = "figure_number", length = 30)
 private  String figureNumber;

@Column(name = "packing_quantity")
 private  Integer packingQuantity;

@Column(name = "supplier_id")
 private  Integer supplierId;

@Column(name = "entry_quantity")
 private  Integer entryQuantity;

@Size(max = 1)
@Column(name = "entry_type", length = 1)
 private  String entryType;

@Size(max = 50)
@Column(name = "purchase_order_number", length = 50)
 private  String purchaseOrderNumber;

@Size(max = 50)
@Column(name = "batch_number", length = 50)
 private  String batchNumber;

@Size(max = 50)
@Column(name = "is_use", length = 50)
 private  String isUse;

@Size(max = 50)
@Column(name = "made_ymd", length = 50)
 private  String madeYmd;

@Size(max = 50)
@Column(name = "made_factory_cd", length = 50)
 private  String madeFactoryCd;

@Size(max = 50)
@Column(name = "tex_ture", length = 50)
 private  String texTure;

@Size(max = 50)
@Column(name = "casting_num", length = 50)
 private  String castingNum;

@Column(name = "entry_date")
 private  ZonedDateTime entryDate;

@Size(max = 1)
@Column(name = "flag_inspect", length = 1)
 private  String flagInspect;

@Column(name = "inspection_time")
 private  ZonedDateTime inspectionTime;

@Column(name = "inspection_completed_time")
 private  ZonedDateTime inspectionCompletedTime;

@Column(name = "inspection_user_id")
 private  Integer inspectionUserId;

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


public QmsMaterielEntry isUse(String isUse){
    this.isUse = isUse;
    return this;
}


public void setFlagInspect(String flagInspect){
    this.flagInspect = flagInspect;
}


public String getMaterielEntryCd(){
    return materielEntryCd;
}


public String getMakeUser(){
    return makeUser;
}


public QmsMaterielEntry figureNumber(String figureNumber){
    this.figureNumber = figureNumber;
    return this;
}


public QmsMaterielEntry materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public String getFlagInspect(){
    return flagInspect;
}


public QmsMaterielEntry flagInspect(String flagInspect){
    this.flagInspect = flagInspect;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsMaterielEntry modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setIsUse(String isUse){
    this.isUse = isUse;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsMaterielEntry modifyTime(ZonedDateTime modifyTime){
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


public void setBatchNumber(String batchNumber){
    this.batchNumber = batchNumber;
}


public QmsMaterielEntry batchNumber(String batchNumber){
    this.batchNumber = batchNumber;
    return this;
}


public void setSupplierId(Integer supplierId){
    this.supplierId = supplierId;
}


public QmsMaterielEntry entryType(String entryType){
    this.entryType = entryType;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setEntryType(String entryType){
    this.entryType = entryType;
}


public QmsMaterielEntry entryDate(ZonedDateTime entryDate){
    this.entryDate = entryDate;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public QmsMaterielEntry inspectionCompletedTime(ZonedDateTime inspectionCompletedTime){
    this.inspectionCompletedTime = inspectionCompletedTime;
    return this;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public Integer getEntryQuantity(){
    return entryQuantity;
}


public QmsMaterielEntry makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public QmsMaterielEntry makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsMaterielEntry flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public Integer getInspectionUserId(){
    return inspectionUserId;
}


public String getTexTure(){
    return texTure;
}


public void setInspectionUserId(Integer inspectionUserId){
    this.inspectionUserId = inspectionUserId;
}


public QmsMaterielEntry compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setMadeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
}


public void setPackingQuantity(Integer packingQuantity){
    this.packingQuantity = packingQuantity;
}


public void setPurchaseOrderNumber(String purchaseOrderNumber){
    this.purchaseOrderNumber = purchaseOrderNumber;
}


public QmsMaterielEntry supplierId(Integer supplierId){
    this.supplierId = supplierId;
    return this;
}


public String getCastingNum(){
    return castingNum;
}


public Integer getMaterielId(){
    return materielId;
}


public void setMadeYmd(String madeYmd){
    this.madeYmd = madeYmd;
}


public QmsMaterielEntry madeYmd(String madeYmd){
    this.madeYmd = madeYmd;
    return this;
}


public QmsMaterielEntry inspectionTime(ZonedDateTime inspectionTime){
    this.inspectionTime = inspectionTime;
    return this;
}


public QmsMaterielEntry madeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
    return this;
}


public Long getId(){
    return id;
}


public QmsMaterielEntry inspectionUserId(Integer inspectionUserId){
    this.inspectionUserId = inspectionUserId;
    return this;
}


public QmsMaterielEntry remark(String remark){
    this.remark = remark;
    return this;
}


public QmsMaterielEntry reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getEntryType(){
    return entryType;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsMaterielEntry packingQuantity(Integer packingQuantity){
    this.packingQuantity = packingQuantity;
    return this;
}


public QmsMaterielEntry reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getSpecificationType(){
    return specificationType;
}


public String getMadeYmd(){
    return madeYmd;
}


public QmsMaterielEntry texTure(String texTure){
    this.texTure = texTure;
    return this;
}


public void setTexTure(String texTure){
    this.texTure = texTure;
}


public String getReserveThird(){
    return reserveThird;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


public void setInspectionTime(ZonedDateTime inspectionTime){
    this.inspectionTime = inspectionTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public ZonedDateTime getEntryDate(){
    return entryDate;
}


public void setEntryDate(ZonedDateTime entryDate){
    this.entryDate = entryDate;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public void setCastingNum(String castingNum){
    this.castingNum = castingNum;
}


public String getIsUse(){
    return isUse;
}


public String getMadeFactoryCd(){
    return madeFactoryCd;
}


public void setInspectionCompletedTime(ZonedDateTime inspectionCompletedTime){
    this.inspectionCompletedTime = inspectionCompletedTime;
}


public ZonedDateTime getInspectionCompletedTime(){
    return inspectionCompletedTime;
}


public Integer getPackingQuantity(){
    return packingQuantity;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setSpecificationType(String specificationType){
    this.specificationType = specificationType;
}


public QmsMaterielEntry reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public QmsMaterielEntry specificationType(String specificationType){
    this.specificationType = specificationType;
    return this;
}


public ZonedDateTime getInspectionTime(){
    return inspectionTime;
}


public String getCompPkid(){
    return compPkid;
}


public QmsMaterielEntry entryQuantity(Integer entryQuantity){
    this.entryQuantity = entryQuantity;
    return this;
}


public QmsMaterielEntry materielEntryCd(String materielEntryCd){
    this.materielEntryCd = materielEntryCd;
    return this;
}


public String getModifyUser(){
    return modifyUser;
}


public String getPurchaseOrderNumber(){
    return purchaseOrderNumber;
}


public void setEntryQuantity(Integer entryQuantity){
    this.entryQuantity = entryQuantity;
}


public String getFigureNumber(){
    return figureNumber;
}


public QmsMaterielEntry purchaseOrderNumber(String purchaseOrderNumber){
    this.purchaseOrderNumber = purchaseOrderNumber;
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
    QmsMaterielEntry qmsMaterielEntry = (QmsMaterielEntry) o;
    if (qmsMaterielEntry.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsMaterielEntry.getId());
}


public Integer getSupplierId(){
    return supplierId;
}


public void setMaterielEntryCd(String materielEntryCd){
    this.materielEntryCd = materielEntryCd;
}


public QmsMaterielEntry castingNum(String castingNum){
    this.castingNum = castingNum;
    return this;
}


@Override
public String toString(){
    return "QmsMaterielEntry{" + "id=" + getId() + ", materielEntryCd='" + getMaterielEntryCd() + "'" + ", materielId=" + getMaterielId() + ", specificationType='" + getSpecificationType() + "'" + ", figureNumber='" + getFigureNumber() + "'" + ", packingQuantity=" + getPackingQuantity() + ", supplierId=" + getSupplierId() + ", entryQuantity=" + getEntryQuantity() + ", entryType='" + getEntryType() + "'" + ", purchaseOrderNumber='" + getPurchaseOrderNumber() + "'" + ", batchNumber='" + getBatchNumber() + "'" + ", isUse='" + getIsUse() + "'" + ", madeYmd='" + getMadeYmd() + "'" + ", madeFactoryCd='" + getMadeFactoryCd() + "'" + ", texTure='" + getTexTure() + "'" + ", castingNum='" + getCastingNum() + "'" + ", entryDate='" + getEntryDate() + "'" + ", flagInspect='" + getFlagInspect() + "'" + ", inspectionTime='" + getInspectionTime() + "'" + ", inspectionCompletedTime='" + getInspectionCompletedTime() + "'" + ", inspectionUserId=" + getInspectionUserId() + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public String getBatchNumber(){
    return batchNumber;
}


}