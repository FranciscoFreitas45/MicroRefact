package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_production_task")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsProductionTask implements Serializable{

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

@Size(max = 10)
@Column(name = "sale_number", length = 10)
 private  String saleNumber;

@Size(max = 10)
@Column(name = "productorder_number", length = 10)
 private  String productorderNumber;

@Column(name = "finish_number")
 private  Integer finishNumber;

@Column(name = "quailfied_number")
 private  Integer quailfiedNumber;

@Column(name = "deffective_number")
 private  Integer deffectiveNumber;

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


public QmsProductionTask furnace(String furnace){
    this.furnace = furnace;
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


public QmsProductionTask saleNumber(String saleNumber){
    this.saleNumber = saleNumber;
    return this;
}


public QmsProductionTask materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setSaleNumber(String saleNumber){
    this.saleNumber = saleNumber;
}


public QmsProductionTask modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsProductionTask modifyTime(ZonedDateTime modifyTime){
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


public Integer getQuailfiedNumber(){
    return quailfiedNumber;
}


public QmsProductionTask finishNumber(Integer finishNumber){
    this.finishNumber = finishNumber;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getSerialNumber(){
    return serialNumber;
}


public QmsProductionTask serialNumber(String serialNumber){
    this.serialNumber = serialNumber;
    return this;
}


public Integer getFinishNumber(){
    return finishNumber;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public QmsProductionTask quailfiedNumber(Integer quailfiedNumber){
    this.quailfiedNumber = quailfiedNumber;
    return this;
}


public void setQuailfiedNumber(Integer quailfiedNumber){
    this.quailfiedNumber = quailfiedNumber;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public QmsProductionTask deffectiveNumber(Integer deffectiveNumber){
    this.deffectiveNumber = deffectiveNumber;
    return this;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsProductionTask makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProductionTask makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsProductionTask flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsProductionTask compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public QmsProductionTask productorderNumber(String productorderNumber){
    this.productorderNumber = productorderNumber;
    return this;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsProductionTask remark(String remark){
    this.remark = remark;
    return this;
}


public QmsProductionTask reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsProductionTask reserveSecond(String reserveSecond){
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


public String getReserveSecond(){
    return reserveSecond;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
}


public QmsProductionTask reserveThird(String reserveThird){
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
    QmsProductionTask qmsProductionTask = (QmsProductionTask) o;
    if (qmsProductionTask.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsProductionTask.getId());
}


@Override
public String toString(){
    return "QmsProductionTask{" + "id=" + getId() + ", bomTechnologyId=" + getBomTechnologyId() + ", materielId=" + getMaterielId() + ", serialNumber='" + getSerialNumber() + "'" + ", furnace='" + getFurnace() + "'" + ", saleNumber='" + getSaleNumber() + "'" + ", productorderNumber='" + getProductorderNumber() + "'" + ", finishNumber=" + getFinishNumber() + ", quailfiedNumber=" + getQuailfiedNumber() + ", deffectiveNumber=" + getDeffectiveNumber() + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsProductionTask bomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
    return this;
}


}