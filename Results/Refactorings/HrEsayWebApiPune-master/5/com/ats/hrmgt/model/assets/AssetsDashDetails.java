import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class AssetsDashDetails {

@Id
 private  String id;

 private  int assetId;

 private  String assetCode;

 private  String assetName;

 private  String assetMake;

 private  String assetModel;

 private  String assetSrno;

 private  Date assetPurDate;

 private  String assetPurImage;

 private  String locName;

 private  String compName;

 private  String catName;

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


public int getExInt2(){
    return exInt2;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getAssetPurDate(){
    return assetPurDate;
}


public String getLocName(){
    return locName;
}


public int getExInt1(){
    return exInt1;
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


public void setLocName(String locName){
    this.locName = locName;
}


public String getId(){
    return id;
}


public String getAssetPurImage(){
    return assetPurImage;
}


public String getAssetCode(){
    return assetCode;
}


public void setAssetModel(String assetModel){
    this.assetModel = assetModel;
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


public void setAssetMake(String assetMake){
    this.assetMake = assetMake;
}


public String getCompName(){
    return compName;
}


public void setAssetCode(String assetCode){
    this.assetCode = assetCode;
}


public void setAssetPurImage(String assetPurImage){
    this.assetPurImage = assetPurImage;
}


public String getAssetSrno(){
    return assetSrno;
}


public String getCatName(){
    return catName;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setCatName(String catName){
    this.catName = catName;
}


public String getAssetName(){
    return assetName;
}


public void setCompName(String compName){
    this.compName = compName;
}


@Override
public String toString(){
    return "AssetsDashDetails [id=" + id + ", assetId=" + assetId + ", assetCode=" + assetCode + ", assetName=" + assetName + ", assetMake=" + assetMake + ", assetModel=" + assetModel + ", assetSrno=" + assetSrno + ", assetPurDate=" + assetPurDate + ", assetPurImage=" + assetPurImage + ", locName=" + locName + ", compName=" + compName + ", catName=" + catName + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setAssetName(String assetName){
    this.assetName = assetName;
}


public String getAssetModel(){
    return assetModel;
}


public int getAssetId(){
    return assetId;
}


}