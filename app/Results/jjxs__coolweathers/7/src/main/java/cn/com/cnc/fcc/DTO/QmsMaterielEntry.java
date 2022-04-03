package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsMaterielEntry implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String materielEntryCd;

 private  Integer materielId;

 private  String specificationType;

 private  String figureNumber;

 private  Integer packingQuantity;

 private  Integer supplierId;

 private  Integer entryQuantity;

 private  String entryType;

 private  String purchaseOrderNumber;

 private  String batchNumber;

 private  String isUse;

 private  String madeYmd;

 private  String madeFactoryCd;

 private  String texTure;

 private  String castingNum;

 private  ZonedDateTime entryDate;

 private  String flagInspect;

 private  ZonedDateTime inspectionTime;

 private  ZonedDateTime inspectionCompletedTime;

 private  Integer inspectionUserId;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public void setFlagInspect(String flagInspect){
    this.flagInspect = flagInspect;
}


public String getMaterielEntryCd(){
    return materielEntryCd;
}


public String getMakeUser(){
    return makeUser;
}


public QmsMaterielEntry materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public String getFlagInspect(){
    return flagInspect;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setIsUse(String isUse){
    this.isUse = isUse;
}


public QmsMaterielEntry modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public void setBatchNumber(String batchNumber){
    this.batchNumber = batchNumber;
}


public void setSupplierId(Integer supplierId){
    this.supplierId = supplierId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public QmsMaterielEntry entryDate(ZonedDateTime entryDate){
    this.entryDate = entryDate;
    return this;
}


public QmsMaterielEntry inspectionCompletedTime(ZonedDateTime inspectionCompletedTime){
    this.inspectionCompletedTime = inspectionCompletedTime;
    return this;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public Integer getEntryQuantity(){
    return entryQuantity;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
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


public QmsMaterielEntry compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setMadeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
}


public void setPurchaseOrderNumber(String purchaseOrderNumber){
    this.purchaseOrderNumber = purchaseOrderNumber;
}


public String getCastingNum(){
    return castingNum;
}


public Integer getMaterielId(){
    return materielId;
}


public QmsMaterielEntry madeYmd(String madeYmd){
    this.madeYmd = madeYmd;
    return this;
}


public QmsMaterielEntry madeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
    return this;
}


public Long getId(){
    return id;
}


public QmsMaterielEntry remark(String remark){
    this.remark = remark;
    return this;
}


public String getEntryType(){
    return entryType;
}


public QmsMaterielEntry packingQuantity(Integer packingQuantity){
    this.packingQuantity = packingQuantity;
    return this;
}


public String getSpecificationType(){
    return specificationType;
}


public String getMadeYmd(){
    return madeYmd;
}


public void setTexTure(String texTure){
    this.texTure = texTure;
}


public String getReserveThird(){
    return reserveThird;
}


public void setInspectionTime(ZonedDateTime inspectionTime){
    this.inspectionTime = inspectionTime;
}


public ZonedDateTime getEntryDate(){
    return entryDate;
}


public String getReserveFirst(){
    return reserveFirst;
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


public ZonedDateTime getInspectionCompletedTime(){
    return inspectionCompletedTime;
}


public Integer getPackingQuantity(){
    return packingQuantity;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsMaterielEntry reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public ZonedDateTime getInspectionTime(){
    return inspectionTime;
}


public String getCompPkid(){
    return compPkid;
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


public String getFigureNumber(){
    return figureNumber;
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


public QmsMaterielEntry castingNum(String castingNum){
    this.castingNum = castingNum;
    return this;
}


public String getFlagStatus(){
    return flagStatus;
}


public String getBatchNumber(){
    return batchNumber;
}


}