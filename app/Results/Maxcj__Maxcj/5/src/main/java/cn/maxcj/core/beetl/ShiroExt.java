package cn.maxcj.core.beetl;
 import cn.maxcj.core.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
public class ShiroExt {

 private  String NAMES_DELIMETER;


public Subject getSubject(){
    return SecurityUtils.getSubject();
}


public boolean authenticated(){
    return getSubject() != null && getSubject().isAuthenticated();
}


public boolean notAuthenticated(){
    return !authenticated();
}


public boolean isGuest(){
    return !isUser();
}


public ShiroUser getUser(){
    if (isGuest()) {
        return null;
    } else {
        return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
    }
}


public boolean lacksRole(String roleName){
    return !hasRole(roleName);
}


public boolean hasAnyRoles(String roleNames){
    boolean hasAnyRole = false;
    Subject subject = getSubject();
    if (subject != null && roleNames != null && roleNames.length() > 0) {
        for (String role : roleNames.split(NAMES_DELIMETER)) {
            if (subject.hasRole(role.trim())) {
                hasAnyRole = true;
                break;
            }
        }
    }
    return hasAnyRole;
}


public String principal(){
    if (getSubject() != null) {
        Object principal = getSubject().getPrincipal();
        return principal.toString();
    }
    return "";
}


public boolean hasPermission(String permission){
    return getSubject() != null && permission != null && permission.length() > 0 && getSubject().isPermitted(permission);
}


public boolean hasRole(String roleName){
    return getSubject() != null && roleName != null && roleName.length() > 0 && getSubject().hasRole(roleName);
}


public boolean hasAllRoles(String roleNames){
    boolean hasAllRole = true;
    Subject subject = getSubject();
    if (subject != null && roleNames != null && roleNames.length() > 0) {
        for (String role : roleNames.split(NAMES_DELIMETER)) {
            if (!subject.hasRole(role.trim())) {
                hasAllRole = false;
                break;
            }
        }
    }
    return hasAllRole;
}


public boolean lacksPermission(String permission){
    return !hasPermission(permission);
}


public boolean isUser(){
    return getSubject() != null && getSubject().getPrincipal() != null;
}


}