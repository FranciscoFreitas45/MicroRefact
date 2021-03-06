package org.jeecgframework.web.system.controller.core;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSPasswordResetkey;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.web.system.sms.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.common.util.HttpUtil;
import Interface.MutiLangServiceI;
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController{

 private  Logger log;

 private  SystemService systemService;

 private  UserService userService;

@Resource
 private  ClientManager clientManager;

@Autowired
 private  MutiLangServiceI mutiLangService;


@RequestMapping(params = "checkuser")
@ResponseBody
public AjaxJson checkuser(TSUser user,HttpServletRequest req){
    HttpSession session = req.getSession();
    AjaxJson j = new AjaxJson();
    // ????????????
    if (req.getParameter("langCode") != null) {
        req.getSession().setAttribute("lang", req.getParameter("langCode"));
    }
    // --------------------????????????----------------------------------------------------
    // ??????????????????????????????
    String returnURL = req.getParameter("ReturnURL");
    if (StringUtils.isNotEmpty(returnURL)) {
        req.getSession().setAttribute("ReturnURL", returnURL);
    }
    // --------------------????????????----------------------------------------------------
    // ?????????
    String randCode = req.getParameter("randCode");
    if (StringUtils.isEmpty(randCode)) {
        j.setMsg(mutiLangService.getLang("common.enter.verifycode"));
        j.setSuccess(false);
    } else if (!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
        j.setMsg(mutiLangService.getLang("common.verifycode.error"));
        log.info("Username:{} ,?????????: {} ??????!", user.getUserName(), randCode);
        j.setSuccess(false);
    // IP????????? check
    } else if (userService.isInBlackList(IpUtil.getIpAddr(req))) {
        log.info("Username:{} ,IP: {} ???????????????!", user.getUserName(), IpUtil.getIpAddr(req));
        j.setMsg(mutiLangService.getLang("common.blacklist.error"));
        j.setSuccess(false);
    } else {
        // ????????????????????????
        TSUser u = userService.checkUserExits(user);
        if (u == null) {
            u = userService.findUniqueByProperty(TSUser.class, "email", user.getUserName());
            // update-begin-author:taoyan date:20180723 for:TASK #2999 ?????????bug???????????????????????????????????????????????????
            if (u == null || !u.getPassword().equals(PasswordUtil.encrypt(u.getUserName(), user.getPassword(), PasswordUtil.getStaticSalt()))) {
                // update-end-author:taoyan date:20180723 for:TASK #2999 ?????????bug???????????????????????????????????????????????????
                j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
                j.setSuccess(false);
                return j;
            }
        }
        if (u != null && u.getStatus() != 0) {
            // update-begin-author???taoyan date:20180625 for:TASK #2845 ??????????????????????????????????????????????????????
            if (u.getDeleteFlag() == 1) {
                j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
                j.setSuccess(false);
                return j;
            }
            // update-end-author???taoyan date:20180625 for:TASK #2845 ??????????????????????????????????????????????????????
            // update-begin-author???jiaqiankun date:20180628 for:TASK#2861::???bug???????????????????????????????????????????????????????????????????????????????????????????????????????????????
            if ("2".equals(u.getUserType())) {
                j.setMsg(mutiLangService.getLang("common.user.interfaceUser"));
                j.setSuccess(false);
                return j;
            }
            // update-end-author???jiaqiankun date:20180628 for:TASK#2861::???bug???????????????????????????????????????????????????????????????????????????????????????????????????????????????
            // ?????????????????????????????????????????????????????????????????????????????????
            Map<String, Object> attrMap = new HashMap<String, Object>();
            j.setAttributes(attrMap);
            String orgId = req.getParameter("orgId");
            if (oConvertUtils.isEmpty(orgId)) {
                // ??????????????????????????????????????????????????????????????????
                Long orgNum = systemService.getCountForJdbcParam("select count(1) from t_s_user_org where user_id = ?", u.getId());
                if (orgNum > 1) {
                    attrMap.put("orgNum", orgNum);
                    attrMap.put("user", u);
                } else {
                    Map<String, Object> userOrgMap = systemService.findOneForJdbc("select org_id as orgId from t_s_user_org where user_id=?", u.getId());
                    userService.saveLoginUserInfo(req, u, (String) userOrgMap.get("orgId"));
                }
            } else {
                attrMap.put("orgNum", 1);
                userService.saveLoginUserInfo(req, u, orgId);
            }
        } else {
            // ??????????????????
            j.setMsg(mutiLangService.getLang("common.lock.user"));
            j.setSuccess(false);
        }
    }
    return j;
}


@RequestMapping(params = "goResetPwd")
public ModelAndView goResetPwd(String key){
    return new ModelAndView("login/resetPwd").addObject("key", key);
}


@RequestMapping(params = "primaryMenuDiy")
@ResponseBody
public String getPrimaryMenuDiy(){
    // ???????????????
    List<TSFunction> primaryMenu = userService.getFunctionMap(ResourceUtil.getSessionUser().getId()).get(1);
    // Shortcut????????????????????????????????????TODO ???????????????
    String floor = userService.getShortcutPrimaryMenuDiy(primaryMenu);
    return floor;
}


@RequestMapping(params = "login")
public String login(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
    TSUser user = ResourceUtil.getSessionUser();
    String roles = "";
    if (user != null) {
        log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>  Login ??????????????????????????????Main??????????????????  ???Main ?????????????????????  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
        List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        for (TSRoleUser ru : rUsers) {
            TSRole role = ru.getTSRole();
            roles += role.getRoleName() + ",";
        }
        if (roles.length() > 0) {
            roles = roles.substring(0, roles.length() - 1);
        }
        modelMap.put("roleName", roles.length() > 3 ? roles.substring(0, 3) + "..." : roles);
        modelMap.put("userName", user.getUserName().length() > 5 ? user.getUserName().substring(0, 5) + "..." : user.getUserName());
        modelMap.put("portrait", user.getPortrait());
        // ????????????????????????
        modelMap.put("currentOrgName", clientManager.getClient().getUser().getCurrentDepart().getDepartname());
        SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
        if ("fineui".equals(sysTheme.getStyle()) || "ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle()) || "acele".equals(sysTheme.getStyle()) || "hplus".equals(sysTheme.getStyle())) {
            request.setAttribute("menuMap", userService.getFunctionMap(user.getId()));
        }
        // update--begin--author:scott---------- date:20180702 ----------- for: i18n???????????????-----------------------------------
        // ?????????cookie
        Cookie i18n_cookie = new Cookie("i18n_browser_Lang", oConvertUtils.getString(request.getSession().getAttribute("lang")));
        // ??????cookie?????????????????????
        i18n_cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(i18n_cookie);
        // update--end--author:scott---------- date:20180702 ----------- for: i18n???????????????-----------------------------------
        // ace addOneTab????????????
        Cookie cookie = new Cookie("JEECGINDEXSTYLE", sysTheme.getStyle());
        // ??????cookie?????????????????????
        cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(cookie);
        // zIndex????????????
        Cookie zIndexCookie = new Cookie("ZINDEXNUMBER", "1990");
        // ??????
        zIndexCookie.setMaxAge(3600 * 24);
        // update--begin--author:scott---------- date:20180709 ----------- for: TASK #2945 ???bug?????????????????????????????????-------------------------
        zIndexCookie.setPath("/");
        // update--end--author:scott---------- date:20180709 ----------- for: TASK #2945 ???bug?????????????????????????????????---------------------------
        response.addCookie(zIndexCookie);
        // -----------------------????????????-------------------------------------------------
        /*
			 * ???????????? - ???????????????????????????????????????????????? ReturnURL ?????? 
			 * HttpUtil.decodeURL(xx) ??????????????????
			 */
        String returnURL = (String) request.getSession().getAttribute("ReturnURL");
        log.info("login ????????????returnURL???" + returnURL);
        if (StringUtils.isNotEmpty(returnURL)) {
            SSOToken st = new SSOToken(request);
            st.setId(UUID.randomUUID().getMostSignificantBits());
            st.setUid(user.getUserName());
            st.setType(1);
            // request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, maxAge);
            // ?????????????????? Cookie maxAge ???????????? ???????????????????????????????????????????????? - ?????????????????????????????? ???
            // maxAge ?????????-1 ?????????????????????????????? 0 ???????????? 120 ??????Cookie?????????2??????(???????????????)
            // request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 60);
            SSOHelper.setSSOCookie(request, response, st, true);
            returnURL = HttpUtil.decodeURL(returnURL);
            log.info("login ????????????returnURL???" + returnURL);
            request.getSession().removeAttribute("ReturnURL");
            try {
                response.sendRedirect(returnURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        // ------------------------????????????------------------------------------------------
        return sysTheme.getIndexPath();
    } else {
        // ------------------------????????????------------------------------------------------
        // ???????????? - ????????????
        String returnURL = (String) request.getSession().getAttribute("ReturnURL");
        if (StringUtils.isNotEmpty(returnURL)) {
            request.setAttribute("ReturnURL", returnURL);
        }
        // -----------------------????????????-------------------------------------------------
        return "login/login";
    }
}


@RequestMapping(params = "sendResetPwdMail")
@ResponseBody
public AjaxJson sendResetPwdMail(String email,HttpServletRequest request){
    AjaxJson ajaxJson = new AjaxJson();
    try {
        // step.1 ------------check????????????????????????--------------------------
        if (StringUtils.isEmpty(email)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("????????????????????????");
            return ajaxJson;
        }
        TSUser user = systemService.findUniqueByProperty(TSUser.class, "email", email);
        if (user == null) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("???????????????????????????????????????");
            return ajaxJson;
        }
        // step.2 ------------??????????????????????????????--------------------------
        String hql = "from TSPasswordResetkey bean where bean.username = ? and bean.isReset = 0 order by bean.createDate desc limit 1";
        List<TSPasswordResetkey> resetKeyList = systemService.findHql(hql, user.getUserName());
        if (resetKeyList != null && !resetKeyList.isEmpty()) {
            TSPasswordResetkey resetKey = resetKeyList.get(0);
            Date now = new Date();
            if (resetKey.getEmail().equals(email) && (now.getTime() - resetKey.getCreateDate().getTime()) < (1000 * 60 * 60 * 3 - 1000 * 60 * 5)) {
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("?????????????????????????????????????????????????????????");
                return ajaxJson;
            }
        }
        // step.3 ------------??????????????????????????????--------------------------
        TSPasswordResetkey passwordResetKey = new TSPasswordResetkey();
        passwordResetKey.setEmail(email);
        passwordResetKey.setUsername(user.getUserName());
        passwordResetKey.setCreateDate(new Date());
        passwordResetKey.setIsReset(0);
        userService.save(passwordResetKey);
        // step.4 ------------????????????????????????--------------------------
        String content = ResourceUtil.getConfigByName("resetpwd.mail.content");
        if (content.indexOf("${username}") > -1) {
            content = content.replace("${username}", user.getUserName());
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/loginController.do?goResetPwd&key=" + passwordResetKey.getId();
        // ????????????????????????
        Map<String, Object> mailConfig = new HashMap<String, Object>();
        mailConfig.put("title", ResourceUtil.getConfigByName("resetpwd.mail.title"));
        mailConfig.put("content", content);
        mailConfig.put("url", url);
        mailConfig.put("commentUrl", "http://www.jeecg.org");
        String mailContent = new FreemarkerHelper().parseTemplate("export/mail/password_reset.ftl", mailConfig);
        MailUtil.sendEmail(ResourceUtil.getConfigByName("mail.smtpHost"), email, "??????????????????", mailContent, ResourceUtil.getConfigByName("mail.sender"), ResourceUtil.getConfigByName("mail.user"), ResourceUtil.getConfigByName("mail.pwd"));
        ajaxJson.setMsg("??????????????????????????????");
    } catch (Exception e) {
        if ("javax.mail.AuthenticationFailedException".equals(e.getClass().getName())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("???????????????????????????????????????????????????");
            log.error("???????????????????????????????????????????????????????????????", e);
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("?????????????????????" + e.getMessage());
            log.error("?????????????????????" + e.getMessage(), e);
        }
    }
    return ajaxJson;
}


@RequestMapping(params = "acehome")
public ModelAndView acehome(HttpServletRequest request){
    SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
    // ACE ACE2 DIY????????????home.jsp?????????????????????js???css??????
    if ("ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle()) || "acele".equals(sysTheme.getStyle())) {
        request.setAttribute("show", "1");
    } else {
        // default???shortcut????????????????????????????????????????????????
        request.setAttribute("show", "0");
    }
    return new ModelAndView("main/acehome");
}


@RequestMapping(params = "goResetPwdMail")
public ModelAndView goResetPwdMail(){
    return new ModelAndView("login/goResetPwdMail");
}


@RequestMapping(params = "logout")
public ModelAndView logout(HttpServletRequest request){
    HttpSession session = ContextHolderUtils.getSession();
    TSUser user = ResourceUtil.getSessionUser();
    try {
        systemService.addLog("??????" + user != null ? user.getUserName() : "" + "?????????", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
        LogUtil.error(e.toString());
    }
    clientManager.removeClinet(session.getId());
    session.invalidate();
    ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
    return modelAndView;
}


@RequestMapping(params = "shortcut_top")
public ModelAndView shortcut_top(HttpServletRequest request){
    TSUser user = ResourceUtil.getSessionUser();
    HttpSession session = ContextHolderUtils.getSession();
    // ??????????????????
    if (user.getId() == null) {
        session.removeAttribute(Globals.USER_SESSION);
        return new ModelAndView(new RedirectView("loginController.do?login"));
    }
    request.setAttribute("menuMap", userService.getFunctionMap(user.getId()));
    return new ModelAndView("main/shortcut_top");
}


@RequestMapping(params = "top")
public ModelAndView top(HttpServletRequest request){
    TSUser user = ResourceUtil.getSessionUser();
    HttpSession session = ContextHolderUtils.getSession();
    // ??????????????????
    if (user.getId() == null) {
        session.removeAttribute(Globals.USER_SESSION);
        return new ModelAndView(new RedirectView("loginController.do?login"));
    }
    request.setAttribute("menuMap", userService.getFunctionMap(user.getId()));
    return new ModelAndView("main/bootstrap_top");
}


@RequestMapping(params = "primaryMenu")
@ResponseBody
public String getPrimaryMenu(){
    List<TSFunction> primaryMenu = userService.getFunctionMap(ResourceUtil.getSessionUser().getId()).get(0);
    // Shortcut????????????????????????????????????TODO ???????????????
    String floor = userService.getShortcutPrimaryMenu(primaryMenu);
    return floor;
}


@RequestMapping(params = "getAutocomplete", method = { RequestMethod.GET, RequestMethod.POST })
public void getAutocomplete(HttpServletRequest request,HttpServletResponse response){
    String searchVal = request.getParameter("q");
    // ?????????session??????????????????
    HttpSession session = ContextHolderUtils.getSession();
    Client client = clientManager.getClient(session.getId());
    // ?????????????????????map??????
    Map<Integer, List<TSFunction>> map = client.getFunctionMap();
    // ??????list??????????????????
    List<TSFunction> autoList = new ArrayList<TSFunction>();
    // ??????map??????????????????
    for (int t = 0; t < map.size(); t++) {
        // ??????map??????????????????TSFuction ???List??????
        List<TSFunction> list = map.get(t);
        // ??????List??????TSFuction??????functionname
        for (int i = 0; i < list.size(); i++) {
            // ??????functionname??????????????????????????????????????????????????????????????????MutiLangUtil??????getLang()?????????
            String name = MutiLangUtil.getLang(list.get(i).getFunctionName());
            if (name.indexOf(searchVal) != -1) {
                TSFunction ts = new TSFunction();
                ts.setFunctionName(MutiLangUtil.getLang(list.get(i).getFunctionName()));
                autoList.add(ts);
            }
        }
    }
    try {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(JSONHelper.listtojson(new String[] { "functionName" }, 1, autoList));
        response.getWriter().flush();
    } catch (Exception e1) {
        e1.printStackTrace();
    } finally {
        try {
            response.getWriter().close();
        } catch (IOException e) {
        }
    }
}


@RequestMapping(params = "changeDefaultOrg")
@ResponseBody
public AjaxJson changeDefaultOrg(TSUser user,HttpServletRequest req){
    AjaxJson j = new AjaxJson();
    Map<String, Object> attrMap = new HashMap<String, Object>();
    String orgId = req.getParameter("orgId");
    TSUser u = userService.checkUserExits(user);
    if (u == null) {
        u = userService.findUniqueByProperty(TSUser.class, "email", user.getUserName());
    }
    if (oConvertUtils.isNotEmpty(orgId)) {
        attrMap.put("orgNum", 1);
        userService.saveLoginUserInfo(req, u, orgId);
    }
    return j;
}


@Autowired
public void setUserService(UserService userService){
    this.userService = userService;
}


@Autowired
public void setSystemService(SystemService systemService){
    this.systemService = systemService;
}


@RequestMapping(params = "getPrimaryMenuForWebos")
@ResponseBody
public AjaxJson getPrimaryMenuForWebos(){
    AjaxJson j = new AjaxJson();
    // ??????????????????Session??????????????????????????????????????????
    Object getPrimaryMenuForWebos = ContextHolderUtils.getSession().getAttribute("getPrimaryMenuForWebos");
    if (oConvertUtils.isNotEmpty(getPrimaryMenuForWebos)) {
        j.setMsg(getPrimaryMenuForWebos.toString());
    } else {
        String PMenu = ListtoMenu.getWebosMenu(userService.getFunctionMap(ResourceUtil.getSessionUser().getId()));
        ContextHolderUtils.getSession().setAttribute("getPrimaryMenuForWebos", PMenu);
        j.setMsg(PMenu);
    }
    return j;
}


@RequestMapping(params = "home")
public ModelAndView home(HttpServletRequest request){
    SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
    // ACE ACE2 DIY????????????home.jsp?????????????????????js???css??????
    if ("ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle()) || "acele".equals(sysTheme.getStyle())) {
        request.setAttribute("show", "1");
    } else {
        // default???shortcut????????????????????????????????????????????????
        request.setAttribute("show", "0");
    }
    return new ModelAndView("main/home");
}


@RequestMapping(params = "fineuiHome")
public ModelAndView fineuiHome(HttpServletRequest request){
    return new ModelAndView("main/fineui_home");
}


@RequestMapping(params = "resetPwd")
@ResponseBody
public AjaxJson resetPwd(String key,String password){
    AjaxJson ajaxJson = new AjaxJson();
    TSPasswordResetkey passwordResetkey = systemService.get(TSPasswordResetkey.class, key);
    Date now = new Date();
    if (passwordResetkey != null && passwordResetkey.getIsReset() != 1 && (now.getTime() - passwordResetkey.getCreateDate().getTime()) < 1000 * 60 * 60 * 3) {
        TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName", passwordResetkey.getUsername());
        user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));
        systemService.updateEntitie(user);
        passwordResetkey.setIsReset(1);
        systemService.updateEntitie(passwordResetkey);
        ajaxJson.setMsg("??????????????????");
    } else {
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg("??????????????????KEY");
    }
    return ajaxJson;
}


@RequestMapping(params = "left")
public ModelAndView left(HttpServletRequest request){
    TSUser user = ResourceUtil.getSessionUser();
    HttpSession session = ContextHolderUtils.getSession();
    ModelAndView modelAndView = new ModelAndView();
    // ??????????????????
    if (user.getId() == null) {
        session.removeAttribute(Globals.USER_SESSION);
        modelAndView.setView(new RedirectView("loginController.do?login"));
    } else {
        modelAndView.setViewName("main/left");
        request.setAttribute("menuMap", userService.getFunctionMap(user.getId()));
    }
    return modelAndView;
}


@RequestMapping(params = "goPwdInit")
public String goPwdInit(){
    return "login/pwd_init";
}


@RequestMapping(params = "noAuth")
public ModelAndView noAuth(HttpServletRequest request){
    return new ModelAndView("common/noAuth");
}


@RequestMapping(params = "getUrlpage")
@ResponseBody
public String getUrlpage(HttpServletRequest request,HttpServletResponse response){
    String urlname = request.getParameter("urlname");
    HttpSession session = ContextHolderUtils.getSession();
    Client client = clientManager.getClient(session.getId());
    Map<Integer, List<TSFunction>> map = client.getFunctionMap();
    List<TSFunction> autoList = new ArrayList<TSFunction>();
    for (int t = 0; t < map.size(); t++) {
        List<TSFunction> list = map.get(t);
        for (int i = 0; i < list.size(); i++) {
            String funname = MutiLangUtil.getLang(list.get(i).getFunctionName());
            if (urlname.equals(funname)) {
                TSFunction ts = new TSFunction();
                ts.setFunctionUrl(list.get(i).getFunctionUrl());
                autoList.add(ts);
            }
        }
    }
    if (autoList.size() == 0) {
        return null;
    } else {
        String name = autoList.get(0).getFunctionUrl();
        return name;
    }
}


@RequestMapping(params = "login3")
public String login3(){
    return "login/login3";
}


@RequestMapping(params = "hplushome")
public ModelAndView hplushome(HttpServletRequest request){
    return new ModelAndView("main/hplushome");
}


}