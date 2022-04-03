package com.uec.imonitor.portal.controller;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
import com.uec.imonitor.config.service.IConfigPageParamService;
import com.uec.imonitor.config.service.IConfigTenantParamService;
import com.uec.imonitor.tenant.bean.TenantEntity;
import com.uec.imonitor.tenant.service.ITenantService;
import com.uec.imonitor.user.bean.OrgEntity;
import com.uec.imonitor.user.bean.OrgUserEntity;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.service.IOrgService;
import com.uec.imonitor.user.service.IUserService;
import com.uec.imonitor.Interface.IUserService;
import com.uec.imonitor.DTO.UserEntity;
@Scope("prototype")
@Controller
@RequestMapping(value = "")
public class PortalController extends BaseController{

 private  Log logger;

@Autowired
@Qualifier("userService")
 private  IUserService userService;

@Autowired
@Qualifier("orgService")
 private  IOrgService orgService;

@Autowired
@Qualifier("tenantService")
 private  ITenantService tenantService;

@Autowired
@Qualifier("configTenantParamService")
 private  IConfigTenantParamService configTenantParamService;

@Autowired
@Qualifier("configPageParamService")
 private  IConfigPageParamService configPageParamService;

@Value("${inews.image.server.address}")
 private  String inewsImageServer;


@RequestMapping(value = "/{tenantMark}/gotoHotImpact", method = RequestMethod.GET)
public String gotoHotImpact(Model model){
    String view = "propagationforce/frontEnd/hotImpact";
    return view;
}


@RequestMapping(value = "/{tenantMark}/gotoUpLoad", method = RequestMethod.GET)
public String gotoUpLoad(Model model){
    String view = "frontEnd/upload";
    return view;
}


@ResponseBody
@RequestMapping(value = "/login", method = RequestMethod.POST)
public ModelMap login(UserEntity user,Model model,HttpServletRequest request,HttpServletResponse response){
    // 设置session不过期
    session.setMaxInactiveInterval(-1);
    UserEntity userEntity = userService.findByUserName(user.getUserName());
    if (null == userEntity) {
        return super.getModelMap(false, null, null, "用户名不存在");
    }
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), CommonUtil.encryptPassword(user.getPassword(), userEntity.getSalt()));
    Subject currentUser = SecurityUtils.getSubject();
    try {
        currentUser.login(token);
    } catch (AuthenticationException e) {
        logger.error(this, e);
    }
    ;
    if (currentUser.isAuthenticated()) {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        Session session = subject.getSession();
        UserEntity userInfo = userService.findByUserName(userName);
        session.setAttribute("user", userInfo);
        // 获取租户id
        List<OrgUserEntity> orgUserList = orgService.findOrgUserByUserId(userInfo.getInnerid());
        if (!CollectionUtils.isEmpty(orgUserList)) {
            OrgUserEntity orgUserEntity = orgUserList.get(0);
            OrgEntity orgEntity = orgService.findOrgById(orgUserEntity.getOrgId());
            session.setAttribute("tenantId", orgEntity.getTenantId());
            // 获取租户信息
            TenantEntity tenantEntity = tenantService.findById(orgEntity.getTenantId());
            if (null != tenantEntity) {
                session.setAttribute("tenantMark", tenantEntity.getTenantMark());
            } else {
                return super.getModelMap(false, null, null, "租户不存在");
            }
        }
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        // 获取保存的URL
        if (savedRequest != null && savedRequest.getRequestUrl() != null) {
            logger.info("登录后跳转路径：" + savedRequest.getRequestUrl());
            return super.getModelMap(savedRequest.getRequestUrl());
        }
        return super.getModelMap(request.getContextPath() + "/" + session.getAttribute("tenantMark") + "/");
    } else {
        return super.getModelMap(false, null, null, "用户名或密码错误");
    }
}


@RequestMapping(value = "/{tenantMark}/gotoArticleInfluence", method = RequestMethod.GET)
public String gotoArticleInfluence(Model model){
    String view = "propagationforce/frontEnd/articleInfluence";
    return view;
}


@RequestMapping(value = "/{tenantMark}/gotoArticlerePrintedDetail", method = RequestMethod.GET)
public String gotoArticlerePrintedDetail(String queryStr,String sourceCrawl,String startTime,Integer newsId,String endTime,Model model){
    String view = "frontEnd/articlereprinteddetail";
    model.addAttribute("queryStr", queryStr);
    model.addAttribute("sourceCrawl", sourceCrawl);
    model.addAttribute("startTime", startTime);
    model.addAttribute("endTime", endTime);
    model.addAttribute("newsId", newsId);
    return view;
}


@RequestMapping(value = "/{tenantMark}/gotoIndex", method = RequestMethod.GET)
public String gotoIndex(Model model){
    String view = "propagationforce/frontEnd/index";
    return view;
}


@RequestMapping(value = "/logout", method = RequestMethod.GET)
public String logout(Model model,HttpServletRequest request,HttpServletResponse response){
    String tenantMark = (String) session.getAttribute("tenantMark");
    SecurityUtils.getSubject().logout();
    if (StringUtils.isBlank(tenantMark)) {
        return "redirect:/login";
    } else {
        return "redirect:/" + tenantMark + "/login";
    }
}


@RequestMapping(value = "/{tenantMark}/gotoReprintedDatail", method = RequestMethod.GET)
public String gotoReprintedDatail(String webpageCode,Integer newsId,Double contentSimilarity,Model model){
    String view = "frontEnd/reprinteddatail";
    model.addAttribute("webpageCode", webpageCode);
    model.addAttribute("newsId", newsId);
    model.addAttribute("contentSimilarity", contentSimilarity);
    return view;
}


@RequestMapping(value = "/{tenantMark}/gotoDetail/{webpageCode}", method = RequestMethod.GET)
public String gotoDetail(String webpageCode,Model model){
    String view = "frontEnd/temporary/detail";
    model.addAttribute("webpageCode", webpageCode);
    return view;
}


@RequestMapping(value = "/{tenantMark}/gotoUnitreProducedDetail", method = RequestMethod.GET)
public String gotoUnitreProducedDetail(String queryStr,String sourceCrawl,String startTime,String endTime,Model model){
    String view = "frontEnd/unitreproduceddetail";
    model.addAttribute("queryStr", queryStr);
    model.addAttribute("sourceCrawl", sourceCrawl);
    model.addAttribute("startTime", startTime);
    model.addAttribute("endTime", endTime);
    return view;
}


@RequestMapping(value = "/{tenantMark}/gotoMediaForwarding", method = RequestMethod.GET)
public String gotoMediaForwarding(Model model){
    String view = "propagationforce/frontEnd/mediaForwarding";
    return view;
}


@RequestMapping(value = "/gotoList", method = RequestMethod.GET)
public String gotoList(Model model){
    String view = "frontEnd/temporary/list";
    return view;
}


@RequestMapping(value = { "/{tenantMark}/", "", "/" }, method = RequestMethod.GET)
public String gotoHome(Model model){
    String view = "frontEnd/index";
    return view;
}


}