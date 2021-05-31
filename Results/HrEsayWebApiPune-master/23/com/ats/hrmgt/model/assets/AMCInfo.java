import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AMCInfo {

@Id
 private  int amcId;

 private  int assetId;

 private  Date amcFrDate;

 private  Date amcToDate;

 private  String amcAmt;

 private  String positiveRemark;

 private  String negativeRemark;

 private  int vendorId;

 private  String compName;

 private  int amcStatus;

 private  String statusText;

 private  int assetStatus;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public void setNegativeRemark(String negativeRemark){
    this.negativeRemark = negativeRemark;
}


public void setAmcId(int amcId){
    this.amcId = amcId;
}


public String getExVar1(){
    return exVar1;
}


public String getAmcAmt(){
    return amcAmt;
}


public void setAmcStatus(int amcStatus){
    this.amcStatus = amcStatus;
}


public void setAmcAmt(String amcAmt){
    this.amcAmt = amcAmt;
}


public String getNegativeRemark(){
    return negativeRemark;
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


public int getAssetStatus(){
    return assetStatus;
}


public int getAmcId(){
    return amcId;
}


public void setAmcFrDate(Date amcFrDate){
    this.amcFrDate = amcFrDate;
}


public int getVendorId(){
    return vendorId;
}


public String getCompName(){
    return compName;
}


public int getAmcStatus(){
    return amcStatus;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcFrDate(){
    return amcFrDate;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setStatusText(String statusText){
    this.statusText = statusText;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAmcToDate(){
    return amcToDate;
}


public void setPositiveRemark(String positiveRemark){
    this.positiveRemark = positiveRemark;
}


public void setAmcToDate(Date amcToDate){
    this.amcToDate = amcToDate;
}


public void setCompName(String compName){
    this.compName = compName;
}


@Override
public String toString(){
    return "AMCInfo [amcId=" + amcId + ", assetId=" + assetId + ", amcFrDate=" + amcFrDate + ", amcToDate=" + amcToDate + ", amcAmt=" + amcAmt + ", positiveRemark=" + positiveRemark + ", negativeRemark=" + negativeRemark + ", vendorId=" + vendorId + ", compName=" + compName + ", amcStatus=" + amcStatus + ", statusText=" + statusText + ", assetStatus=" + assetStatus + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setAssetStatus(int assetStatus){
    this.assetStatus = assetStatus;
}


public String getPositiveRemark(){
    return positiveRemark;
}


public String getStatusText(){
    return statusText;
}


public int getAssetId(){
    return assetId;
}


}