import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_equipment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsEquipment implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "equipment_cd", length = 10)
 private  String equipmentCd;

@Size(max = 100)
@Column(name = "equipment_name", length = 100)
 private  String equipmentName;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

@Size(max = 200)
@Column(name = "remark", length = 200)
 private  String remark;

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


public String getEquipmentName(){
    return equipmentName;
}


public String getEquipmentCd(){
    return equipmentCd;
}


public Long getId(){
    return id;
}


public QmsEquipment remark(String remark){
    this.remark = remark;
    return this;
}


public QmsEquipment equipmentCd(String equipmentCd){
    this.equipmentCd = equipmentCd;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public void setEquipmentCd(String equipmentCd){
    this.equipmentCd = equipmentCd;
}


public QmsEquipment modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsEquipment modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
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


public QmsEquipment equipmentName(String equipmentName){
    this.equipmentName = equipmentName;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
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


public void setEquipmentName(String equipmentName){
    this.equipmentName = equipmentName;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsEquipment makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsEquipment makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsEquipment flagStatus(String flagStatus){
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
    QmsEquipment qmsEquipment = (QmsEquipment) o;
    if (qmsEquipment.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsEquipment.getId());
}


@Override
public String toString(){
    return "QmsEquipment{" + "id=" + getId() + ", equipmentCd='" + getEquipmentCd() + "'" + ", equipmentName='" + getEquipmentName() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsEquipment compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}