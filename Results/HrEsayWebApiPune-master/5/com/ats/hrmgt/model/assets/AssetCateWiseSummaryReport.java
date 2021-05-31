import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AssetCateWiseSummaryReport {

@Id
 private  int assetCatId;

 private  String catName;

 private  int unAssigned;

 private  int assigned;

 private  int lost;

 private  int scrapped;


public void setAssetCatId(int assetCatId){
    this.assetCatId = assetCatId;
}


public void setUnAssigned(int unAssigned){
    this.unAssigned = unAssigned;
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


public int getAssigned(){
    return assigned;
}


public void setLost(int lost){
    this.lost = lost;
}


public int getScrapped(){
    return scrapped;
}


public void setAssigned(int assigned){
    this.assigned = assigned;
}


public void setScrapped(int scrapped){
    this.scrapped = scrapped;
}


public int getLost(){
    return lost;
}


public int getUnAssigned(){
    return unAssigned;
}


@Override
public String toString(){
    return "AssetCateWiseSummaryReport [assetCatId=" + assetCatId + ", catName=" + catName + ", unAssigned=" + unAssigned + ", assigned=" + assigned + ", lost=" + lost + ", scrapped=" + scrapped + "]";
}


}