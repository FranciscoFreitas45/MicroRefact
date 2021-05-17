import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class CatWiseTotalAssetsReport {

@Id
 private  String id;

 private  int assetId;

 private  String assetCode;

 private  String assetName;

 private  int assetCatId;

 private  String catName;

 private  String compName;

 private  String contactNo1;

 private  String conatctPersonName;

 private  String contactPersonNo;

 private  String locName;

 private  String statusText;

 private  float amcAmt;

 private  Date amcFrDate;

 private  Date amcToDate;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public void setAssetCatId(int assetCatId){
    this.assetCatId = assetCatId;
}


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public void setConatctPersonName(String conatctPersonName){
    this.conatctPersonName = conatctPersonName;
}


public String getLocName(){
    return locName;
}


public int getExInt1(){
    return exInt1;
}


public String getContactPersonNo(){
    return contactPersonNo;
}


public String getExVar1(){
    return exVar1;
}


public void setLocName(String locName){
    this.locName = locName;
}


public String getId(){
    return id;
}


public String getAssetCode(){
    return assetCode;
}


public float getAmcAmt(){
    return amcAmt;
}


public String getContactNo1(){
    return contactNo1;
}


public void setAmcAmt(float amcAmt){
    this.amcAmt = amcAmt;
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


public void setId(String id){
    this.id = id;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setAmcFrDate(Date amcFrDate){
    this.amcFrDate = amcFrDate;
}


public String getCompName(){
    return compName;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcFrDate(){
    return amcFrDate;
}


public int getAssetCatId(){
    return assetCatId;
}


public String getCatName(){
    return catName;
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


public void setCatName(String catName){
    this.catName = catName;
}


public void setStatusText(String statusText){
    this.statusText = statusText;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcToDate(){
    return amcToDate;
}


public String getAssetName(){
    return assetName;
}


public void setAmcToDate(Date amcToDate){
    this.amcToDate = amcToDate;
}


public void setCompName(String compName){
    this.compName = compName;
}


@Override
public String toString(){
    return "CatWiseTotalAssetsReport [id=" + id + ", assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", assetCatId=" + assetCatId + ", catName=" + catName + ", compName=" + compName + ", contactNo1=" + contactNo1 + ", conatctPersonName=" + conatctPersonName + ", contactPersonNo=" + contactPersonNo + ", locName=" + locName + ", statusText=" + statusText + ", amcAmt=" + amcAmt + ", amcFrDate=" + amcFrDate + ", amcToDate=" + amcToDate + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public String getConatctPersonName(){
    return conatctPersonName;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public String getStatusText(){
    return statusText;
}


public int getAssetId(){
    return assetId;
}


}