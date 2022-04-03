package com.ukefu.webim.web.handler;
 import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.OrganRoleRepository;
import com.ukefu.webim.service.repository.RoleAuthRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.service.repository.UserRoleRepository;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.OrganRole;
import com.ukefu.webim.web.model.Role;
import com.ukefu.webim.web.model.RoleAuth;
import com.ukefu.webim.web.model.SystemConfig;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.UserRole;
import DTO.SystemConfig;
@Controller
public class LoginController extends Handler{

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  OrganRoleRepository organRoleRes;

@Autowired
 private  UserRoleRepository userRoleRes;

@Autowired
 private  RoleAuthRepository roleAuthRes;

@Autowired
 private  OrganRepository organRepository;


@RequestMapping("/logout")
public String logout(HttpServletRequest request,HttpServletResponse response){
    request.getSession().removeAttribute(UKDataContext.USER_SESSION_NAME);
    request.getSession().invalidate();
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie != null && !StringUtils.isBlank(cookie.getName()) && !StringUtils.isBlank(cookie.getValue())) {
                if (cookie.getName().equals(UKDataContext.UKEFU_SYSTEM_COOKIES_FLAG)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }
    return "redirect:/";
}


public String validUser(User user){
    String msg = "";
    User tempUser = userRepository.findByUsernameAndDatastatus(user.getUsername(), false);
    if (tempUser != null) {
        msg = "username_exist";
        return msg;
    }
    tempUser = userRepository.findByEmailAndDatastatus(user.getEmail(), false);
    if (tempUser != null) {
        msg = "email_exist";
        return msg;
    }
    tempUser = userRepository.findByMobileAndDatastatus(user.getMobile(), false);
    if (tempUser != null) {
        msg = "mobile_exist";
        return msg;
    }
    return msg;
}


@RequestMapping("/addAdmin")
@Menu(type = "apps", subtype = "user", access = true)
public ModelAndView addAdmin(HttpServletRequest request,HttpServletResponse response,User user){
    String msg = "";
    msg = validUser(user);
    if (!StringUtils.isBlank(msg)) {
        return request(super.createRequestPageTempletResponse("redirect:/register.html?msg=" + msg));
    } else {
        user.setUname(user.getUsername());
        user.setUsertype("0");
        if (!StringUtils.isBlank(user.getPassword())) {
            user.setPassword(UKTools.md5(user.getPassword()));
        }
        user.setOrgi(super.getOrgiByTenantshare(request));
        /*if(!StringUtils.isBlank(super.getUser(request).getOrgid())) {
    			user.setOrgid(super.getUser(request).getOrgid());
    		}else {
    			user.setOrgid(UKDataContext.SYSTEM_ORGI);
    		}*/
        userRepository.save(user);
        OnlineUserUtils.clean(super.getOrgi(request));
    }
    ModelAndView view = this.processLogin(request, response, request(super.createRequestPageTempletResponse("redirect:/")), user, "");
    // 当前用户 企业id为空 调到创建企业页面
    if (StringUtils.isBlank(user.getOrgid())) {
        view = request(super.createRequestPageTempletResponse("redirect:/apps/organization/add.html"));
    }
    return view;
}


public ModelAndView processLogin(HttpServletRequest request,HttpServletResponse response,ModelAndView view,User loginUser,String referer){
    if (loginUser != null) {
        loginUser.setLogin(true);
        if (!StringUtils.isBlank(referer)) {
            view = request(super.createRequestPageTempletResponse("redirect:" + referer));
        } else {
            view = request(super.createRequestPageTempletResponse("redirect:/"));
        }
        // 登录成功 判断是否进入多租户页面
        SystemConfig systemConfig = UKTools.getSystemConfig();
        if (systemConfig != null && systemConfig.isEnabletneant() && systemConfig.isTenantconsole() && !loginUser.isSuperuser()) {
            view = request(super.createRequestPageTempletResponse("redirect:/apps/tenant/index"));
        }
        List<UserRole> userRoleList = userRoleRes.findByOrgiAndUser(loginUser.getOrgi(), loginUser);
        if (userRoleList != null && userRoleList.size() > 0) {
            for (UserRole userRole : userRoleList) {
                loginUser.getRoleList().add(userRole.getRole());
            }
        }
        if (!StringUtils.isBlank(loginUser.getOrgan())) {
            Organ organ = organRepository.findByIdAndOrgi(loginUser.getOrgan(), loginUser.getOrgi());
            if (organ != null) {
                List<OrganRole> organRoleList = organRoleRes.findByOrgiAndOrgan(loginUser.getOrgi(), organ);
                if (organRoleList.size() > 0) {
                    for (OrganRole organRole : organRoleList) {
                        loginUser.getRoleAuthMap().put(organRole.getDicvalue(), true);
                    }
                }
            }
        }
        // 获取用户的授权资源
        List<RoleAuth> roleAuthList = roleAuthRes.findAll(new Specification<RoleAuth>() {

            @Override
            public Predicate toPredicate(Root<RoleAuth> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (loginUser.getRoleList() != null && loginUser.getRoleList().size() > 0) {
                    for (Role role : loginUser.getRoleList()) {
                        list.add(cb.equal(root.get("roleid").as(String.class), role.getId()));
                    }
                }
                Predicate[] p = new Predicate[list.size()];
                cb.and(cb.equal(root.get("orgi").as(String.class), loginUser.getOrgi()));
                return cb.or(list.toArray(p));
            }
        });
        if (roleAuthList != null) {
            for (RoleAuth roleAuth : roleAuthList) {
                loginUser.getRoleAuthMap().put(roleAuth.getDicvalue(), true);
            }
        }
        loginUser.setLastlogintime(new Date());
        if (!StringUtils.isBlank(loginUser.getId())) {
            userRepository.save(loginUser);
        }
        super.setUser(request, loginUser);
        // 当前用户 企业id为空 调到创建企业页面
        if (StringUtils.isBlank(loginUser.getOrgid())) {
            view = request(super.createRequestPageTempletResponse("redirect:/apps/organization/add.html"));
        }
    }
    return view;
}


@RequestMapping(value = "/login", method = RequestMethod.POST)
@Menu(type = "apps", subtype = "user", access = true)
public ModelAndView login(HttpServletRequest request,HttpServletResponse response,User user,String referer,String sla){
    ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/"));
    if (request.getSession(true).getAttribute(UKDataContext.USER_SESSION_NAME) == null) {
        if (user != null && user.getUsername() != null) {
            final User loginUser = userRepository.findByUsernameAndPasswordAndDatastatus(user.getUsername(), UKTools.md5(user.getPassword()), false);
            if (loginUser != null && !StringUtils.isBlank(loginUser.getId())) {
                view = this.processLogin(request, response, view, loginUser, referer);
            } else {
                view = request(super.createRequestPageTempletResponse("/login"));
                if (!StringUtils.isBlank(referer)) {
                    view.addObject("referer", referer);
                }
                view.addObject("msg", "0");
            }
        }
    }
    SystemConfig systemConfig = UKTools.getSystemConfig();
    if (systemConfig != null && systemConfig.isEnableregorgi()) {
        view.addObject("show", true);
    }
    if (systemConfig != null) {
        view.addObject("systemConfig", systemConfig);
    }
    return view;
}


@Override
public Predicate toPredicate(Root<RoleAuth> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> list = new ArrayList<Predicate>();
    if (loginUser.getRoleList() != null && loginUser.getRoleList().size() > 0) {
        for (Role role : loginUser.getRoleList()) {
            list.add(cb.equal(root.get("roleid").as(String.class), role.getId()));
        }
    }
    Predicate[] p = new Predicate[list.size()];
    cb.and(cb.equal(root.get("orgi").as(String.class), loginUser.getOrgi()));
    return cb.or(list.toArray(p));
}


@RequestMapping(value = "/register")
@Menu(type = "apps", subtype = "user", access = true)
public ModelAndView register(HttpServletRequest request,HttpServletResponse response,String msg){
    ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/"));
    if (request.getSession(true).getAttribute(UKDataContext.USER_SESSION_NAME) == null) {
        view = request(super.createRequestPageTempletResponse("/register"));
    }
    if (!StringUtils.isBlank(msg)) {
        view.addObject("msg", msg);
    }
    return view;
}


}