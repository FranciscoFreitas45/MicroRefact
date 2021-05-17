import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LocationWiseTtlAssets {

@Id
 private  String id;

 private  String locName;

 private  String catName;

 private  int assetCount;

 private  int assetCat_id;

 private  int locId;


public String getLocName(){
    return locName;
}


public int getLocId(){
    return locId;
}


public void setLocName(String locName){
    this.locName = locName;
}


public String getCatName(){
    return catName;
}


public void setAssetCount(int assetCount){
    this.assetCount = assetCount;
}


public void setLocId(int locId){
    this.locId = locId;
}


public String getId(){
    return id;
}


public void setCatName(String catName){
    this.catName = catName;
}


public int getAssetCount(){
    return assetCount;
}


public void setAssetCat_id(int assetCat_id){
    this.assetCat_id = assetCat_id;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "LocationWiseTtlAssets [id=" + id + ", locName=" + locName + ", catName=" + catName + ", assetCount=" + assetCount + ", assetCat_id=" + assetCat_id + ", locId=" + locId + "]";
}


public int getAssetCat_id(){
    return assetCat_id;
}


}