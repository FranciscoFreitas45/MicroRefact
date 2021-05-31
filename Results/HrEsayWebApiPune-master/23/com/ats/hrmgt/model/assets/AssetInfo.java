import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AssetInfo {

@Id
 private  int assetId;

 private  String assetCode;

 private  String assetName;

 private  String assetDesc;

 private  int assetCatId;

 private  String assetMake;

 private  String assetModel;

 private  String assetSrno;

 private  String assetPurDate;

 private  int vendorId;

 private  String assetRemark;

 private  int locId;

 private  String exVar1;

 private  String exVar2;

 private  String catName;

 private  String locName;

 private  String compName;

 private  String contactNo1;

 private  String conatctPersonName;

 private  String contactPersonNo;

 private  String contactPersonEmail;


public void setAssetDesc(String assetDesc){
    this.assetDesc = assetDesc;
}


public void setAssetCatId(int assetCatId){
    this.assetCatId = assetCatId;
}


public void setAssetPurDate(String assetPurDate){
    this.assetPurDate = assetPurDate;
}


public String getExVar2(){
    return exVar2;
}


public void setContactPersonName(String conatctPersonName){
    this.conatctPersonName = conatctPersonName;
}


public String getAssetPurDate(){
    return assetPurDate;
}


public String getLocName(){
    return locName;
}


public int getLocId(){
    return locId;
}


public String getAssetMake(){
    return assetMake;
}


public String getExVar1(){
    return exVar1;
}


public String getContactPersonNo(){
    return contactPersonNo;
}


public void setAssetSrno(String assetSrno){
    this.assetSrno = assetSrno;
}


public String getAssetRemark(){
    return assetRemark;
}


public void setLocName(String locName){
    this.locName = locName;
}


public void setContactPersonEmail(String contactPersonEmail){
    this.contactPersonEmail = contactPersonEmail;
}


public String getAssetCode(){
    return assetCode;
}


public void setAssetModel(String assetModel){
    this.assetModel = assetModel;
}


public String getContactNo1(){
    return contactNo1;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setVendorId(int vendorId){
    this.vendorId = vendorId;
}


public int getVendorId(){
    return vendorId;
}


public void setAssetMake(String assetMake){
    this.assetMake = assetMake;
}


public String getAssetDesc(){
    return assetDesc;
}


public String getCompName(){
    return compName;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public int getAssetCatId(){
    return assetCatId;
}


public String getAssetSrno(){
    return assetSrno;
}


public void setLocId(int locId){
    this.locId = locId;
}


public String getCatName(){
    return catName;
}


public String getContactPersonEmail(){
    return contactPersonEmail;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setContactNo1(String contactNo1){
    this.contactNo1 = contactNo1;
}


public void setContactPersonNo(String contactPersonNo){
    this.contactPersonNo = contactPersonNo;
}


public void setCatName(String catName){
    this.catName = catName;
}


public String getContactPersonName(){
    return conatctPersonName;
}


public String getAssetName(){
    return assetName;
}


public void setAssetRemark(String assetRemark){
    this.assetRemark = assetRemark;
}


public void setCompName(String compName){
    this.compName = compName;
}


@Override
public String toString(){
    return "AssetInfo [assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", assetDesc=" + assetDesc + ", assetCatId=" + assetCatId + ", assetMake=" + assetMake + ", assetModel=" + assetModel + ", assetSrno=" + assetSrno + ", assetPurDate=" + assetPurDate + ", vendorId=" + vendorId + ", assetRemark=" + assetRemark + ", locId=" + locId + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", catName=" + catName + ", locName=" + locName + ", compName=" + compName + ", contactNo1=" + contactNo1 + ", contactPersonName=" + conatctPersonName + ", contactPersonNo=" + contactPersonNo + ", contactPersonEmail=" + contactPersonEmail + "]";
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public String getAssetModel(){
    return assetModel;
}


public int getAssetId(){
    return assetId;
}


}