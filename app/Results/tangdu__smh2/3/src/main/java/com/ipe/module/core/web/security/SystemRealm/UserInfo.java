package com.ipe.module.core.web.security.SystemRealm;
 import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ipe.module.core.entity.Log;
import com.ipe.module.core.entity.Resource;
import com.ipe.module.core.entity.Role;
import com.ipe.module.core.entity.User;
import com.ipe.module.core.service.LogService;
import com.ipe.module.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
public class UserInfo implements Serializable{

 private  String userId;

 private  String userAccount;

 private  String userName;

 private  Date userLoginTime;

public UserInfo(User user) {
    this.userId = user.getId();
    this.userName = user.getUserName();
    this.userAccount = user.getUserAccount();
}
public Date getUserLoginTime(){
    return userLoginTime;
}


public String getUserAccount(){
    return userAccount;
}


public String getUserName(){
    return userName;
}


public String getUserId(){
    return userId;
}


}