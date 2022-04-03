package de.metas.ui.web.login.json;
 import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
@SuppressWarnings("serial")
public class JSONLoginRole implements Serializable{

@JsonProperty("key")
 private  String key;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("roleId")
 private  int roleId;

@JsonProperty("tenantId")
 private  int tenantId;

@JsonProperty("orgId")
 private  int orgId;


public int getOrgId(){
    return orgId;
}


@JsonCreator
public JSONLoginRole of(String caption,int roleId,int tenantId,int orgId){
    return new JSONLoginRole(caption, roleId, tenantId, orgId);
}


public int getRoleId(){
    return roleId;
}


public String getCaption(){
    return caption;
}


public int getTenantId(){
    return tenantId;
}


}