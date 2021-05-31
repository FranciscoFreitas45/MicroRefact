import java.sql.Timestamp;
import java.util.List;
public class Feature {

 private  int featureId;

 private  String description;

 private  String featureCode;

 private  String featureName;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  List<ValueObject> featurePermissionMappings;


public int getFeatureId(){
    return featureId;
}


public List<ValueObject> getFeaturePermissionMappings(){
    return featurePermissionMappings;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getFeatureName(){
    return featureName;
}


public void setFeaturePermissionMappings(List<ValueObject> featurePermissionMappings){
    this.featurePermissionMappings = featurePermissionMappings;
}


public void setFeatureCode(String featureCode){
    this.featureCode = featureCode;
}


public String getFeatureCode(){
    return featureCode;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setFeatureId(int featureId){
    this.featureId = featureId;
}


public void setFeatureName(String featureName){
    this.featureName = featureName;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}