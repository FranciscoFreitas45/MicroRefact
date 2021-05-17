import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_nrv_telation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsNrvTelation implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 20)
@Column(name = "vehicle_type", length = 20)
 private  String vehicleType;

@Size(max = 20)
@Column(name = "vehicle_cd", length = 20)
 private  String vehicleCd;

@Size(max = 10)
@Column(name = "materiel_cd", length = 10)
 private  String materielCd;

@Size(max = 10)
@Column(name = "parent_materiel_cd", length = 10)
 private  String parentMaterielCd;

@Size(max = 10)
@Column(name = "serial_number", length = 10)
 private  String serialNumber;

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


public String getMaterielCd(){
    return materielCd;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsNrvTelation vehicleCd(String vehicleCd){
    this.vehicleCd = vehicleCd;
    return this;
}


public QmsNrvTelation modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsNrvTelation modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public void setVehicleCd(String vehicleCd){
    this.vehicleCd = vehicleCd;
}


public void setVehicleType(String vehicleType){
    this.vehicleType = vehicleType;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public QmsNrvTelation vehicleType(String vehicleType){
    this.vehicleType = vehicleType;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getSerialNumber(){
    return serialNumber;
}


public QmsNrvTelation serialNumber(String serialNumber){
    this.serialNumber = serialNumber;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setParentMaterielCd(String parentMaterielCd){
    this.parentMaterielCd = parentMaterielCd;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsNrvTelation makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsNrvTelation makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsNrvTelation flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsNrvTelation compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public String getVehicleType(){
    return vehicleType;
}


public String getParentMaterielCd(){
    return parentMaterielCd;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Long getId(){
    return id;
}


public QmsNrvTelation remark(String remark){
    this.remark = remark;
    return this;
}


public QmsNrvTelation reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsNrvTelation reserveSecond(String reserveSecond){
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


public String getVehicleCd(){
    return vehicleCd;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
}


public QmsNrvTelation reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public QmsNrvTelation parentMaterielCd(String parentMaterielCd){
    this.parentMaterielCd = parentMaterielCd;
    return this;
}


public String getModifyUser(){
    return modifyUser;
}


public QmsNrvTelation materielCd(String materielCd){
    this.materielCd = materielCd;
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
    QmsNrvTelation qmsNrvTelation = (QmsNrvTelation) o;
    if (qmsNrvTelation.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsNrvTelation.getId());
}


@Override
public String toString(){
    return "QmsNrvTelation{" + "id=" + getId() + ", vehicleType='" + getVehicleType() + "'" + ", vehicleCd='" + getVehicleCd() + "'" + ", materielCd='" + getMaterielCd() + "'" + ", parentMaterielCd='" + getParentMaterielCd() + "'" + ", serialNumber='" + getSerialNumber() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}