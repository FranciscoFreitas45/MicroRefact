import javax.persistence;
@Entity
@Table(name = "m_asset_vendor")
public class AssetVendor {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int vendorId;

 private  String compName;

 private  String compAddress;

 private  String vendorCity;

 private  String contactNo1;

 private  String contactNo2;

 private  String vendorEmail;

 private  String website;

 private  String gstin;

 private  String conatctPersonName;

 private  String contactPersonNo;

 private  String contactPersonEmail;

 private  String remark;

 private  int delStatus;

 private  int exInt1;

 private  String exVar1;

 private  int exInt2;

 private  String exVar2;

 private  int makerUserId;

 private  String updateDatetime;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public void setConatctPersonName(String conatctPersonName){
    this.conatctPersonName = conatctPersonName;
}


public int getExInt1(){
    return exInt1;
}


public String getUpdateDatetime(){
    return updateDatetime;
}


public String getContactPersonNo(){
    return contactPersonNo;
}


public String getExVar1(){
    return exVar1;
}


public String getGstin(){
    return gstin;
}


public void setContactPersonEmail(String contactPersonEmail){
    this.contactPersonEmail = contactPersonEmail;
}


public String getVendorEmail(){
    return vendorEmail;
}


public String getContactNo1(){
    return contactNo1;
}


public String getContactNo2(){
    return contactNo2;
}


public String getVendorCity(){
    return vendorCity;
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


public String getRemark(){
    return remark;
}


public void setVendorId(int vendorId){
    this.vendorId = vendorId;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public int getVendorId(){
    return vendorId;
}


public void setCompAddress(String compAddress){
    this.compAddress = compAddress;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getCompName(){
    return compName;
}


public void setWebsite(String website){
    this.website = website;
}


public String getWebsite(){
    return website;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setGstin(String gstin){
    this.gstin = gstin;
}


public void setContactNo2(String contactNo2){
    this.contactNo2 = contactNo2;
}


public String getContactPersonEmail(){
    return contactPersonEmail;
}


public void setContactNo1(String contactNo1){
    this.contactNo1 = contactNo1;
}


public void setContactPersonNo(String contactPersonNo){
    this.contactPersonNo = contactPersonNo;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setVendorCity(String vendorCity){
    this.vendorCity = vendorCity;
}


public void setVendorEmail(String vendorEmail){
    this.vendorEmail = vendorEmail;
}


public void setUpdateDatetime(String updateDatetime){
    this.updateDatetime = updateDatetime;
}


public int getDelStatus(){
    return delStatus;
}


public void setCompName(String compName){
    this.compName = compName;
}


public String getCompAddress(){
    return compAddress;
}


@Override
public String toString(){
    return "AssetVendor [vendorId=" + vendorId + ", compName=" + compName + ", compAddress=" + compAddress + ", vendorCity=" + vendorCity + ", contactNo1=" + contactNo1 + ", contactNo2=" + contactNo2 + ", vendorEmail=" + vendorEmail + ", website=" + website + ", gstin=" + gstin + ", conatctPersonName=" + conatctPersonName + ", contactPersonNo=" + contactPersonNo + ", contactPersonEmail=" + contactPersonEmail + ", remark=" + remark + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + ", delStatus=" + delStatus + ", exInt2=" + exInt2 + ", exVar2=" + exVar2 + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime + "]";
}


public String getConatctPersonName(){
    return conatctPersonName;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}