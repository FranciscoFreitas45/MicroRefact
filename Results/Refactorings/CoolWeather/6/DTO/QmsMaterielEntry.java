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


public String getMaterielEntryCd(){
    return materielEntryCd;
}


public String getMakeUser(){
    return makeUser;
}


public String getFlagInspect(){
    return flagInspect;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public Integer getEntryQuantity(){
    return entryQuantity;
}


public Integer getInspectionUserId(){
    return inspectionUserId;
}


public String getTexTure(){
    return texTure;
}


public String getCastingNum(){
    return castingNum;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public String getEntryType(){
    return entryType;
}


public String getSpecificationType(){
    return specificationType;
}


public String getMadeYmd(){
    return madeYmd;
}


public String getReserveThird(){
    return reserveThird;
}


public ZonedDateTime getEntryDate(){
    return entryDate;
}


public String getReserveFirst(){
    return reserveFirst;
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


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public ZonedDateTime getInspectionTime(){
    return inspectionTime;
}


public String getCompPkid(){
    return compPkid;
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


public Integer getSupplierId(){
    return supplierId;
}


public String getFlagStatus(){
    return flagStatus;
}


public String getBatchNumber(){
    return batchNumber;
}


}