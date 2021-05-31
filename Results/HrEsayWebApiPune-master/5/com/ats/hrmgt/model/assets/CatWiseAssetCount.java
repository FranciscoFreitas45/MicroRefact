import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CatWiseAssetCount {

@Id
 private  int assetCatId;

 private  int assetCount;

 private  String catName;


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


public void setCatName(String catName){
    this.catName = catName;
}


@Override
public String toString(){
    return "CatWiseAssetCount [assetCatId=" + assetCatId + ", assetCount=" + assetCount + ", catName=" + catName + "]";
}


}