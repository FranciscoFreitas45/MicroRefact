package org.gliderwiki.web.system.security;
 import java.util.ArrayList;
import java.util.Collection;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.domain.AuthorityType;
import org.gliderwiki.web.login.service.LoginService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class GliderwikiUserDetailService implements UserDetailsService{

 public  String BLANK_PASSWORD;

 private  LoginService loginService;


@Override
public UserDetails loadUserByUsername(String userId){
    MemberSessionVo result;
    try {
        result = loginService.getRowWeUserById(userId);
    } catch (Throwable e) {
        throw new GliderwikiException("로그인한 회원정보 가져오는 도중 오류발생!", e);
    }
    if (result != null) {
        // 최종방문일 업데이트 추가
        loginService.updateLastVisitDate(result.getWeUserIdx());
        return new GliderwikiUserDetails(result.getWeUserId(), BLANK_PASSWORD, true, true, true, true, createAuthorities(result), result);
    }
    return null;
}


public Collection<GrantedAuthority> createAuthorities(MemberSessionVo loginUser){
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    // 로그인한 경우에 ROLE_USER권한 기본적으로 부여
    authorities.add(new GrantedAuthorityImpl(GliderAuthorityType.ROLE_USER.name()));
    // 공간관리자 권한 부여
    if (loginUser.getWeGrade() == 3) {
        authorities.add(new GrantedAuthorityImpl(GliderAuthorityType.ROLE_SPACE_ADMIN.name()));
    }
    // 운영관리자 권한 부여
    if (loginUser.getWeGrade() == 8) {
        authorities.add(new GrantedAuthorityImpl(GliderAuthorityType.ROLE_MANAGER.name()));
    }
    // 슈퍼관리자 권한 부여
    if (loginUser.getWeGrade() == 9) {
        authorities.add(new GrantedAuthorityImpl(GliderAuthorityType.ROLE_SUPER_ADMIN.name()));
    }
    return authorities;
}


public void setLoginService(LoginService loginService){
    this.loginService = loginService;
}


}