import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AssignedAssetsList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int assetTransId;

 private  int assetId;

 private  String assetCode;

 private  String assetName;

 private  String catName;

 private  Date useFromDate;

 private  Date useToDate;

 private  String assignRemark;

 private  String assignImgFile;


public String getAssignRemark(){
    return assignRemark;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public String getCatName(){
    return catName;
}


public int getAssetTransId(){
    return assetTransId;
}


public void setCatName(String catName){
    this.catName = catName;
}


public String getAssetCode(){
    return assetCode;
}


public void setAssignImgFile(String assignImgFile){
    this.assignImgFile = assignImgFile;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public String getAssetName(){
    return assetName;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getUseFromDate(){
    return useFromDate;
}


public void setUseToDate(Date useToDate){
    this.useToDate = useToDate;
}


public String getAssignImgFile(){
    return assignImgFile;
}


@Override
public String toString(){
    return "AssignedAssetsList [assetTransId=" + assetTransId + ", assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", catName=" + catName + ", useFromDate=" + useFromDate + ", useToDate=" + useToDate + ", assignRemark=" + assignRemark + ", assignImgFile=" + assignImgFile + "]";
}


public void setAssignRemark(String assignRemark){
    this.assignRemark = assignRemark;
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public void setAssetTransId(int assetTransId){
    this.assetTransId = assetTransId;
}


public int getAssetId(){
    return assetId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getUseToDate(){
    return useToDate;
}


public void setUseFromDate(Date useFromDate){
    this.useFromDate = useFromDate;
}


}