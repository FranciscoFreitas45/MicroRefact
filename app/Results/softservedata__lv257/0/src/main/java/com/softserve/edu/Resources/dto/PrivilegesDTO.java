package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.dto.asd.PropertyPrivilege;
import com.softserve.edu.Resources.dto.asd.SystemPrivilege;
import com.softserve.edu.Resources.dto.asd.TypePrivilege;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.PrivilegeType;
import com.softserve.edu.Resources.service.PrivilegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.softserve.edu.Resources.DTO.Privilege;
public class PrivilegesDTO {

@Autowired
 private PrivilegeService privilegeService;

 static  Logger logger;

 private  String roleName;

 private  List<SystemPrivilege> systemPrivileges;

 private  List<TypePrivilege> typePrivileges;

public PrivilegesDTO() {
}
public void addTypePrivilege(Privilege privilege,boolean enabled){
    String typeName = privilege.getName().substring(0, privilege.getName().indexOf(':'));
    String permissionName = privilege.getName().substring(privilege.getName().indexOf(':') + 1);
    TypePrivilege resTypePrivilege = new TypePrivilege();
    resTypePrivilege.setProperties(new ArrayList<>());
    boolean matchingNames = false;
    // if there is no typePrivileges in array it uses newly created object
    if (typePrivileges.isEmpty()) {
        resTypePrivilege.setName(typeName);
        resTypePrivilege.setProperties(new ArrayList<>());
    } else // if there are typePrivileges in array it looks for name equality
    {
        for (TypePrivilege x : typePrivileges) {
            // if names match it assigns this object to resTypePrivilege
            if (x.getName().equals(typeName)) {
                resTypePrivilege = x;
                resTypePrivilege.setName(typeName);
                matchingNames = true;
                break;
            }
        }
        // if there is no match it initialize new object to resTypePrivilege
        if (!matchingNames) {
            resTypePrivilege.setName(typeName);
            resTypePrivilege.setProperties(new ArrayList<>());
        }
    }
    switch(permissionName) {
        case "create":
            resTypePrivilege.setCreate(enabled);
        case "read":
            resTypePrivilege.setRead(enabled);
        case "update":
            resTypePrivilege.setUpdate(enabled);
        case "delete":
            resTypePrivilege.setDelete(enabled);
    }
    if (!matchingNames) {
        typePrivileges.add(resTypePrivilege);
    }
}


public String getRoleName(){
    return roleName;
}


public void convertToPrivilegeDTO(Map<Privilege,Boolean> allPrivilegesMap,String roleName){
    this.roleName = roleName;
    for (Map.Entry<Privilege, Boolean> entry : allPrivilegesMap.entrySet()) {
        if (entry.getKey().getPrivilegeType().equals(PrivilegeType.SYSTEM)) {
            addSystemPrivilege(entry.getKey(), entry.getValue());
        } else if (entry.getKey().getPrivilegeType().equals(PrivilegeType.RESOURCE_TYPE)) {
            addTypePrivilege(entry.getKey(), entry.getValue());
        } else /*(entry.getKey().getPrivilegeType().equals(PrivilegeType.PROPERTY))*/
        {
            addPropertyPrivilege(entry.getKey(), entry.getValue());
        }
    }
}


public List<SystemPrivilege> getSystemPrivileges(){
    return systemPrivileges;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public void addPropertyPrivilege(Privilege privilege,boolean enabled){
    String[] s = privilege.getName().split(":");
    String typeName = s[0];
    String propertyName = s[1];
    String permissionName = s[2];
    PropertyPrivilege resPropertyPrivilege = new PropertyPrivilege();
    boolean matchingPropertyName = false;
    for (TypePrivilege x : typePrivileges) {
        if (x.getName().equals(typeName)) {
            if (x.getProperties().isEmpty()) {
                resPropertyPrivilege.setName(propertyName);
            } else {
                for (PropertyPrivilege y : x.getProperties()) {
                    if (y.getName().equals(propertyName)) {
                        resPropertyPrivilege = y;
                        matchingPropertyName = true;
                        break;
                    }
                }
                if (!matchingPropertyName) {
                    resPropertyPrivilege.setName(propertyName);
                }
            }
            if (permissionName.equals("read"))
                resPropertyPrivilege.setRead(enabled);
            if (permissionName.equals("update"))
                resPropertyPrivilege.setUpdate(enabled);
            if (!matchingPropertyName) {
                x.getProperties().add(resPropertyPrivilege);
            }
        }
    }
}


public void setTypePrivileges(List<TypePrivilege> typePrivileges){
    this.typePrivileges = typePrivileges;
}


public void addSystemPrivilege(Privilege privilege,boolean enabled){
    SystemPrivilege systemPrivilege = new SystemPrivilege();
    systemPrivilege.setName(privilege.getName());
    systemPrivilege.setDescription(privilege.getDescription());
    systemPrivilege.setEnabled(enabled);
    systemPrivileges.add(systemPrivilege);
}


public Collection<Privilege> convertToPrivileges(PrivilegesDTO privilegesDTO){
    Collection<Privilege> privileges = new ArrayList<>();
    for (SystemPrivilege sysItem : privilegesDTO.getSystemPrivileges()) {
        if (sysItem.isEnabled()) {
            Privilege systemPrivilege = new Privilege();
            systemPrivilege.setName(sysItem.getName());
            systemPrivilege.setDescription(sysItem.getDescription());
            systemPrivilege.setPrivilegeType(PrivilegeType.SYSTEM);
            privileges.add(systemPrivilege);
        }
    }
    for (TypePrivilege typeItem : privilegesDTO.getTypePrivileges()) {
        if (typeItem.isCreate()) {
            Privilege privilege = new Privilege();
            privilege.setPrivilegeType(PrivilegeType.RESOURCE_TYPE);
            privilege.setName(typeItem.getName() + ":create");
            privileges.add(privilege);
        }
        if (typeItem.isRead()) {
            Privilege privilege = new Privilege();
            privilege.setPrivilegeType(PrivilegeType.RESOURCE_TYPE);
            privilege.setName(typeItem.getName() + ":read");
            privileges.add(privilege);
        }
        if (typeItem.isUpdate()) {
            Privilege privilege = new Privilege();
            privilege.setPrivilegeType(PrivilegeType.RESOURCE_TYPE);
            privilege.setName(typeItem.getName() + ":create");
            privileges.add(privilege);
        }
        if (typeItem.isDelete()) {
            Privilege privilege = new Privilege();
            privilege.setPrivilegeType(PrivilegeType.RESOURCE_TYPE);
            privilege.setName(typeItem.getName() + ":create");
            privileges.add(privilege);
        }
        for (PropertyPrivilege propItem : typeItem.getProperties()) {
            if (propItem.isRead()) {
                Privilege privilege = new Privilege();
                privilege.setPrivilegeType(PrivilegeType.PROPERTY);
                privilege.setName(typeItem.getName() + ":read");
                privileges.add(privilege);
            }
            if (propItem.isUpdate()) {
                Privilege privilege = new Privilege();
                privilege.setPrivilegeType(PrivilegeType.PROPERTY);
                privilege.setName(typeItem.getName() + ":create");
                privileges.add(privilege);
            }
        }
    }
    return privileges;
}


public void setSystemPrivileges(List<SystemPrivilege> systemPrivileges){
    this.systemPrivileges = systemPrivileges;
}


public List<TypePrivilege> getTypePrivileges(){
    return typePrivileges;
}


}