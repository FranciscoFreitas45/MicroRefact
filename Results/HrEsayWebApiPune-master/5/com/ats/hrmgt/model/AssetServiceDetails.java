import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AssetServiceDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int tServicingId;

 private  int assetId;

 private  int serviceType;

 private  Date serviceDate;

 private  Date nextServiceDate;

 private  float billAmt;

 private  String serviceDesc;

 private  String serviceRemark;

 private  String serviceVendor;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public void setServiceDate(Date serviceDate){
    this.serviceDate = serviceDate;
}


public int getExInt2(){
    return exInt2;
}


public void setServiceVendor(String serviceVendor){
    this.serviceVendor = serviceVendor;
}


public int getExInt1(){
    return exInt1;
}


public void setServiceType(int serviceType){
    this.serviceType = serviceType;
}


public String getExVar1(){
    return exVar1;
}


public float getBillAmt(){
    return billAmt;
}


public int gettServicingId(){
    return tServicingId;
}


public void setBillAmt(float billAmt){
    this.billAmt = billAmt;
}


public void setNextServiceDate(Date nextServiceDate){
    this.nextServiceDate = nextServiceDate;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getServiceDate(){
    return serviceDate;
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


public void settServicingId(int tServicingId){
    this.tServicingId = tServicingId;
}


public void setServiceRemark(String serviceRemark){
    this.serviceRemark = serviceRemark;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getNextServiceDate(){
    return nextServiceDate;
}


public String getServiceVendor(){
    return serviceVendor;
}


public void setServiceDesc(String serviceDesc){
    this.serviceDesc = serviceDesc;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getServiceDesc(){
    return serviceDesc;
}


public String getServiceRemark(){
    return serviceRemark;
}


@Override
public String toString(){
    return "AssetServiceDetails [tServicingId=" + tServicingId + ", assetId=" + assetId + ", serviceType=" + serviceType + ", serviceDate=" + serviceDate + ", nextServiceDate=" + nextServiceDate + ", billAmt=" + billAmt + ", serviceDesc=" + serviceDesc + ", serviceRemark=" + serviceRemark + ", serviceVendor=" + serviceVendor + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public int getServiceType(){
    return serviceType;
}


public int getAssetId(){
    return assetId;
}


}