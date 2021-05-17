import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_materiel_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsMaterielType implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 10)
@Column(name = "materiel_type_cd", length = 10)
 private  String materielTypeCd;

@Size(max = 100)
@Column(name = "materiel_type_name", length = 100)
 private  String materielTypeName;

@Size(max = 10)
@Column(name = "parent_cd", length = 10)
 private  String parentCd;

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


public QmsMaterielType materielTypeCd(String materielTypeCd){
    this.materielTypeCd = materielTypeCd;
    return this;
}


public String getParentCd(){
    return parentCd;
}


public Long getId(){
    return id;
}


public QmsMaterielType remark(String remark){
    this.remark = remark;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCompPkid"));

.queryParam("compPkid",compPkid);
restTemplate.put(builder.toUriString(),null);


public void setMaterielTypeCd(String materielTypeCd){
    this.materielTypeCd = materielTypeCd;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterielTypeCd"));

.queryParam("materielTypeCd",materielTypeCd);
restTemplate.put(builder.toUriString(),null);


public String getMaterielTypeName(){
    return materielTypeName;
}


public QmsMaterielType materielTypeName(String materielTypeName){
    this.materielTypeName = materielTypeName;
    return this;
}


public QmsMaterielType modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsMaterielType modifyTime(ZonedDateTime modifyTime){
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


public QmsMaterielType parentCd(String parentCd){
    this.parentCd = parentCd;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeTime"));

.queryParam("makeTime",makeTime);
restTemplate.put(builder.toUriString(),null);


public String getMaterielTypeCd(){
    return materielTypeCd;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeUser"));

.queryParam("makeUser",makeUser);
restTemplate.put(builder.toUriString(),null);


public void setMaterielTypeName(String materielTypeName){
    this.materielTypeName = materielTypeName;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterielTypeName"));

.queryParam("materielTypeName",materielTypeName);
restTemplate.put(builder.toUriString(),null);


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setFlagStatus"));

.queryParam("flagStatus",flagStatus);
restTemplate.put(builder.toUriString(),null);


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyTime"));

.queryParam("modifyTime",modifyTime);
restTemplate.put(builder.toUriString(),null);


public QmsMaterielType makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setParentCd(String parentCd){
    this.parentCd = parentCd;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setParentCd"));

.queryParam("parentCd",parentCd);
restTemplate.put(builder.toUriString(),null);


public QmsMaterielType makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsMaterielType flagStatus(String flagStatus){
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
    QmsMaterielType qmsMaterielType = (QmsMaterielType) o;
    if (qmsMaterielType.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsMaterielType.getId());
}


@Override
public String toString(){
    return "QmsMaterielType{" + "id=" + getId() + ", materielTypeCd='" + getMaterielTypeCd() + "'" + ", materielTypeName='" + getMaterielTypeName() + "'" + ", parentCd='" + getParentCd() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/toString"));

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux


public String getFlagStatus(){
    return flagStatus;
}


public QmsMaterielType compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyUser"));

.queryParam("modifyUser",modifyUser);
restTemplate.put(builder.toUriString(),null);


}