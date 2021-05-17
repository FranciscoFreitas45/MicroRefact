import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "t_asset_log")
public class AssetLog {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int assetLogId;

 private  int assetId;

 private  int assetTransId;

 private  String assetLogDesc;

 private  Date assetLogDate;

 private  int makerUserId;

 private  String updateDateTime;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  int exVar1;

 private  int exVar2;


public int getExVar2(){
    return exVar2;
}


public int getAssetLogId(){
    return assetLogId;
}


public void setUpdateDateTime(String updateDateTime){
    this.updateDateTime = updateDateTime;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public int getExVar1(){
    return exVar1;
}


public String getAssetLogDesc(){
    return assetLogDesc;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setAssetId(int assetId){
    this.assetId = assetId;
}


public void setExVar1(int exVar1){
    this.exVar1 = exVar1;
}


public void setAssetLogDate(Date assetLogDate){
    this.assetLogDate = assetLogDate;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setExVar2(int exVar2){
    this.exVar2 = exVar2;
}


public int getAssetTransId(){
    return assetTransId;
}


public String getUpdateDateTime(){
    return updateDateTime;
}


public Date getAssetLogDate(){
    return assetLogDate;
}


public int getDelStatus(){
    return delStatus;
}


public void setAssetLogId(int assetLogId){
    this.assetLogId = assetLogId;
}


@Override
public String toString(){
    return "AssetLog [assetLogId=" + assetLogId + ", assetId=" + assetId + ", assetTransId=" + assetTransId + ", assetLogDesc=" + assetLogDesc + ", assetLogDate=" + assetLogDate + ", makerUserId=" + makerUserId + ", updateDateTime=" + updateDateTime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setAssetLogDesc(String assetLogDesc){
    this.assetLogDesc = assetLogDesc;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public int getAssetId(){
    return assetId;
}


public void setAssetTransId(int assetTransId){
    this.assetTransId = assetTransId;
}


}