import java.sql.Timestamp;
import java.util.List;
public class FeaturePermissionMapping {

 private  int featurePermissionId;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  Feature feature;

 private  Permission permission;

 private  List<ValueObject> roleSchemes;


public int getFeaturePermissionId(){
    return featurePermissionId;
}


public void setFeaturePermissionId(int featurePermissionId){
    this.featurePermissionId = featurePermissionId;
}


public Permission getPermission(){
    return permission;
}


public void setFeature(Feature feature){
    this.feature = feature;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public void setRoleSchemes(List<ValueObject> roleSchemes){
    this.roleSchemes = roleSchemes;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setPermission(Permission permission){
    this.permission = permission;
}


public List<ValueObject> getRoleSchemes(){
    return roleSchemes;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


public Feature getFeature(){
    return feature;
}


}