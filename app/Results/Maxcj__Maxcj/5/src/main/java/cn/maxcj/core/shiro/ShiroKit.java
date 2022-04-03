package cn.maxcj.core.shiro;
 import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.constant.Const;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import java.util.List;
public class ShiroKit {

 private  String NAMES_DELIMETER;

 public  String hashAlgorithmName;

 public  int hashIterations;


public Subject getSubject(){
    return SecurityUtils.getSubject();
}


public boolean notAuthenticated(){
    return !isAuthenticated();
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


@SuppressWarnings("unchecked")
public T getSessionAttr(String key){
    Session session = getSession();
    return session != null ? (T) session.getAttribute(key) : null;
}


public boolean lacksRole(String roleName){
    return !hasRole(roleName);
}


public String getRandomSalt(int length){
    return ToolUtil.getRandomString(length);
}


public boolean isAuthenticated(){
    return getSubject() != null && getSubject().isAuthenticated();
}


public boolean isAdmin(){
    List<Integer> roleList = ShiroKit.getUser().getRoleList();
    for (Integer integer : roleList) {
        String singleRoleTip = ConstantFactory.me().getSingleRoleTip(integer);
        if (singleRoleTip.equals(Const.ADMIN_NAME)) {
            return true;
        }
    }
    return false;
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


public void removeSessionAttr(String key){
    Session session = getSession();
    if (session != null) {
        session.removeAttribute(key);
    }
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


public void setSessionAttr(String key,Object value){
    Session session = getSession();
    session.setAttribute(key, value);
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


public Session getSession(){
    return getSubject().getSession();
}


public List<Integer> getDeptDataScope(){
    Integer deptId = getUser().getDeptId();
    List<Integer> subDeptIds = ConstantFactory.me().getSubDeptId(deptId);
    subDeptIds.add(deptId);
    return subDeptIds;
}


public String md5(String credentials,String saltSource){
    ByteSource salt = new Md5Hash(saltSource);
    return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
}


}