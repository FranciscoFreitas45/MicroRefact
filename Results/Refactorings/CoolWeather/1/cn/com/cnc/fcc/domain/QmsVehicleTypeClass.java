import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_vehicle_type_class")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsVehicleTypeClass implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 20)
@Column(name = "vehicle_class", length = 20)
 private  String vehicleClass;

@Size(max = 100)
@Column(name = "vehicle_class_name", length = 100)
 private  String vehicleClassName;

@Size(max = 200)
@Column(name = "remark", length = 200)
 private  String remark;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

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


public Long getId(){
    return id;
}


public String getVehicleClass(){
    return vehicleClass;
}


public QmsVehicleTypeClass remark(String remark){
    this.remark = remark;
    return this;
}


public QmsVehicleTypeClass reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public String getVehicleClassName(){
    return vehicleClassName;
}


public QmsVehicleTypeClass reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsVehicleTypeClass modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsVehicleTypeClass modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public QmsVehicleTypeClass vehicleClass(String vehicleClass){
    this.vehicleClass = vehicleClass;
    return this;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public void setVehicleClass(String vehicleClass){
    this.vehicleClass = vehicleClass;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setVehicleClassName(String vehicleClassName){
    this.vehicleClassName = vehicleClassName;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public QmsVehicleTypeClass reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public String getModifyUser(){
    return modifyUser;
}


public QmsVehicleTypeClass vehicleClassName(String vehicleClassName){
    this.vehicleClassName = vehicleClassName;
    return this;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsVehicleTypeClass makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsVehicleTypeClass makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsVehicleTypeClass flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
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
    QmsVehicleTypeClass qmsVehicleTypeClass = (QmsVehicleTypeClass) o;
    if (qmsVehicleTypeClass.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsVehicleTypeClass.getId());
}


@Override
public String toString(){
    return "QmsVehicleTypeClass{" + "id=" + getId() + ", vehicleClass='" + getVehicleClass() + "'" + ", vehicleClassName='" + getVehicleClassName() + "'" + ", remark='" + getRemark() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsVehicleTypeClass compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}