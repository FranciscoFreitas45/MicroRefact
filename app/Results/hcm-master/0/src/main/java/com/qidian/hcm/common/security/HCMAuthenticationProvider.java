package com.qidian.hcm.common.security;
 import com.qidian.hcm.common.jwt.JWTGrantedAuthority;
import com.qidian.hcm.common.jwt.Jwt;
import com.qidian.hcm.common.jwt.JwtUserInfo;
import com.qidian.hcm.common.utils.CommonUtils;
import com.qidian.hcm.module.center.entity.GroupConfig;
import com.qidian.hcm.module.center.entity.User;
import com.qidian.hcm.module.center.repository.GroupConfigRepository;
import com.qidian.hcm.module.center.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
@Slf4j
public class HCMAuthenticationProvider implements AuthenticationProvider{

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  GroupConfigRepository groupConfigRepository;


@Override
public Authentication authenticate(Authentication authentication){
    User user = userRepository.findByUsername(authentication.getPrincipal().toString());
    if (user != null && CommonUtils.encoderByMd5(authentication.getCredentials().toString()).equals(user.getPassword())) {
        JwtUserInfo jwtUserInfo = new JwtUserInfo();
        jwtUserInfo.setId(user.getId());
        GroupConfig groupConfig = groupConfigRepository.findByGroupId(user.getGroupId());
        if (groupConfig == null) {
            return null;
        }
        jwtUserInfo.setTenantName(groupConfig.getTenantName());
        jwtUserInfo.setUsername(user.getUsername());
        List<JWTGrantedAuthority> authorities = new ArrayList<>();
        JWTGrantedAuthority grantedAuthority = new JWTGrantedAuthority();
        authorities.add(grantedAuthority);
        jwtUserInfo.setAuthorities(authorities);
        String token = Jwt.createToken(jwtUserInfo);
        log.info("user login success,username:{},tenantName:{}", user.getUsername(), groupConfig.getTenantName());
        return new HCMAuthenticationObject(authentication.getPrincipal(), authentication.getCredentials(), token);
    }
    return null;
}


@Override
public boolean supports(Class<?> authentication){
    return true;
}


}