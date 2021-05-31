import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CatWiseAssetDistributn {

@Id
 private  int assetCatId;

 private  int assetCount;

 private  int assetStatus;

 private  String catName;

 private  String statusText;


public void setAssetCatId(int assetCatId){
    this.assetCatId = assetCatId;
}


public int getAssetCount(){
    return assetCount;
}


public int getAssetCatId(){
    return assetCatId;
}


public void setAssetCount(int assetCount){
    this.assetCount = assetCount;
}


public String getCatName(){
    return catName;
}


public int getAssetStatus(){
    return assetStatus;
}


public void setCatName(String catName){
    this.catName = catName;
}


@Override
public String toString(){
    return "CatWiseAssetDistributn [assetCatId=" + assetCatId + ", assetCount=" + assetCount + ", assetStatus=" + assetStatus + ", catName=" + catName + ", statusText=" + statusText + "]";
}


public void setAssetStatus(int assetStatus){
    this.assetStatus = assetStatus;
}


public void setStatusText(String statusText){
    this.statusText = statusText;
}


public String getStatusText(){
    return statusText;
}


}