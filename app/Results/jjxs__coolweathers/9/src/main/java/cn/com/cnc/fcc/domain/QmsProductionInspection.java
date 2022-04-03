package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_production_inspection")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsProductionInspection implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "bom_technology_id")
 private  Integer bomTechnologyId;

@Column(name = "materiel_id")
 private  Integer materielId;

@Size(max = 10)
@Column(name = "serial_number", length = 10)
 private  String serialNumber;

@Size(max = 20)
@Column(name = "furnace", length = 20)
 private  String furnace;

@Size(max = 20)
@Column(name = "workno", length = 20)
 private  String workno;

@Size(max = 10)
@Column(name = "sale_number", length = 10)
 private  String saleNumber;

@Size(max = 10)
@Column(name = "productorder_number", length = 10)
 private  String productorderNumber;

@Column(name = "product_id")
 private  Integer productId;

@Column(name = "finish_number")
 private  Integer finishNumber;

@Column(name = "quailfied_number")
 private  Integer quailfiedNumber;

@Column(name = "deffective_number")
 private  Integer deffectiveNumber;

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


public QmsProductionInspection furnace(String furnace){
    this.furnace = furnace;
    return this;
}


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


public void setFurnace(String furnace){
    this.furnace = furnace;
}


public QmsProductionInspection saleNumber(String saleNumber){
    this.saleNumber = saleNumber;
    return this;
}


public QmsProductionInspection materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public Integer getProductId(){
    return productId;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setSaleNumber(String saleNumber){
    this.saleNumber = saleNumber;
}


public QmsProductionInspection modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsProductionInspection modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public QmsProductionInspection isOk(String isOk){
    this.isOk = isOk;
    return this;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public Integer getQuailfiedNumber(){
    return quailfiedNumber;
}


public String getIsOk(){
    return isOk;
}


public QmsProductionInspection finishNumber(Integer finishNumber){
    this.finishNumber = finishNumber;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getSerialNumber(){
    return serialNumber;
}


public QmsProductionInspection serialNumber(String serialNumber){
    this.serialNumber = serialNumber;
    return this;
}


public QmsProductionInspection productId(Integer productId){
    this.productId = productId;
    return this;
}


public Integer getFinishNumber(){
    return finishNumber;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public QmsProductionInspection quailfiedNumber(Integer quailfiedNumber){
    this.quailfiedNumber = quailfiedNumber;
    return this;
}


public void setQuailfiedNumber(Integer quailfiedNumber){
    this.quailfiedNumber = quailfiedNumber;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public QmsProductionInspection deffectiveNumber(Integer deffectiveNumber){
    this.deffectiveNumber = deffectiveNumber;
    return this;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsProductionInspection makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProductionInspection makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public void setInspectionDiff(String inspectionDiff){
    this.inspectionDiff = inspectionDiff;
}


public QmsProductionInspection flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public String getInspectionDiff(){
    return inspectionDiff;
}


public void setWorkno(String workno){
    this.workno = workno;
}


public QmsProductionInspection compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
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


public QmsProductionInspection remark(String remark){
    this.remark = remark;
    return this;
}


public QmsProductionInspection reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsProductionInspection reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


public Integer getDeffectiveNumber(){
    return deffectiveNumber;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getSaleNumber(){
    return saleNumber;
}


public String getProductorderNumber(){
    return productorderNumber;
}


public void setProductorderNumber(String productorderNumber){
    this.productorderNumber = productorderNumber;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public void setDeffectiveNumber(Integer deffectiveNumber){
    this.deffectiveNumber = deffectiveNumber;
}


public void setFinishNumber(Integer finishNumber){
    this.finishNumber = finishNumber;
}


public void setIsOk(String isOk){
    this.isOk = isOk;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
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


public QmsProductionInspection workno(String workno){
    this.workno = workno;
    return this;
}


public String getWorkno(){
    return workno;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
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


@Override
public String toString(){
    return "QmsProductionInspection{" + "id=" + getId() + ", bomTechnologyId=" + getBomTechnologyId() + ", materielId=" + getMaterielId() + ", serialNumber='" + getSerialNumber() + "'" + ", furnace='" + getFurnace() + "'" + ", workno='" + getWorkno() + "'" + ", saleNumber='" + getSaleNumber() + "'" + ", productorderNumber='" + getProductorderNumber() + "'" + ", productId=" + getProductId() + ", finishNumber=" + getFinishNumber() + ", quailfiedNumber=" + getQuailfiedNumber() + ", deffectiveNumber=" + getDeffectiveNumber() + ", inspectionDiff='" + getInspectionDiff() + "'" + ", isOk='" + getIsOk() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsProductionInspection bomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
    return this;
}


}