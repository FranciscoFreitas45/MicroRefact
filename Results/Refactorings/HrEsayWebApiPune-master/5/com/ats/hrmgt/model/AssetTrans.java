import javax.persistence;
@Entity
@Table(name = "t_asset_trans")
public class AssetTrans {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int assetTransId;

 private  int empId;

 private  int assetId;

 private  String useFromDate;

 private  String useToDate;

 private  String returnDate;

 private  String assignRemark;

 private  int assetTransStatus;

 private  String assignImgFile;

 private  String returnImgFile;

 private  int isLost;

 private  String lostRemark;

 private  String returnRemark;

 private  int makerUserId;

 private  String updateDatetime;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public String getAssignRemark(){
    return assignRemark;
}


public void setIsLost(int isLost){
    this.isLost = isLost;
}


public int getExInt2(){
    return exInt2;
}


public String getUpdateDatetime(){
    return updateDatetime;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setReturnImgFile(String returnImgFile){
    this.returnImgFile = returnImgFile;
}


public void setReturnRemark(String returnRemark){
    this.returnRemark = returnRemark;
}


public void setReturnDate(String returnDate){
    this.returnDate = returnDate;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public String getUseFromDate(){
    return useFromDate;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getAssignImgFile(){
    return assignImgFile;
}


public int getIsLost(){
    return isLost;
}


public String getReturnDate(){
    return returnDate;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setAssignRemark(String assignRemark){
    this.assignRemark = assignRemark;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setAssetTransStatus(int assetTransStatus){
    this.assetTransStatus = assetTransStatus;
}


public String getLostRemark(){
    return lostRemark;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getAssetTransId(){
    return assetTransId;
}


public void setAssignImgFile(String assignImgFile){
    this.assignImgFile = assignImgFile;
}


public int getEmpId(){
    return empId;
}


public int getAssetTransStatus(){
    return assetTransStatus;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setLostRemark(String lostRemark){
    this.lostRemark = lostRemark;
}


public void setUpdateDatetime(String updateDatetime){
    this.updateDatetime = updateDatetime;
}


public int getDelStatus(){
    return delStatus;
}


public void setUseToDate(String useToDate){
    this.useToDate = useToDate;
}


public String getReturnImgFile(){
    return returnImgFile;
}


@Override
public String toString(){
    return "AssetTrans [assetTransId=" + assetTransId + ", empId=" + empId + ", assetId=" + assetId + ", useFromDate=" + useFromDate + ", useToDate=" + useToDate + ", returnDate=" + returnDate + ", assignRemark=" + assignRemark + ", assetTransStatus=" + assetTransStatus + ", assignImgFile=" + assignImgFile + ", returnImgFile=" + returnImgFile + ", isLost=" + isLost + ", lostRemark=" + lostRemark + ", returnRemark=" + returnRemark + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setAssetTransId(int assetTransId){
    this.assetTransId = assetTransId;
}


public int getAssetId(){
    return assetId;
}


public String getUseToDate(){
    return useToDate;
}


public void setUseFromDate(String useFromDate){
    this.useFromDate = useFromDate;
}


public String getReturnRemark(){
    return returnRemark;
}


}