import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "t_asset_servicing")
public class AssetServicing {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int tServicingId;

 private  int assetId;

 private  int serviceType;

 private  Date serviceDate;

 private  Date nextServiceDate;

 private  int vendorId;

 private  String serviceDesc;

 private  float billAmt;

 private  String billDocFile;

 private  String serviceRemark;

 private  int serviceStatus;

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


public void setServiceDate(Date serviceDate){
    this.serviceDate = serviceDate;
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


public void setServiceStatus(int serviceStatus){
    this.serviceStatus = serviceStatus;
}


public String getBillDocFile(){
    return billDocFile;
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


public void setVendorId(int vendorId){
    this.vendorId = vendorId;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public int getVendorId(){
    return vendorId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getNextServiceDate(){
    return nextServiceDate;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setBillDocFile(String billDocFile){
    this.billDocFile = billDocFile;
}


public int getServiceStatus(){
    return serviceStatus;
}


public int getMakerUserId(){
    return makerUserId;
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


public void setUpdateDatetime(String updateDatetime){
    this.updateDatetime = updateDatetime;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "AssetServicing [tServicingId=" + tServicingId + ", assetId=" + assetId + ", serviceType=" + serviceType + ", serviceDate=" + serviceDate + ", nextServiceDate=" + nextServiceDate + ", vendorId=" + vendorId + ", serviceDesc=" + serviceDesc + ", billAmt=" + billAmt + ", billDocFile=" + billDocFile + ", serviceRemark=" + serviceRemark + ", serviceStatus=" + serviceStatus + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public int getServiceType(){
    return serviceType;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public int getAssetId(){
    return assetId;
}


}