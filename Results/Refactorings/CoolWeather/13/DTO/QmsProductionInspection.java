import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsProductionInspection implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer bomTechnologyId;

 private  Integer materielId;

 private  String serialNumber;

 private  String furnace;

 private  String workno;

 private  String saleNumber;

 private  String productorderNumber;

 private  Integer productId;

 private  Integer finishNumber;

 private  Integer quailfiedNumber;

 private  Integer deffectiveNumber;

 private  String inspectionDiff;

 private  String isOk;

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


public String getFurnace(){
    return furnace;
}


public String getMakeUser(){
    return makeUser;
}


public Integer getProductId(){
    return productId;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public Integer getQuailfiedNumber(){
    return quailfiedNumber;
}


public String getIsOk(){
    return isOk;
}


public String getSerialNumber(){
    return serialNumber;
}


public Integer getFinishNumber(){
    return finishNumber;
}


public String getInspectionDiff(){
    return inspectionDiff;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public String getReserveThird(){
    return reserveThird;
}


public Integer getDeffectiveNumber(){
    return deffectiveNumber;
}


public String getSaleNumber(){
    return saleNumber;
}


public String getProductorderNumber(){
    return productorderNumber;
}


public String getReserveFirst(){
    return reserveFirst;
}


public String getReserveSecond(){
    return reserveSecond;
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


public String getWorkno(){
    return workno;
}


public String getFlagStatus(){
    return flagStatus;
}


}