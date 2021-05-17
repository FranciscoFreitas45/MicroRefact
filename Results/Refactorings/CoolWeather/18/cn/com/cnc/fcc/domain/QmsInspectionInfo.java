import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_inspection_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsInspectionInfo implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 20)
@Column(name = "vehicle_type", length = 20)
 private  String vehicleType;

@Column(name = "no")
 private  Integer no;

@Size(max = 100)
@Column(name = "vehicle_type_name", length = 100)
 private  String vehicleTypeName;

@Size(max = 20)
@Column(name = "constitutive_coding", length = 20)
 private  String constitutiveCoding;

@Size(max = 100)
@Column(name = "constitutive_name", length = 100)
 private  String constitutiveName;

@Size(max = 20)
@Column(name = "constitutive_coding_name", length = 20)
 private  String constitutiveCodingName;

@Size(max = 20)
@Column(name = "vehicle_number", length = 20)
 private  String vehicleNumber;

@Size(max = 20)
@Column(name = "component_number", length = 20)
 private  String componentNumber;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

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


public QmsInspectionInfo no(Integer no){
    this.no = no;
    return this;
}


public String getConstitutiveName(){
    return constitutiveName;
}


public String getComponentNumber(){
    return componentNumber;
}


public void setNo(Integer no){
    this.no = no;
}


public void setVehicleTypeName(String vehicleTypeName){
    this.vehicleTypeName = vehicleTypeName;
}


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public String getConstitutiveCodingName(){
    return constitutiveCodingName;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public String getConstitutiveCoding(){
    return constitutiveCoding;
}


public void setVehicleNumber(String vehicleNumber){
    this.vehicleNumber = vehicleNumber;
}


public QmsInspectionInfo modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setConstitutiveName(String constitutiveName){
    this.constitutiveName = constitutiveName;
}


public QmsInspectionInfo modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public void setVehicleType(String vehicleType){
    this.vehicleType = vehicleType;
}


public void setId(Long id){
    this.id = id;
}


public QmsInspectionInfo vehicleNumber(String vehicleNumber){
    this.vehicleNumber = vehicleNumber;
    return this;
}


public QmsInspectionInfo vehicleType(String vehicleType){
    this.vehicleType = vehicleType;
    return this;
}


public QmsInspectionInfo vehicleTypeName(String vehicleTypeName){
    this.vehicleTypeName = vehicleTypeName;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public QmsInspectionInfo constitutiveName(String constitutiveName){
    this.constitutiveName = constitutiveName;
    return this;
}


public void setConstitutiveCoding(String constitutiveCoding){
    this.constitutiveCoding = constitutiveCoding;
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


public String getVehicleTypeName(){
    return vehicleTypeName;
}


public Integer getNo(){
    return no;
}


public QmsInspectionInfo constitutiveCodingName(String constitutiveCodingName){
    this.constitutiveCodingName = constitutiveCodingName;
    return this;
}


public void setComponentNumber(String componentNumber){
    this.componentNumber = componentNumber;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsInspectionInfo makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public String getVehicleNumber(){
    return vehicleNumber;
}


public QmsInspectionInfo makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsInspectionInfo componentNumber(String componentNumber){
    this.componentNumber = componentNumber;
    return this;
}


public QmsInspectionInfo flagStatus(String flagStatus){
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
    QmsInspectionInfo qmsInspectionInfo = (QmsInspectionInfo) o;
    if (qmsInspectionInfo.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsInspectionInfo.getId());
}


@Override
public String toString(){
    return "QmsInspectionInfo{" + "id=" + getId() + ", vehicleType='" + getVehicleType() + "'" + ", no=" + getNo() + ", vehicleTypeName='" + getVehicleTypeName() + "'" + ", constitutiveCoding='" + getConstitutiveCoding() + "'" + ", constitutiveName='" + getConstitutiveName() + "'" + ", constitutiveCodingName='" + getConstitutiveCodingName() + "'" + ", vehicleNumber='" + getVehicleNumber() + "'" + ", componentNumber='" + getComponentNumber() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public void setConstitutiveCodingName(String constitutiveCodingName){
    this.constitutiveCodingName = constitutiveCodingName;
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsInspectionInfo compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public String getVehicleType(){
    return vehicleType;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public QmsInspectionInfo constitutiveCoding(String constitutiveCoding){
    this.constitutiveCoding = constitutiveCoding;
    return this;
}


}