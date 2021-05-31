import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AssetLogReport {

@Id
 private  int assetLogId;

 private  int assetId;

 private  int assetTransId;

 private  String assetLogDesc;

 private  Date assetLogDate;

 private  int makerUserId;

 private  String updateDateTime;

 private  String empCode;

 private  String firstName;

 private  String surname;

 private  String deptName;

 private  String empDesgn;

 private  String assetName;

 private  String assetCode;


public int getAssetLogId(){
    return assetLogId;
}


public void setUpdateDateTime(String updateDateTime){
    this.updateDateTime = updateDateTime;
}


public String getEmpDesgn(){
    return empDesgn;
}


public String getAssetLogDesc(){
    return assetLogDesc;
}


public String getAssetCode(){
    return assetCode;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setAssetLogDate(Date assetLogDate){
    this.assetLogDate = assetLogDate;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getAssetTransId(){
    return assetTransId;
}


public String getUpdateDateTime(){
    return updateDateTime;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAssetLogDate(){
    return assetLogDate;
}


public String getDeptName(){
    return deptName;
}


public void setEmpDesgn(String empDesgn){
    this.empDesgn = empDesgn;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getEmpCode(){
    return empCode;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getAssetName(){
    return assetName;
}


public void setAssetLogId(int assetLogId){
    this.assetLogId = assetLogId;
}


@Override
public String toString(){
    return "AssetLogReport [assetLogId=" + assetLogId + ", assetId=" + assetId + ", assetTransId=" + assetTransId + ", assetLogDesc=" + assetLogDesc + ", assetLogDate=" + assetLogDate + ", makerUserId=" + makerUserId + ", updateDateTime=" + updateDateTime + ", empCode=" + empCode + ", firstName=" + firstName + ", surname=" + surname + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", assetName=" + assetName + ", assetCode=" + assetCode + "]";
}


public void setAssetLogDesc(String assetLogDesc){
    this.assetLogDesc = assetLogDesc;
}


public String getFirstName(){
    return firstName;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public int getAssetId(){
    return assetId;
}


public void setAssetTransId(int assetTransId){
    this.assetTransId = assetTransId;
}


public String getSurname(){
    return surname;
}


}