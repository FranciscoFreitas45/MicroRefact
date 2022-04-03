package com.gs.common.util;
 import org.apache.shiro.SecurityUtils;
import java.util.ArrayList;
import java.util.List;
public class RoleUtil {


public boolean checkRoles(String roleName){
    String[] rolesNames = roleName.split(",");
    List<String> roles = new ArrayList<String>();
    for (String r : rolesNames) {
        roles.add(r);
    }
    boolean[] isRoles = SecurityUtils.getSubject().hasRoles(roles);
    for (boolean b : isRoles) {
        if (b) {
            return true;
        }
    }
    return false;
}


}