package org.jeecgframework.web.system.controller.core;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
@Scope("prototype")
@Controller
@RequestMapping("/mLoginController")
public class MobileLoginController extends BaseController{

 private  Logger log;

 private  SystemService systemService;

 private  UserService userService;

 private  String message;

@Autowired
 private  MutiLangServiceI mutiLangService;


@RequestMapping(params = "checkuser")
@ResponseBody
public AjaxJson checkuser(TSUser user,HttpServletRequest req){
    HttpSession session = ContextHolderUtils.getSession();
    DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
    AjaxJson j = new AjaxJson();
    // update-begin--Author:ken  Date:20140629 for?????????????????????
    if (req.getParameter("langCode") != null) {
        req.getSession().setAttribute("lang", req.getParameter("langCode"));
    }
    // update-end--Author:ken  Date:20140629 for?????????????????????
    // update-begin--Author:zhangguoming  Date:20140226 for??????????????????
    String randCode = req.getParameter("randCode");
    if (StringUtils.isEmpty(randCode)) {
        j.setMsg(mutiLangService.getLang("common.enter.verifycode"));
        j.setSuccess(false);
    } else if (!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
        // todo "randCode"????????????servlet?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        j.setMsg(mutiLangService.getLang("common.verifycode.error"));
        j.setSuccess(false);
    } else {
        // update-end--Author:zhangguoming  Date:20140226 for??????????????????
        // int users = userService.getList(TSUser.class).size();
        int users = 1;
        if (users == 0) {
            j.setMsg("a");
            j.setSuccess(false);
        } else {
            TSUser u = userService.checkUserExits(user);
            // update-begin--Author:zhangguoming  Date:20140617 for????????????bug
            if (u == null) {
                j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
                j.setSuccess(false);
                return j;
            }
            // update-end--Author:zhangguoming  Date:20140617 for????????????bug
            TSUser u2 = userService.getEntity(TSUser.class, u.getId());
            if (u != null && u2.getStatus() != 0) {
                // if (user.getUserKey().equals(u.getUserKey())) {
                if (true) {
                    // update-start-Author:zhangguoming  Date:20140825 for????????????????????????????????????????????????????????????????????????????????????
                    Map<String, Object> attrMap = new HashMap<String, Object>();
                    j.setAttributes(attrMap);
                    String orgId = req.getParameter("orgId");
                    if (oConvertUtils.isEmpty(orgId)) {
                        // ??????????????????????????????????????????????????????????????????
                        Long orgNum = systemService.getCountForJdbc("select count(1) from t_s_user_org where user_id = '" + u.getId() + "'");
                        if (orgNum > 1) {
                            attrMap.put("orgNum", orgNum);
                            attrMap.put("user", u2);
                        } else {
                            Map<String, Object> userOrgMap = systemService.findOneForJdbc("select org_id as orgId from t_s_user_org where user_id=?", u2.getId());
                            saveLoginSuccessInfo(req, u2, (String) userOrgMap.get("orgId"));
                        }
                    } else {
                        attrMap.put("orgNum", 1);
                        saveLoginSuccessInfo(req, u2, orgId);
                    }
                // update-end-Author:zhangguoming  Date:20140825 for????????????????????????????????????????????????????????????????????????????????????
                } else {
                    j.setMsg(mutiLangService.getLang("common.check.shield"));
                    j.setSuccess(false);
                }
            } else {
                j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
                j.setSuccess(false);
            }
        }
    // update-begin--Author:zhangguoming  Date:20140226 for??????????????????
    }
    // update-end--Author:zhangguoming  Date:20140226 for??????????????????
    return j;
}


public Map<String,TSFunction> getUserFunction(TSUser user){
    HttpSession session = ContextHolderUtils.getSession();
    Client client = ClientManager.getInstance().getClient(session.getId());
    // update-start--Author:JueYue  Date:2014-5-28 for:????????????,??????????????????????????????
    if (client.getFunctions() == null || client.getFunctions().size() == 0) {
        // update-end--Author:JueYue  Date:2014-5-28 for:????????????,??????????????????????????????
        Map<String, TSFunction> loginActionlist = new HashMap<String, TSFunction>();
        // update-begin--Author:jg_longjb?????????  Date:20150313 for?????????????????????????????????hql??????hibernate?????????sql??????
        /*String hql="from TSFunction t where t.id in  (select d.TSFunction.id from TSRoleFunction d where d.TSRole.id in(select t.TSRole.id from TSRoleUser t where t.TSUser.id ='"+
	           user.getId()+"' ))";
	           String hql2="from TSFunction t where t.id in  ( select b.tsRole.id from TSRoleOrg b where b.tsDepart.id in(select a.tsDepart.id from TSUserOrg a where a.tsUser.id='"+
	           user.getId()+"'))";
	           List<TSFunction> list = systemService.findHql(hql);
	           log.info("role functions:  "+list.size());
	           for(TSFunction function:list){
	              loginActionlist.put(function.getId(),function);
	           }
	           List<TSFunction> list2 = systemService.findHql(hql2);
	           log.info("org functions: "+list2.size());
	           for(TSFunction function:list2){
	              loginActionlist.put(function.getId(),function);
	           }*/
        // update-begin--Author:jg_gudongli?????????  Date:20150516 for????????????????????????????????????hql?????????????????????????????????????????????exists
        StringBuilder hqlsb1 = new StringBuilder("select distinct f from TSFunction f,TSRoleFunction rf,TSRoleUser ru  ").append("where ru.TSRole.id=rf.TSRole.id and rf.TSFunction.id=f.id and ru.TSUser.id=? ");
        StringBuilder hqlsb2 = new StringBuilder("select distinct c from TSFunction c,TSRoleOrg b,TSUserOrg a ").append("where a.tsDepart.id=b.tsDepart.id and b.tsRole.id=c.id and a.tsUser.id=?");
        List<TSFunction> list1 = systemService.findHql(hqlsb1.toString(), user.getId());
        List<TSFunction> list2 = systemService.findHql(hqlsb2.toString(), user.getId());
        for (TSFunction function : list1) {
            loginActionlist.put(function.getId(), function);
        }
        for (TSFunction function : list2) {
            loginActionlist.put(function.getId(), function);
        }
        client.setFunctions(loginActionlist);
    }
    return client.getFunctions();
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
        saveLoginSuccessInfo(req, u, orgId);
    }
    return j;
}


@Autowired
public void setUserService(UserService userService){
    this.userService = userService;
}


@RequestMapping(params = "login")
public String login(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
    DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
    TSUser user = ResourceUtil.getSessionUser();
    String roles = "";
    if (user != null) {
        List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        for (TSRoleUser ru : rUsers) {
            TSRole role = ru.getTSRole();
            roles += role.getRoleName() + ",";
        }
        if (roles.length() > 0) {
            roles = roles.substring(0, roles.length() - 1);
        }
        modelMap.put("roleName", roles);
        modelMap.put("userName", user.getUserName());
        // update-start-Author:zhangguoming  Date:20140914 for??????????????????????????????????????????
        modelMap.put("currentOrgName", ClientManager.getInstance().getClient().getUser().getCurrentDepart().getDepartname());
        // update-end-Author:zhangguoming  Date:20140914 for??????????????????????????????????????????
        request.getSession().setAttribute("CKFinder_UserRole", "admin");
        /*			// ????????????
			String indexStyle = "shortcut";
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
					continue;
				}
				if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
					indexStyle = cookie.getValue();
				}
			}
			// ???????????????????????????????????????????????????
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("bootstrap")) {
//				return "main/bootstrap_main";
//			}
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("shortcut")) {
//				return "main/shortcut_main";
//			}
//
////			update-start--Author:gaofeng  Date:2014-01-24 for:??????????????????????????????
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("sliding")) {
//				return "main/sliding_main";
//			}
//	update-start--Author:jg_longjb Date:2015-03-25 for:???????????????????????? ?????????????????? ???????????????????????????????????????
			if (StringUtils.isNotEmpty(indexStyle)&&
					!"default".equalsIgnoreCase(indexStyle)&&
					!"undefined".equalsIgnoreCase(indexStyle)) {
				if("ace".equals(indexStyle)){
					request.setAttribute("menuMap", getFunctionMap(user));
				}
				log.info("main/"+indexStyle.toLowerCase()+"_main");
				return "main/"+indexStyle.toLowerCase()+"_main";
			}
			*/
        // SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
        // if("ace".equals(sysTheme.getStyle())){
        // request.setAttribute("menuMap", getFunctionMap(user));
        // }
        // //update-start--Author:zhoujf Date:20150610 for:ace addOneTab????????????
        // Cookie cookie = new Cookie("JEECGINDEXSTYLE", sysTheme.getStyle());
        // //??????cookie?????????????????????
        // cookie.setMaxAge(3600*24*30);
        // response.addCookie(cookie);
        // update-end--Author:zhoujf Date:20150610 for:ace addOneTab????????????
        return "workflow/mobile/app/task/task-running";
    } else {
        return "workflow/mobile/login/login";
    }
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
        String PMenu = ListtoMenu.getWebosMenu(getFunctionMap(ResourceUtil.getSessionUser()));
        ContextHolderUtils.getSession().setAttribute("getPrimaryMenuForWebos", PMenu);
        j.setMsg(PMenu);
    }
    return j;
}


@RequestMapping(params = "home")
public ModelAndView home(HttpServletRequest request){
    return new ModelAndView("main/home");
}


@RequestMapping(params = "logout")
public ModelAndView logout(HttpServletRequest request){
    HttpSession session = ContextHolderUtils.getSession();
    TSUser user = ResourceUtil.getSessionUser();
    systemService.addLog("??????" + user.getUserName() + "?????????", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
    ClientManager.getInstance().removeClinet(session.getId());
    session.invalidate();
    ModelAndView modelAndView = new ModelAndView(new RedirectView("mLoginController.do?login"));
    return modelAndView;
}


public void saveLoginSuccessInfo(HttpServletRequest req,TSUser user,String orgId){
    TSDepart currentDepart = systemService.get(TSDepart.class, orgId);
    user.setCurrentDepart(currentDepart);
    HttpSession session = ContextHolderUtils.getSession();
    // update-begin--update---author:scott-----------date:20151218-------for:???????????????????????????----------
    session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
    // update-end--author:scott-----------date:20151218-------for:???????????????????????????---------------------
    message = mutiLangService.getLang("common.user") + ": " + user.getUserName() + "[" + currentDepart.getDepartname() + "]" + mutiLangService.getLang("common.login.success");
    // update-start-Author:jg_renjie  Date:20151220 for???TASK #804 ????????????????????????????????????????????????????????????????????????????????????????????????
    // ??????session?????? ?????? ??????session???????????????????????????????????????????????????????????????Client??????
    Client clientOld = ClientManager.getInstance().getClient(session.getId());
    if (clientOld == null || clientOld.getUser() == null || user.getUserName().equals(clientOld.getUser().getUserName())) {
        Client client = new Client();
        client.setIp(IpUtil.getIpAddr(req));
        client.setLogindatetime(new Date());
        client.setUser(user);
        ClientManager.getInstance().addClinet(session.getId(), client);
    } else {
        // ???????????????????????????session?????????session=req.getSession(true)?????????session
        ClientManager.getInstance().removeClinet(session.getId());
        session.invalidate();
        // session?????????
        session = req.getSession(true);
        session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
        // ???????????????
        session.setAttribute("randCode", req.getParameter("randCode"));
        checkuser(user, req);
    }
    // update-end-Author:jg_renjie  Date:20151220 for???TASK #804 ????????????????????????????????????????????????????????????????????????????????????????????????
    // ??????????????????
    systemService.addLog(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
}


@RequestMapping(params = "primaryMenu")
@ResponseBody
public String getPrimaryMenu(){
    List<TSFunction> primaryMenu = getFunctionMap(ResourceUtil.getSessionUser()).get(0);
    String floor = "";
    // update-start--Author:zhangguoming  Date:20140923 for?????????????????????????????????????????????????????????bug
    if (primaryMenu == null) {
        return floor;
    }
    // update-end--Author:zhangguoming  Date:20140923 for?????????????????????????????????????????????????????????bug
    for (TSFunction function : primaryMenu) {
        if (function.getFunctionLevel() == 0) {
            String lang_key = function.getFunctionName();
            String lang_context = mutiLangService.getLang(lang_key);
            lang_context = lang_context.trim();
            // update-start--Author:huangzq  Date:20160113 for???:TASK#858::??????????????????logo??????
            if ("????????????".equals(lang_context)) {
                String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/ywsq.png' /> " + " <img class='imag2' src='plug-in/login/images/ywsq-up.png' style='display: none;' />" + ss + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/grbg.png' /> " + " <img class='imag2' src='plug-in/login/images/grbg-up.png' style='display: none;' />" + ss + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/lcsj.png' /> " + " <img class='imag2' src='plug-in/login/images/lcsj-up.png' style='display: none;' />" + ss + " </li> ";
            } else if ("Online ??????".equals(lang_context)) {
                floor += " <li><img class='imag1' src='plug-in/login/images/online.png' /> " + " <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />" + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/zdybd.png' /> " + " <img class='imag2' src='plug-in/login/images/zdybd-up.png' style='display: none;' />" + ss + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                floor += " <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> " + " <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />" + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                floor += " <li><img class='imag1' src='plug-in/login/images/tjbb.png' /> " + " <img class='imag2' src='plug-in/login/images/tjbb_up.png' style='display: none;' />" + " </li> ";
            } else if ("???????????????".equals(lang_context)) {
                String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> " + " <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + ss + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                floor += " <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> " + " <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />" + " </li> ";
            } else if ("????????????".equals(lang_context)) {
                floor += " <li><img class='imag1' src='plug-in/login/images/cysl.png' /> " + " <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />" + " </li> ";
            } else if (lang_context.contains("????????????")) {
                String s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>????????????</div>";
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> " + " <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + s + "</li> ";
            } else {
                // ???????????????????????????????????????
                String s = "";
                if (lang_context.length() >= 5 && lang_context.length() < 7) {
                    s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                } else if (lang_context.length() < 5) {
                    s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>" + lang_context + "</div>";
                } else if (lang_context.length() >= 7) {
                    s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context.substring(0, 6) + "</span></div>";
                }
                floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> " + " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />" + s + "</li> ";
            }
        }
    }
    // update-end--Author:huangzq  Date:20160114 for???:TASK#858::??????????????????logo??????
    return floor;
}


@RequestMapping(params = "goPwdInit")
public String goPwdInit(){
    return "login/pwd_init";
}


public void assembleFunctionsByRole(Map<String,TSFunction> loginActionlist,TSRole role){
    List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
    for (TSRoleFunction roleFunction : roleFunctionList) {
        TSFunction function = roleFunction.getTSFunction();
        // update-begin--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
        if (function.getFunctionType().intValue() == Globals.Function_TYPE_FROM.intValue()) {
            // ??????????????????????????? ??????????????????????????????
            continue;
        }
        // update-end--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
        loginActionlist.put(function.getId(), function);
    }
}


@RequestMapping(params = "noAuth")
public ModelAndView noAuth(HttpServletRequest request){
    return new ModelAndView("common/noAuth");
}


@RequestMapping(params = "userOrgSelect")
@ResponseBody
public AjaxJson userOrgSelect(HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    List<Map<String, String>> orgList = new ArrayList<Map<String, String>>();
    String userId = oConvertUtils.getString(request.getParameter("userId"));
    List<Object[]> orgArrList = systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.tsDepart.id and uo.tsUser.id=?", new String[] { userId });
    for (Object[] departs : orgArrList) {
        TSDepart depart = (TSDepart) departs[0];
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", depart.getId());
        map.put("departname", depart.getDepartname());
        orgList.add(map);
    }
    j.setObj(orgList);
    return j;
}


@RequestMapping(params = "login3")
public String login3(){
    return "login/login3";
}


@RequestMapping(params = "login2")
public String login2(){
    return "login/login2";
}


public Map<Integer,List<TSFunction>> getFunctionMap(TSUser user){
    HttpSession session = ContextHolderUtils.getSession();
    Client client = ClientManager.getInstance().getClient(session.getId());
    if (client.getFunctionMap() == null || client.getFunctionMap().size() == 0) {
        Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
        Map<String, TSFunction> loginActionlist = getUserFunction(user);
        if (loginActionlist.size() > 0) {
            Collection<TSFunction> allFunctions = loginActionlist.values();
            for (TSFunction function : allFunctions) {
                // update-begin--Author:anchao  Date:20140913 for???????????????--------------------
                if (function.getFunctionType().intValue() == Globals.Function_TYPE_FROM.intValue()) {
                    // ??????????????????????????? ??????????????????????????????
                    continue;
                }
                // update-end--Author:anchao  Date:20140913 for???????????????--------------------
                if (!functionMap.containsKey(function.getFunctionLevel() + 0)) {
                    functionMap.put(function.getFunctionLevel() + 0, new ArrayList<TSFunction>());
                }
                functionMap.get(function.getFunctionLevel() + 0).add(function);
            }
            // ???????????????
            Collection<List<TSFunction>> c = functionMap.values();
            for (List<TSFunction> list : c) {
                Collections.sort(list, new NumberComparator());
            }
        }
        client.setFunctionMap(functionMap);
        return functionMap;
    } else {
        return client.getFunctionMap();
    }
}


}