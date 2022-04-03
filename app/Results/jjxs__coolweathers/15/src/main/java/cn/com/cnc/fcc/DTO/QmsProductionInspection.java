package cn.com.cnc.fcc.DTO;
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


public QmsProductionInspection inspectionDiff(String inspectionDiff){
    this.inspectionDiff = inspectionDiff;
    return this;
}


public String getFurnace(){
    return furnace;
}


public String getMakeUser(){
    return makeUser;
}


public QmsProductionInspection saleNumber(String saleNumber){
    this.saleNumber = saleNumber;
    return this;
}


public Integer getProductId(){
    return productId;
}


public void setSaleNumber(String saleNumber){
    this.saleNumber = saleNumber;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
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


public Integer getQuailfiedNumber(){
    return quailfiedNumber;
}


public String getIsOk(){
    return isOk;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getSerialNumber(){
    return serialNumber;
}


public QmsProductionInspection productId(Integer productId){
    this.productId = productId;
    return this;
}


public Integer getFinishNumber(){
    return finishNumber;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public void setQuailfiedNumber(Integer quailfiedNumber){
    this.quailfiedNumber = quailfiedNumber;
}


public QmsProductionInspection deffectiveNumber(Integer deffectiveNumber){
    this.deffectiveNumber = deffectiveNumber;
    return this;
}


public QmsProductionInspection makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setInspectionDiff(String inspectionDiff){
    this.inspectionDiff = inspectionDiff;
}


public String getInspectionDiff(){
    return inspectionDiff;
}


public QmsProductionInspection compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public QmsProductionInspection productorderNumber(String productorderNumber){
    this.productorderNumber = productorderNumber;
    return this;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsProductionInspection reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public QmsProductionInspection reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
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


public void setDeffectiveNumber(Integer deffectiveNumber){
    this.deffectiveNumber = deffectiveNumber;
}


public void setIsOk(String isOk){
    this.isOk = isOk;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsProductionInspection reserveThird(String reserveThird){
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


public String getWorkno(){
    return workno;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsProductionInspection qmsProductionInspection = (QmsProductionInspection) o;
    if (qmsProductionInspection.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsProductionInspection.getId());
}


public String getFlagStatus(){
    return flagStatus;
}


}