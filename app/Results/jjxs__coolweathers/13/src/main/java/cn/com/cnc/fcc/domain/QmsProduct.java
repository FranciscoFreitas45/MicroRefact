package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsProduct implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 20)
@Column(name = "product_batch", length = 20)
 private  String productBatch;

@Size(max = 10)
@Column(name = "product_num", length = 10)
 private  String productNum;

@Column(name = "materiel_id")
 private  Integer materielId;

@Size(max = 1)
@Column(name = "product_diff", length = 1)
 private  String productDiff;

@Size(max = 1)
@Column(name = "is_use", length = 1)
 private  String isUse;

@Size(max = 1)
@Column(name = "is_ok", length = 1)
 private  String isOk;

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


public QmsProduct isUse(String isUse){
    this.isUse = isUse;
    return this;
}


public QmsProduct productDiff(String productDiff){
    this.productDiff = productDiff;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public QmsProduct materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsProduct modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setIsUse(String isUse){
    this.isUse = isUse;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsProduct modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public QmsProduct isOk(String isOk){
    this.isOk = isOk;
    return this;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setProductDiff(String productDiff){
    this.productDiff = productDiff;
}


public String getIsOk(){
    return isOk;
}


public void setProductBatch(String productBatch){
    this.productBatch = productBatch;
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


public String getProductNum(){
    return productNum;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsProduct makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProduct makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsProduct flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public String getProductDiff(){
    return productDiff;
}


public QmsProduct compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsProduct remark(String remark){
    this.remark = remark;
    return this;
}


public QmsProduct reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getProductBatch(){
    return productBatch;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsProduct reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
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


public QmsProduct productBatch(String productBatch){
    this.productBatch = productBatch;
    return this;
}


public String getIsUse(){
    return isUse;
}


public void setIsOk(String isOk){
    this.isOk = isOk;
}


public String getReserveSecond(){
    return reserveSecond;
}


public QmsProduct reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public QmsProduct productNum(String productNum){
    this.productNum = productNum;
    return this;
}


public String getModifyUser(){
    return modifyUser;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsProduct qmsProduct = (QmsProduct) o;
    if (qmsProduct.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsProduct.getId());
}


public void setProductNum(String productNum){
    this.productNum = productNum;
}


@Override
public String toString(){
    return "QmsProduct{" + "id=" + getId() + ", productBatch='" + getProductBatch() + "'" + ", productNum='" + getProductNum() + "'" + ", materielId=" + getMaterielId() + ", productDiff='" + getProductDiff() + "'" + ", isUse='" + getIsUse() + "'" + ", isOk='" + getIsOk() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}