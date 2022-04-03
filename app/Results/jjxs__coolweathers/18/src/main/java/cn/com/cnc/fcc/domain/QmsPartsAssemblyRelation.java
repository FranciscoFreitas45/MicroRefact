package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_parts_assembly_relation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsPartsAssemblyRelation implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "bom_technology_id")
 private  Integer bomTechnologyId;

@Column(name = "assembly_num")
 private  Integer assemblyNum;

@Column(name = "assembly_materiel_id")
 private  Integer assemblyMaterielId;

@Column(name = "assembly_count")
 private  Integer assemblyCount;

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


public QmsPartsAssemblyRelation remark(String remark){
    this.remark = remark;
    return this;
}


public QmsPartsAssemblyRelation reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public Integer getAssemblyCount(){
    return assemblyCount;
}


public QmsPartsAssemblyRelation reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsPartsAssemblyRelation modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsPartsAssemblyRelation modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public QmsPartsAssemblyRelation assemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
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


public Integer getAssemblyMaterielId(){
    return assemblyMaterielId;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public void setAssemblyNum(Integer assemblyNum){
    this.assemblyNum = assemblyNum;
}


public void setAssemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsPartsAssemblyRelation assemblyNum(Integer assemblyNum){
    this.assemblyNum = assemblyNum;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public QmsPartsAssemblyRelation reserveThird(String reserveThird){
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


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsPartsAssemblyRelation makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setAssemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
}


public QmsPartsAssemblyRelation makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
}


public Integer getAssemblyNum(){
    return assemblyNum;
}


public QmsPartsAssemblyRelation flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsPartsAssemblyRelation assemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
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
    QmsPartsAssemblyRelation qmsPartsAssemblyRelation = (QmsPartsAssemblyRelation) o;
    if (qmsPartsAssemblyRelation.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsPartsAssemblyRelation.getId());
}


@Override
public String toString(){
    return "QmsPartsAssemblyRelation{" + "id=" + getId() + ", bomTechnologyId=" + getBomTechnologyId() + ", assemblyNum=" + getAssemblyNum() + ", assemblyMaterielId=" + getAssemblyMaterielId() + ", assemblyCount=" + getAssemblyCount() + ", remark='" + getRemark() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsPartsAssemblyRelation compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public QmsPartsAssemblyRelation bomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
    return this;
}


}