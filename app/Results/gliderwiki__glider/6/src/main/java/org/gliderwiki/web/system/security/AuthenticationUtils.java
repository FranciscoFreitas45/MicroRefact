package org.gliderwiki.web.system.security;
 import java.util.Collection;
import org.gliderwiki.web.system.interceptor.GlobalRequestAttributesInterceptor;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
public class AuthenticationUtils {

 static  Logger logger;


public Authentication getAuthentication(){
    if (SecurityContextHolder.getContext() == null) {
        logger.debug("null 1번");
        return null;
    }
    return SecurityContextHolder.getContext().getAuthentication();
}


public String getCurrentUserId(){
    if (getGliderwikiUser() == null) {
        MemberSessionVo account = MemberSessionVo.GUEST_USER.getGuestUser();
        return account.getWeUserId();
    }
    return getGliderwikiUser().getWeUserId();
}


public MemberSessionVo getUser(Authentication authentication){
    if (authentication == null) {
        return MemberSessionVo.GUEST_USER.getGuestUser();
    }
    Object principal = authentication.getPrincipal();
    if (principal == null) {
        logger.debug("null 3번");
        throw new NullPointerException("Principal should not null!");
    }
    if (principal instanceof GliderwikiUserDetails) {
        GliderwikiUserDetails userDetails = (GliderwikiUserDetails) principal;
        MemberSessionVo account = userDetails.getMemberSessionVo();
        return account;
    } else {
        return MemberSessionVo.GUEST_USER.getGuestUser();
    }
}


public boolean hasRole(GliderAuthorityType authorityType){
    return getAuthorities().contains(new GrantedAuthorityImpl(authorityType.name()));
}


public boolean isAuthenticated(){
    if (getAuthentication() == null) {
        return false;
    }
    AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    if (getAuthentication().isAuthenticated()) {
        return !trustResolver.isAnonymous(getAuthentication());
    }
    return false;
}


public Collection<GrantedAuthority> getAuthorities(){
    return getAuthentication().getAuthorities();
}


public MemberSessionVo getGliderwikiUser(){
    return getUser(getAuthentication());
}


}