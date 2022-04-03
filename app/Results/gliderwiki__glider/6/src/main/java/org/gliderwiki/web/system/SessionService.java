package org.gliderwiki.web.system;
 import org.gliderwiki.web.system.security.AuthenticationUtils;
import org.gliderwiki.web.system.security.GliderAuthorityType;
import org.gliderwiki.web.vo.MemberSessionVo;
public class SessionService {


public boolean hasRole(GliderAuthorityType authorityType){
    return AuthenticationUtils.hasRole(authorityType);
}


public boolean isAuthenticated(){
    return AuthenticationUtils.isAuthenticated();
}


public MemberSessionVo getLoginUser(){
    return AuthenticationUtils.getGliderwikiUser();
}


}