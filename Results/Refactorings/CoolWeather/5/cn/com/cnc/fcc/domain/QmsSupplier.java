import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_supplier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsSupplier implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "supplier_class_id")
 private  Integer supplierClassId;

@Size(max = 10)
@Column(name = "supplier_cd", length = 10)
 private  String supplierCd;

@Size(max = 100)
@Column(name = "supplier_name", length = 100)
 private  String supplierName;

@Size(max = 100)
@Column(name = "address", length = 100)
 private  String address;

@Size(max = 20)
@Column(name = "tel_num_1", length = 20)
 private  String telNum1;

@Size(max = 20)
@Column(name = "tel_num_2", length = 20)
 private  String telNum2;

@Size(max = 20)
@Column(name = "fax_num", length = 20)
 private  String faxNum;

@Size(max = 100)
@Column(name = "url_address", length = 100)
 private  String urlAddress;

@Size(max = 50)
@Column(name = "mail_address", length = 50)
 private  String mailAddress;

@Size(max = 40)
@Column(name = "link_man", length = 40)
 private  String linkMan;

@Size(max = 40)
@Column(name = "department", length = 40)
 private  String department;

@Size(max = 20)
@Column(name = "ass_record", length = 20)
 private  String assRecord;

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


public String getDepartment(){
    return department;
}


public QmsSupplier supplierCd(String supplierCd){
    this.supplierCd = supplierCd;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public QmsSupplier linkMan(String linkMan){
    this.linkMan = linkMan;
    return this;
}


public String getLinkMan(){
    return linkMan;
}


public QmsSupplier modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsSupplier modifyTime(ZonedDateTime modifyTime){
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


public void setId(Long id){
    this.id = id;
}


public void setAssRecord(String assRecord){
    this.assRecord = assRecord;
}


public QmsSupplier assRecord(String assRecord){
    this.assRecord = assRecord;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setDepartment(String department){
    this.department = department;
}


public QmsSupplier mailAddress(String mailAddress){
    this.mailAddress = mailAddress;
    return this;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public String getFaxNum(){
    return faxNum;
}


public void setMailAddress(String mailAddress){
    this.mailAddress = mailAddress;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setSupplierClassId(Integer supplierClassId){
    this.supplierClassId = supplierClassId;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public QmsSupplier makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsSupplier makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsSupplier supplierClassId(Integer supplierClassId){
    this.supplierClassId = supplierClassId;
    return this;
}


public QmsSupplier faxNum(String faxNum){
    this.faxNum = faxNum;
    return this;
}


public QmsSupplier flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public void setUrlAddress(String urlAddress){
    this.urlAddress = urlAddress;
}


public QmsSupplier compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setFaxNum(String faxNum){
    this.faxNum = faxNum;
}


public Integer getSupplierClassId(){
    return supplierClassId;
}


public String getSupplierCd(){
    return supplierCd;
}


public Long getId(){
    return id;
}


public QmsSupplier remark(String remark){
    this.remark = remark;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getAddress(){
    return address;
}


public QmsSupplier urlAddress(String urlAddress){
    this.urlAddress = urlAddress;
    return this;
}


public QmsSupplier department(String department){
    this.department = department;
    return this;
}


public QmsSupplier supplierName(String supplierName){
    this.supplierName = supplierName;
    return this;
}


public String getTelNum1(){
    return telNum1;
}


public String getTelNum2(){
    return telNum2;
}


public String getUrlAddress(){
    return urlAddress;
}


public QmsSupplier address(String address){
    this.address = address;
    return this;
}


public String getSupplierName(){
    return supplierName;
}


public void setAddress(String address){
    this.address = address;
}


public QmsSupplier telNum2(String telNum2){
    this.telNum2 = telNum2;
    return this;
}


public void setTelNum2(String telNum2){
    this.telNum2 = telNum2;
}


public QmsSupplier telNum1(String telNum1){
    this.telNum1 = telNum1;
    return this;
}


public void setTelNum1(String telNum1){
    this.telNum1 = telNum1;
}


public void setSupplierCd(String supplierCd){
    this.supplierCd = supplierCd;
}


public String getAssRecord(){
    return assRecord;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getMailAddress(){
    return mailAddress;
}


public String getModifyUser(){
    return modifyUser;
}


public void setLinkMan(String linkMan){
    this.linkMan = linkMan;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsSupplier qmsSupplier = (QmsSupplier) o;
    if (qmsSupplier.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsSupplier.getId());
}


@Override
public String toString(){
    return "QmsSupplier{" + "id=" + getId() + ", supplierClassId=" + getSupplierClassId() + ", supplierCd='" + getSupplierCd() + "'" + ", supplierName='" + getSupplierName() + "'" + ", address='" + getAddress() + "'" + ", telNum1='" + getTelNum1() + "'" + ", telNum2='" + getTelNum2() + "'" + ", faxNum='" + getFaxNum() + "'" + ", urlAddress='" + getUrlAddress() + "'" + ", mailAddress='" + getMailAddress() + "'" + ", linkMan='" + getLinkMan() + "'" + ", department='" + getDepartment() + "'" + ", assRecord='" + getAssRecord() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


}