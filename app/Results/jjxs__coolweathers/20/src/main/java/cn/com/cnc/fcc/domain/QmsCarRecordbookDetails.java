package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_car_recordbook_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsCarRecordbookDetails implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "recordbook_main_id")
 private  Integer recordbookMainId;

@Column(name = "product_id")
 private  Integer productId;

@Size(max = 30)
@Column(name = "assembly_cd", length = 30)
 private  String assemblyCd;

@Size(max = 100)
@Column(name = "fileupload", length = 100)
 private  String fileupload;

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


public QmsCarRecordbookDetails assemblyCd(String assemblyCd){
    this.assemblyCd = assemblyCd;
    return this;
}


public Long getId(){
    return id;
}


public QmsCarRecordbookDetails remark(String remark){
    this.remark = remark;
    return this;
}


public QmsCarRecordbookDetails reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsCarRecordbookDetails reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public Integer getProductId(){
    return productId;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsCarRecordbookDetails fileupload(String fileupload){
    this.fileupload = fileupload;
    return this;
}


public QmsCarRecordbookDetails modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsCarRecordbookDetails modifyTime(ZonedDateTime modifyTime){
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


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public String getFileupload(){
    return fileupload;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setAssemblyCd(String assemblyCd){
    this.assemblyCd = assemblyCd;
}


public QmsCarRecordbookDetails productId(Integer productId){
    this.productId = productId;
    return this;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setRecordbookMainId(Integer recordbookMainId){
    this.recordbookMainId = recordbookMainId;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public QmsCarRecordbookDetails reserveThird(String reserveThird){
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


public QmsCarRecordbookDetails recordbookMainId(Integer recordbookMainId){
    this.recordbookMainId = recordbookMainId;
    return this;
}


public void setFileupload(String fileupload){
    this.fileupload = fileupload;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsCarRecordbookDetails makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsCarRecordbookDetails makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsCarRecordbookDetails flagStatus(String flagStatus){
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
    QmsCarRecordbookDetails qmsCarRecordbookDetails = (QmsCarRecordbookDetails) o;
    if (qmsCarRecordbookDetails.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsCarRecordbookDetails.getId());
}


public String getAssemblyCd(){
    return assemblyCd;
}


@Override
public String toString(){
    return "QmsCarRecordbookDetails{" + "id=" + getId() + ", recordbookMainId=" + getRecordbookMainId() + ", productId=" + getProductId() + ", assemblyCd='" + getAssemblyCd() + "'" + ", fileupload='" + getFileupload() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public Integer getRecordbookMainId(){
    return recordbookMainId;
}


public String getFlagStatus(){
    return flagStatus;
}


public QmsCarRecordbookDetails compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}