package org.gliderwiki.web.system.security;
 import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.domain.WeAccess;
import org.gliderwiki.web.wiki.common.service.RestrictIpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;
public class IPRestrictFilter extends OncePerRequestFilter{

 private Logger logger;

 private  RequestManager requestManager;

 private  RestrictIpService restrictIpService;

 private  String targetRole;


@Override
public void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain){
    // 사용자의 역할을 가져온다.
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && targetRole != null) {
        boolean isAllowedRole = false;
        // 로그인유저인지를 확인한다.
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(targetRole)) {
                isAllowedRole = true;
                break;
            }
        }
        if (isAllowedRole) {
            // DB에 저장되어 있는 차단목록을 가져온다
            if (!CollectionUtils.isEmpty(restrictIpService.getRestrictIpList())) {
                for (WeAccess weAccess : restrictIpService.getRestrictIpList()) {
                    String restrictIp = weAccess.getWe_target_ip();
                    String currentIp = requestManager.getRemoteAddress(request);
                    if (StringUtils.equals(restrictIp, currentIp)) {
                        logger.debug("restrictIp, currentIp : {}{}", restrictIp, currentIp);
                        throw new AccessDeniedException(currentIp + "는 접근이 허용되지 않는 IP 주소입니다.");
                    }
                }
            }
        }
    } else {
        throw new AccessDeniedException("인증되지 않은 사용자입니다.");
    }
    chain.doFilter(request, response);
}


public void setRequestManager(RequestManager requestManager){
    this.requestManager = requestManager;
}


public void setTargetRole(String targetRole){
    this.targetRole = targetRole;
}


public void setRestrictIpService(RestrictIpService restrictIpService){
    this.restrictIpService = restrictIpService;
}


}