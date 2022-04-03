package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_materiel_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsMaterielDetails implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "entry_id")
 private  Integer entryId;

@Size(max = 30)
@Column(name = "goods_cd", length = 30)
 private  String goodsCd;

@Size(max = 1)
@Column(name = "flag_inspect", length = 1)
 private  String flagInspect;

@Size(max = 1)
@Column(name = "is_check_ok", length = 1)
 private  String isCheckOk;

@Column(name = "goods_quantity")
 private  Integer goodsQuantity;

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


public QmsMaterielDetails goodsCd(String goodsCd){
    this.goodsCd = goodsCd;
    return this;
}


public void setGoodsCd(String goodsCd){
    this.goodsCd = goodsCd;
}


public void setFlagInspect(String flagInspect){
    this.flagInspect = flagInspect;
}


public String getGoodsCd(){
    return goodsCd;
}


public void setEntryId(Integer entryId){
    this.entryId = entryId;
}


public String getMakeUser(){
    return makeUser;
}


public QmsMaterielDetails entryId(Integer entryId){
    this.entryId = entryId;
    return this;
}


public String getFlagInspect(){
    return flagInspect;
}


public QmsMaterielDetails flagInspect(String flagInspect){
    this.flagInspect = flagInspect;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsMaterielDetails modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsMaterielDetails modifyTime(ZonedDateTime modifyTime){
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


public Integer getEntryId(){
    return entryId;
}


public void setId(Long id){
    this.id = id;
}


public QmsMaterielDetails goodsQuantity(Integer goodsQuantity){
    this.goodsQuantity = goodsQuantity;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsMaterielDetails makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public Integer getGoodsQuantity(){
    return goodsQuantity;
}


public QmsMaterielDetails makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsMaterielDetails flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsMaterielDetails compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setIsCheckOk(String isCheckOk){
    this.isCheckOk = isCheckOk;
}


public Long getId(){
    return id;
}


public QmsMaterielDetails isCheckOk(String isCheckOk){
    this.isCheckOk = isCheckOk;
    return this;
}


public QmsMaterielDetails remark(String remark){
    this.remark = remark;
    return this;
}


public QmsMaterielDetails reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsMaterielDetails reserveSecond(String reserveSecond){
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


public String getIsCheckOk(){
    return isCheckOk;
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


public QmsMaterielDetails reserveThird(String reserveThird){
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


public void setGoodsQuantity(Integer goodsQuantity){
    this.goodsQuantity = goodsQuantity;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsMaterielDetails qmsMaterielDetails = (QmsMaterielDetails) o;
    if (qmsMaterielDetails.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsMaterielDetails.getId());
}


@Override
public String toString(){
    return "QmsMaterielDetails{" + "id=" + getId() + ", entryId=" + getEntryId() + ", goodsCd='" + getGoodsCd() + "'" + ", flagInspect='" + getFlagInspect() + "'" + ", isCheckOk='" + getIsCheckOk() + "'" + ", goodsQuantity=" + getGoodsQuantity() + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}