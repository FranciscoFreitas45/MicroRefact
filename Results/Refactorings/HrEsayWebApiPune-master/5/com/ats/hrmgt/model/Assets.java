import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.sql.Date;
@Entity
@Table(name = "m_assets")
public class Assets {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int assetId;

 private  String assetCode;

 private  String assetName;

 private  String assetDesc;

 private  int assetCatId;

 private  String assetMake;

 private  String assetModel;

 private  String assetSrno;

 private  Date assetPurDate;

 private  int vendorId;

 private  String assetRemark;

 private  int assetStatus;

 private  Date scrapDate;

 private  String scrapRemark;

 private  String scrapAuthoriyDetails;

 private  int scrapLoginUserid;

 private  String scrapDatetime;

 private  String assetPurImage;

 private  String assetScrapImage;

 private  int locId;

 private  int makerUserId;

 private  String updateDatetime;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public void setAssetPurDate(Date assetPurDate){
    this.assetPurDate = assetPurDate;
}


public String getExVar2(){
    return exVar2;
}


public String getUpdateDatetime(){
    return updateDatetime;
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


public void setAssetSrno(String assetSrno){
    this.assetSrno = assetSrno;
}


public String getAssetCode(){
    return assetCode;
}


public void setScrapDatetime(String scrapDatetime){
    this.scrapDatetime = scrapDatetime;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setScrapLoginUserid(int scrapLoginUserid){
    this.scrapLoginUserid = scrapLoginUserid;
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


public void setAssetMake(String assetMake){
    this.assetMake = assetMake;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getAssetDesc(){
    return assetDesc;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public void setAssetPurImage(String assetPurImage){
    this.assetPurImage = assetPurImage;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getScrapLoginUserid(){
    return scrapLoginUserid;
}


public void setUpdateDatetime(String updateDatetime){
    this.updateDatetime = updateDatetime;
}


public String getAssetName(){
    return assetName;
}


public void setAssetRemark(String assetRemark){
    this.assetRemark = assetRemark;
}


public void setScrapDate(Date scrapDate){
    this.scrapDate = scrapDate;
}


public String getScrapAuthoriyDetails(){
    return scrapAuthoriyDetails;
}


public void setAssetStatus(int assetStatus){
    this.assetStatus = assetStatus;
}


public String getAssetModel(){
    return assetModel;
}


public String getAssetScrapImage(){
    return assetScrapImage;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public int getAssetId(){
    return assetId;
}


public void setAssetDesc(String assetDesc){
    this.assetDesc = assetDesc;
}


public void setAssetCatId(int assetCatId){
    this.assetCatId = assetCatId;
}


public int getExInt2(){
    return exInt2;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAssetPurDate(){
    return assetPurDate;
}


public int getExInt1(){
    return exInt1;
}


public String getAssetRemark(){
    return assetRemark;
}


public String getAssetPurImage(){
    return assetPurImage;
}


public void setAssetModel(String assetModel){
    this.assetModel = assetModel;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public String getScrapRemark(){
    return scrapRemark;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getAssetStatus(){
    return assetStatus;
}


public void setScrapAuthoriyDetails(String scrapAuthoriyDetails){
    this.scrapAuthoriyDetails = scrapAuthoriyDetails;
}


public int getMakerUserId(){
    return makerUserId;
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


public int getDelStatus(){
    return delStatus;
}


public void setAssetScrapImage(String assetScrapImage){
    this.assetScrapImage = assetScrapImage;
}


public String getScrapDatetime(){
    return scrapDatetime;
}


@Override
public String toString(){
    return "Assets [assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", assetDesc=" + assetDesc + ", assetCatId=" + assetCatId + ", assetMake=" + assetMake + ", assetModel=" + assetModel + ", assetSrno=" + assetSrno + ", assetPurDate=" + assetPurDate + ", vendorId=" + vendorId + ", assetRemark=" + assetRemark + ", assetStatus=" + assetStatus + ", scrapDate=" + scrapDate + ", scrapRemark=" + scrapRemark + ", scrapAuthoriyDetails=" + scrapAuthoriyDetails + ", scrapLoginUserid=" + scrapLoginUserid + ", scrapDatetime=" + scrapDatetime + ", assetPurImage=" + assetPurImage + ", assetScrapImage=" + assetScrapImage + ", locId=" + locId + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setScrapRemark(String scrapRemark){
    this.scrapRemark = scrapRemark;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getScrapDate(){
    return scrapDate;
}


}