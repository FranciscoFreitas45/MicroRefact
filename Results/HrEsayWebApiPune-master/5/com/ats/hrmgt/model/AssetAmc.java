import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "t_asset_amc")
public class AssetAmc {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int amcId;

 private  int assetId;

 private  Date amcFrDate;

 private  Date amcToDate;

 private  float amcAmt;

 private  String positiveRemark;

 private  String negativeRemark;

 private  int vendorId;

 private  int amcStatus;

 private  String termAndCondi;

 private  String amcDocFile;

 private  int makerUserId;

 private  String updateDatetime;

 private  int delStatus;

 private  int exInt1;

 private  String exVar1;

 private  int exInt2;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public void setNegativeRemark(String negativeRemark){
    this.negativeRemark = negativeRemark;
}


public int getExInt2(){
    return exInt2;
}


public void setAmcId(int amcId){
    this.amcId = amcId;
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


public void setAmcDocFile(String amcDocFile){
    this.amcDocFile = amcDocFile;
}


public float getAmcAmt(){
    return amcAmt;
}


public void setAmcStatus(int amcStatus){
    this.amcStatus = amcStatus;
}


public void setTermAndCondi(String termAndCondi){
    this.termAndCondi = termAndCondi;
}


public void setAmcAmt(float amcAmt){
    this.amcAmt = amcAmt;
}


public String getNegativeRemark(){
    return negativeRemark;
}


public String getTermAndCondi(){
    return termAndCondi;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
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


public int getAmcId(){
    return amcId;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setAmcFrDate(Date amcFrDate){
    this.amcFrDate = amcFrDate;
}


public int getVendorId(){
    return vendorId;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getAmcDocFile(){
    return amcDocFile;
}


public int getAmcStatus(){
    return amcStatus;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcFrDate(){
    return amcFrDate;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcToDate(){
    return amcToDate;
}


public void setPositiveRemark(String positiveRemark){
    this.positiveRemark = positiveRemark;
}


public void setUpdateDatetime(String updateDatetime){
    this.updateDatetime = updateDatetime;
}


public int getDelStatus(){
    return delStatus;
}


public void setAmcToDate(Date amcToDate){
    this.amcToDate = amcToDate;
}


@Override
public String toString(){
    return "AssetAmc [amcId=" + amcId + ", assetId=" + assetId + ", amcFrDate=" + amcFrDate + ", amcToDate=" + amcToDate + ", amcAmt=" + amcAmt + ", positiveRemark=" + positiveRemark + ", negativeRemark=" + negativeRemark + ", vendorId=" + vendorId + ", amcStatus=" + amcStatus + ", termAndCondi=" + termAndCondi + ", amcDocFile=" + amcDocFile + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + ", exInt2=" + exInt2 + ", exVar2=" + exVar2 + "]";
}


public String getPositiveRemark(){
    return positiveRemark;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public int getAssetId(){
    return assetId;
}


}