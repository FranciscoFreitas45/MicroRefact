import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_production_relation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsProductionRelation implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "production_inspection_id")
 private  Integer productionInspectionId;

@Column(name = "assembly_id")
 private  Integer assemblyId;

@Column(name = "do_product_id")
 private  Integer doProductId;

@Size(max = 200)
@Column(name = "actual_use_location", length = 200)
 private  String actualUseLocation;

@Column(name = "assembly_materiel_id")
 private  Integer assemblyMaterielId;

@Column(name = "use_product_id")
 private  Integer useProductId;

@Size(max = 1)
@Column(name = "from_diff", length = 1)
 private  String fromDiff;

@Size(max = 10)
@Column(name = "confirm_user_1", length = 10)
 private  String confirmUser1;

@Column(name = "confirm_time_1")
 private  ZonedDateTime confirmTime1;

@Size(max = 10)
@Column(name = "confirm_user_2", length = 10)
 private  String confirmUser2;

@Column(name = "confirm_time_2")
 private  ZonedDateTime confirmTime2;

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


public Integer getUseProductId(){
    return useProductId;
}


public QmsProductionRelation useProductId(Integer useProductId){
    this.useProductId = useProductId;
    return this;
}


public QmsProductionRelation assemblyId(Integer assemblyId){
    this.assemblyId = assemblyId;
    return this;
}


public QmsProductionRelation fromDiff(String fromDiff){
    this.fromDiff = fromDiff;
    return this;
}


public void setFromDiff(String fromDiff){
    this.fromDiff = fromDiff;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsProductionRelation modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsProductionRelation confirmUser1(String confirmUser1){
    this.confirmUser1 = confirmUser1;
    return this;
}


public QmsProductionRelation confirmUser2(String confirmUser2){
    this.confirmUser2 = confirmUser2;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsProductionRelation modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public QmsProductionRelation assemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public Integer getAssemblyMaterielId(){
    return assemblyMaterielId;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setAssemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setAssemblyId(Integer assemblyId){
    this.assemblyId = assemblyId;
}


public void setActualUseLocation(String actualUseLocation){
    this.actualUseLocation = actualUseLocation;
}


public Integer getDoProductId(){
    return doProductId;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsProductionRelation makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProductionRelation makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsProductionRelation flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public String getConfirmUser2(){
    return confirmUser2;
}


public void setConfirmTime2(ZonedDateTime confirmTime2){
    this.confirmTime2 = confirmTime2;
}


public String getConfirmUser1(){
    return confirmUser1;
}


public void setConfirmTime1(ZonedDateTime confirmTime1){
    this.confirmTime1 = confirmTime1;
}


public QmsProductionRelation compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Integer getProductionInspectionId(){
    return productionInspectionId;
}


public QmsProductionRelation confirmTime1(ZonedDateTime confirmTime1){
    this.confirmTime1 = confirmTime1;
    return this;
}


public void setUseProductId(Integer useProductId){
    this.useProductId = useProductId;
}


public QmsProductionRelation confirmTime2(ZonedDateTime confirmTime2){
    this.confirmTime2 = confirmTime2;
    return this;
}


public String getActualUseLocation(){
    return actualUseLocation;
}


public Long getId(){
    return id;
}


public QmsProductionRelation remark(String remark){
    this.remark = remark;
    return this;
}


public QmsProductionRelation reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public ZonedDateTime getConfirmTime1(){
    return confirmTime1;
}


public QmsProductionRelation reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public ZonedDateTime getConfirmTime2(){
    return confirmTime2;
}


public QmsProductionRelation actualUseLocation(String actualUseLocation){
    this.actualUseLocation = actualUseLocation;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public void setConfirmUser1(String confirmUser1){
    this.confirmUser1 = confirmUser1;
}


public void setConfirmUser2(String confirmUser2){
    this.confirmUser2 = confirmUser2;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsProductionRelation productionInspectionId(Integer productionInspectionId){
    this.productionInspectionId = productionInspectionId;
    return this;
}


public String getFromDiff(){
    return fromDiff;
}


public QmsProductionRelation reserveThird(String reserveThird){
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


public Integer getAssemblyId(){
    return assemblyId;
}


public QmsProductionRelation doProductId(Integer doProductId){
    this.doProductId = doProductId;
    return this;
}


public void setProductionInspectionId(Integer productionInspectionId){
    this.productionInspectionId = productionInspectionId;
}


public void setDoProductId(Integer doProductId){
    this.doProductId = doProductId;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsProductionRelation qmsProductionRelation = (QmsProductionRelation) o;
    if (qmsProductionRelation.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsProductionRelation.getId());
}


@Override
public String toString(){
    return "QmsProductionRelation{" + "id=" + getId() + ", productionInspectionId=" + getProductionInspectionId() + ", assemblyId=" + getAssemblyId() + ", doProductId=" + getDoProductId() + ", actualUseLocation='" + getActualUseLocation() + "'" + ", assemblyMaterielId=" + getAssemblyMaterielId() + ", useProductId=" + getUseProductId() + ", fromDiff='" + getFromDiff() + "'" + ", confirmUser1='" + getConfirmUser1() + "'" + ", confirmTime1='" + getConfirmTime1() + "'" + ", confirmUser2='" + getConfirmUser2() + "'" + ", confirmTime2='" + getConfirmTime2() + "'" + ", remark='" + getRemark() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}