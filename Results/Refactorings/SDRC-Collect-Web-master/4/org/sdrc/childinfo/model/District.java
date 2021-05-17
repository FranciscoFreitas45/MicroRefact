import java.sql.Timestamp;
import java.util.List;
public class District {

 private  int districtId;

 private  String districtCode;

 private  String districtName;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  List<Block> samikshyaBlocks;

 private  ValueObject samikshyaState;


public void setDistrictName(String districtName){
    this.districtName = districtName;
}


public int getDistrictId(){
    return districtId;
}


public void setSamikshyaState(ValueObject samikshyaState){
    this.samikshyaState = samikshyaState;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setDistrictCode(String districtCode){
    this.districtCode = districtCode;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public void setSamikshyaBlocks(List<Block> samikshyaBlocks){
    this.samikshyaBlocks = samikshyaBlocks;
}


public ValueObject getSamikshyaState(){
    return samikshyaState;
}


public void setDistrictId(int districtId){
    this.districtId = districtId;
}


public String getDistrictCode(){
    return districtCode;
}


public List<Block> getSamikshyaBlocks(){
    return samikshyaBlocks;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public String getDistrictName(){
    return districtName;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}