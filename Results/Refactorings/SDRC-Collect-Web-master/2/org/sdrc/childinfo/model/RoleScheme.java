import java.sql.Timestamp;
import java.util.List;
public class RoleScheme {

 private  int roleSchemeId;

 private  String areaCode;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  String roleSchemeName;

 private  FeaturePermissionMapping featurePermissionMapping;

 private  Role samikshyaRole;

 private  List<ValueObject> userRoleSchemeMappings;


public void setFeaturePermissionMapping(FeaturePermissionMapping featurePermissionMapping){
    this.featurePermissionMapping = featurePermissionMapping;
}


public FeaturePermissionMapping getFeaturePermissionMapping(){
    return featurePermissionMapping;
}


public void setUserRoleSchemeMappings(List<ValueObject> userRoleSchemeMappings){
    this.userRoleSchemeMappings = userRoleSchemeMappings;
}


public void setAreaCode(String areaCode){
    this.areaCode = areaCode;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public List<ValueObject> getUserRoleSchemeMappings(){
    return userRoleSchemeMappings;
}


public void setRoleSchemeName(String roleSchemeName){
    this.roleSchemeName = roleSchemeName;
}


public void setSamikshyaRole(Role samikshyaRole){
    this.samikshyaRole = samikshyaRole;
}


public Role getSamikshyaRole(){
    return samikshyaRole;
}


public String getAreaCode(){
    return areaCode;
}


public int getRoleSchemeId(){
    return roleSchemeId;
}


public void setRoleSchemeId(int roleSchemeId){
    this.roleSchemeId = roleSchemeId;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public String getRoleSchemeName(){
    return roleSchemeName;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}