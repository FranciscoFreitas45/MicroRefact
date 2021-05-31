import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ScrappedAssetsReport {

@Id
 private  int assetId;

 private  String assetCode;

 private  String assetName;

 private  Date assetPurDate;

 private  String catName;

 private  Date scrapDate;

 private  String scrapRemark;

 private  String scrapAuthoriyDetails;

 private  String scrapDatetime;

 private  String empCode;

 private  String firstName;

 private  String surname;

 private  String deptName;

 private  String empDesgn;

 private  String locName;


public void setAssetPurDate(Date assetPurDate){
    this.assetPurDate = assetPurDate;
}


public String getEmpDesgn(){
    return empDesgn;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAssetPurDate(){
    return assetPurDate;
}


public String getLocName(){
    return locName;
}


public void setLocName(String locName){
    this.locName = locName;
}


public String getAssetCode(){
    return assetCode;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setScrapDatetime(String scrapDatetime){
    this.scrapDatetime = scrapDatetime;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public String getScrapRemark(){
    return scrapRemark;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setScrapAuthoriyDetails(String scrapAuthoriyDetails){
    this.scrapAuthoriyDetails = scrapAuthoriyDetails;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public String getCatName(){
    return catName;
}


public void setCatName(String catName){
    this.catName = catName;
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


public String getAssetName(){
    return assetName;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setScrapDate(Date scrapDate){
    this.scrapDate = scrapDate;
}


public String getScrapAuthoriyDetails(){
    return scrapAuthoriyDetails;
}


public String getScrapDatetime(){
    return scrapDatetime;
}


@Override
public String toString(){
    return "ScrappedAssetsReport [assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", assetPurDate=" + assetPurDate + ", catName=" + catName + ", scrapDate=" + scrapDate + ", scrapRemark=" + scrapRemark + ", scrapAuthoriyDetails=" + scrapAuthoriyDetails + ", scrapDatetime=" + scrapDatetime + ", empCode=" + empCode + ", firstName=" + firstName + ", surname=" + surname + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", locName=" + locName + "]";
}


public void setScrapRemark(String scrapRemark){
    this.scrapRemark = scrapRemark;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public String getFirstName(){
    return firstName;
}


public int getAssetId(){
    return assetId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getScrapDate(){
    return scrapDate;
}


public String getSurname(){
    return surname;
}


}