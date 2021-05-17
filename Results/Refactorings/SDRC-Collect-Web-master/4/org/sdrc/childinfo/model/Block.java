import java.sql.Timestamp;
import java.util.List;
public class Block {

 private  int blockId;

 private  String blockCode;

 private  String blockName;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  ValueObject samikshyaDistrict;

 private  List<Cluster> samikshyaClusters;


public String getBlockCode(){
    return blockCode;
}


public void setBlockCode(String blockCode){
    this.blockCode = blockCode;
}


public void setBlockName(String blockName){
    this.blockName = blockName;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public ValueObject getSamikshyaDistrict(){
    return samikshyaDistrict;
}


public String getBlockName(){
    return blockName;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public List<Cluster> getSamikshyaClusters(){
    return samikshyaClusters;
}


public void setBlockId(int blockId){
    this.blockId = blockId;
}


public int getBlockId(){
    return blockId;
}


public void setSamikshyaClusters(List<Cluster> samikshyaClusters){
    this.samikshyaClusters = samikshyaClusters;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setSamikshyaDistrict(ValueObject samikshyaDistrict){
    this.samikshyaDistrict = samikshyaDistrict;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}