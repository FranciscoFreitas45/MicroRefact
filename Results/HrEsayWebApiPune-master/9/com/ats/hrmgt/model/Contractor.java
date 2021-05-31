import javax.persistence;
@Entity
@Table(name = "m_contractor")
public class Contractor {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int contractorId;

 private  String service;

 private  String orgName;

 private  String licenceNo;

 private  String vatNo;

 private  String panNo;

 private  String pfNo;

 private  String esicNo;

 private  String address;

 private  String officeNo;

 private  String email;

 private  String owner;

 private  String mobileNo;

 private  String duration;

 private  String remark;

 private  int companyId;

 private  int delStatus;

 private  int isActive;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public void setEsicNo(String esicNo){
    this.esicNo = esicNo;
}


public void setDuration(String duration){
    this.duration = duration;
}


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public void setContractorId(int contractorId){
    this.contractorId = contractorId;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public String getOwner(){
    return owner;
}


public int getContractorId(){
    return contractorId;
}


public String getPfNo(){
    return pfNo;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setVatNo(String vatNo){
    this.vatNo = vatNo;
}


public String getDuration(){
    return duration;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setPfNo(String pfNo){
    this.pfNo = pfNo;
}


public String getRemark(){
    return remark;
}


public String getAddress(){
    return address;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public String getVatNo(){
    return vatNo;
}


public String getOrgName(){
    return orgName;
}


public void setAddress(String address){
    this.address = address;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public void setOrgName(String orgName){
    this.orgName = orgName;
}


public String getEsicNo(){
    return esicNo;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setLicenceNo(String licenceNo){
    this.licenceNo = licenceNo;
}


public void setOfficeNo(String officeNo){
    this.officeNo = officeNo;
}


public String getService(){
    return service;
}


public String getOfficeNo(){
    return officeNo;
}


public int getIsActive(){
    return isActive;
}


public void setService(String service){
    this.service = service;
}


public void setEmail(String email){
    this.email = email;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public String getEmail(){
    return email;
}


public String getLicenceNo(){
    return licenceNo;
}


public String getMobileNo(){
    return mobileNo;
}


@Override
public String toString(){
    return "Contractor [contractorId=" + contractorId + ", service=" + service + ", orgName=" + orgName + ", licenceNo=" + licenceNo + ", vatNo=" + vatNo + ", panNo=" + panNo + ", pfNo=" + pfNo + ", esicNo=" + esicNo + ", address=" + address + ", officeNo=" + officeNo + ", email=" + email + ", owner=" + owner + ", mobileNo=" + mobileNo + ", duration=" + duration + ", remark=" + remark + ", companyId=" + companyId + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public String getPanNo(){
    return panNo;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


public void setMobileNo(String mobileNo){
    this.mobileNo = mobileNo;
}


public void setPanNo(String panNo){
    this.panNo = panNo;
}


}