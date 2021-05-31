import javax.persistence;
@Entity
@Table(name = "m_asset_category")
public class AssetCategory {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int assetCatId;

 private  String catName;

 private  String catRemark;

 private  int returnNotifctnDays;

 private  int amcNotifctnDays;

 private  int serviceNotifctnDays;

 private  int serviceCycleDays;

 private  int delStatus;

 private  int makerUserId;

 private  String updateDatetime;

 private  int exInt1;

 private  String exVar1;


public void setAssetCatId(int assetCatId){
    this.assetCatId = assetCatId;
}


public void setReturnNotifctnDays(int returnNotifctnDays){
    this.returnNotifctnDays = returnNotifctnDays;
}


public void setAmcNotifctnDays(int amcNotifctnDays){
    this.amcNotifctnDays = amcNotifctnDays;
}


public String getUpdateDatetime(){
    return updateDatetime;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setServiceNotifctnDays(int serviceNotifctnDays){
    this.serviceNotifctnDays = serviceNotifctnDays;
}


public void setServiceCycleDays(int serviceCycleDays){
    this.serviceCycleDays = serviceCycleDays;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public int getServiceNotifctnDays(){
    return serviceNotifctnDays;
}


public int getServiceCycleDays(){
    return serviceCycleDays;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getReturnNotifctnDays(){
    return returnNotifctnDays;
}


public int getAmcNotifctnDays(){
    return amcNotifctnDays;
}


public int getAssetCatId(){
    return assetCatId;
}


public String getCatName(){
    return catName;
}


public void setCatName(String catName){
    this.catName = catName;
}


public void setUpdateDatetime(String updateDatetime){
    this.updateDatetime = updateDatetime;
}


public int getDelStatus(){
    return delStatus;
}


public String getCatRemark(){
    return catRemark;
}


public void setCatRemark(String catRemark){
    this.catRemark = catRemark;
}


@Override
public String toString(){
    return "AssetCategory [assetCatId=" + assetCatId + ", catName=" + catName + ", catRemark=" + catRemark + ", returnNotifctnDays=" + returnNotifctnDays + ", amcNotifctnDays=" + amcNotifctnDays + ", serviceNotifctnDays=" + serviceNotifctnDays + ", serviceCycleDays=" + serviceCycleDays + ", delStatus=" + delStatus + ", makerUserId=" + makerUserId + ", updateDatetime=" + updateDatetime + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}