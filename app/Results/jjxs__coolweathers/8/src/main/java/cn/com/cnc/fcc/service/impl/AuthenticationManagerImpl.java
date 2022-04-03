package cn.com.cnc.fcc.service.impl;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.com.cnc.fcc.domain.PapiToken;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.repository.RbacUserRepository;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.MD5Util;
@Service("AuthenticationManagerImpl")
public class AuthenticationManagerImpl implements AuthenticationManager{

@Autowired
 private HttpServletRequest request;

 static  List<GrantedAuthority> AUTHORITIES;

 private  RbacUserRepository rbacUserRepository;

 private  EntityManagerFactory emf;

 private  DateUtil dateUtil;

 private  Logger log;

// 构造方法初期 自己的构造方法
public AuthenticationManagerImpl(EntityManagerFactory emf, RbacUserRepository rbacUserRepository, DateUtil dateUtil) {
    this.emf = emf;
    this.rbacUserRepository = rbacUserRepository;
    this.dateUtil = dateUtil;
}
@SuppressWarnings({ "unchecked" })
@Override
public Authentication authenticate(Authentication authentication){
    EntityManager em = emf.createEntityManager();
    int identifying = 0;
    if (authentication.getDetails() != null) {
        JSONObject jsonObject = JSONObject.parseObject(authentication.getDetails().toString());
        identifying = jsonObject.getIntValue("identifying");
    }
    try {
        // 清除用户权限
        AUTHORITIES.clear();
        if (identifying == 0) {
            List<RbacUser> adminUserList = new ArrayList<RbacUser>();
            // 用户信息查询
            Query query = em.createNativeQuery("select *" + " from rbac_user user where user.user_code = :userCode and user.user_password = :userPass and user.del_flag = 0", RbacUser.class);
            query.setParameter("userCode", authentication.getPrincipal());
            query.setParameter("userPass", MD5Util.toMd5(authentication.getCredentials().toString()));
            adminUserList = query.getResultList();
            if (adminUserList.isEmpty()) {
                throw new BadCredentialsException("admin not found");
            }
            // 设置最后登录时间
            adminUserList.get(0).setUserLastLoginTime(dateUtil.getDBNowDate());
            // 设置登录登录次数
            adminUserList.get(0).setUserLoginCount(adminUserList.get(0).getUserLoginCount() + 1);
            rbacUserRepository.save(adminUserList.get(0));
            AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (adminUserList.get(0).getAppId() == null) {
                AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        } else {
            List<PapiToken> PapiToken = new ArrayList<PapiToken>();
            // 用户信息查询
            Query query = em.createNativeQuery("select *" + " from papi_token where api_code = :userCode and api_password = :userPass and del_flag = 0", PapiToken.class);
            query.setParameter("userCode", authentication.getPrincipal());
            query.setParameter("userPass", authentication.getCredentials().toString());
            PapiToken = query.getResultList();
            if (PapiToken.isEmpty()) {
                throw new BadCredentialsException("papi not found");
            }
            AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_PAPI"));
        }
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), AUTHORITIES);
}


}