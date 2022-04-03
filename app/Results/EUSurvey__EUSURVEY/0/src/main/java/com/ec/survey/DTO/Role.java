package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
public class Role {

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  Map<GlobalPrivilege,Integer> globalPrivileges;

public Role() {
    globalPrivileges = new HashMap<>();
    globalPrivileges.put(GlobalPrivilege.UserManagement, 0);
    globalPrivileges.put(GlobalPrivilege.FormManagement, 0);
    globalPrivileges.put(GlobalPrivilege.ContactManagement, 0);
    globalPrivileges.put(GlobalPrivilege.RightManagement, 0);
    globalPrivileges.put(GlobalPrivilege.ECAccess, 0);
    globalPrivileges.put(GlobalPrivilege.SystemManagement, 0);
}
@Column(name = "ROLE_NAME")
public String getName(){
    return name;
}


@Transient
public Map<GlobalPrivilege,Integer> getGlobalPrivileges(){
    return globalPrivileges;
}


@Id
@Column(name = "ROLE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "ROLE_PRIVILEGES")
public String getPrivileges(){
    StringBuilder result = new StringBuilder();
    TreeSet<GlobalPrivilege> privileges = new TreeSet<>(globalPrivileges.keySet());
    for (GlobalPrivilege privilege : privileges) {
        result.append(privilege.toString()).append(":").append(globalPrivileges.get(privilege)).append(";");
    }
    return result.toString();
}


@Transient
public int getPrivilegeValue(String key){
    GlobalPrivilege priv = GlobalPrivilege.valueOf(key);
    if (globalPrivileges.containsKey(priv)) {
        return globalPrivileges.get(priv);
    }
    return 0;
}


}