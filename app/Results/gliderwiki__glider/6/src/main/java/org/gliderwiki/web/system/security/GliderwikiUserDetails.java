package org.gliderwiki.web.system.security;
 import java.util.Collection;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class GliderwikiUserDetails extends User{

 private  long serialVersionUID;

 private  MemberSessionVo memberSessionVo;

public GliderwikiUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities, MemberSessionVo memberSessionVo) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.memberSessionVo = memberSessionVo;
}
public MemberSessionVo getMemberSessionVo(){
    return memberSessionVo;
}


}