package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_enclosure")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsEnclosure implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "inspection_info_id")
 private  Integer inspectionInfoId;

@Size(max = 1)
@Column(name = "inspection_kbn", length = 1)
 private  String inspectionKbn;

@Size(max = 100)
@Column(name = "enclosure_address", length = 100)
 private  String enclosureAddress;

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


public String getMakeUser(){
    return makeUser;
}


public void setEnclosureAddress(String enclosureAddress){
    this.enclosureAddress = enclosureAddress;
}


public QmsEnclosure modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsEnclosure modifyTime(ZonedDateTime modifyTime){
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


public void setId(Long id){
    this.id = id;
}


public void setInspectionInfoId(Integer inspectionInfoId){
    this.inspectionInfoId = inspectionInfoId;
}


public Integer getInspectionInfoId(){
    return inspectionInfoId;
}


public String getInspectionKbn(){
    return inspectionKbn;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setInspectionKbn(String inspectionKbn){
    this.inspectionKbn = inspectionKbn;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public QmsEnclosure inspectionInfoId(Integer inspectionInfoId){
    this.inspectionInfoId = inspectionInfoId;
    return this;
}


public String getModifyUser(){
    return modifyUser;
}


public QmsEnclosure inspectionKbn(String inspectionKbn){
    this.inspectionKbn = inspectionKbn;
    return this;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsEnclosure enclosureAddress(String enclosureAddress){
    this.enclosureAddress = enclosureAddress;
    return this;
}


public QmsEnclosure makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsEnclosure makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public String getEnclosureAddress(){
    return enclosureAddress;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsEnclosure qmsEnclosure = (QmsEnclosure) o;
    if (qmsEnclosure.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsEnclosure.getId());
}


@Override
public String toString(){
    return "QmsEnclosure{" + "id=" + getId() + ", inspectionInfoId=" + getInspectionInfoId() + ", inspectionKbn='" + getInspectionKbn() + "'" + ", enclosureAddress='" + getEnclosureAddress() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}