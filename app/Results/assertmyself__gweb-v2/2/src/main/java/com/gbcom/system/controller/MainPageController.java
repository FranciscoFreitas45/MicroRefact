package com.gbcom.system.controller;
 import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.hibernate.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.domain.SysUserRole;
import com.gbcom.system.manager.ConfigManager;
import com.gbcom.system.manager.SimpleQueryManager;
import com.gbcom.system.manager.SysCodeManager;
import com.gbcom.system.manager.SysMenuManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.DateUtil;
import com.gbcom.system.utils.PrivilegeCode;
import com.gbcom.system.utils.UserSessionUtils;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.utils.DateTimeHelper;
import com.gbcom.Interface.SysMenuManager;
import com.gbcom.Interface.SysCodeManager;
import com.gbcom.Interface.ConfigManager;
import com.gbcom.Interface.SimpleQueryManager;
@Controller
public class MainPageController extends BaseCRUDActionController{

@Autowired
 private  SysMenuManager sysMenuManager;

@Autowired
 private  SysCodeManager sysCodeManager;

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  ConfigManager configManager;

@Autowired
 private  SimpleQueryManager simpleQueryManager;


@RequestMapping
public String index(Model model){
    SysUser sysUser = sysUserManager.getSysUser();
    if (sysUser != null) {
        model.addAttribute("realName", sysUser.getDisplayName());
        // 用户授权菜单
        model.addAttribute("xmlJsonString", sysMenuManager.getUserMenuJson(sysUser.getId()));
    // model.addAttribute("companyName", sysUser.getPerson().getCompany().getName());
    }
    model.addAttribute("siteName", configManager.getSiteName().replace("product", "GB-WEB"));
    model.addAttribute("showFirst", sysUserManager.hasPrivilege(PrivilegeCode.SYS_FIRSTPAGE));
    // 初始时间
    String initDateTime = DateTimeHelper.formatTimestamp(new Timestamp(System.currentTimeMillis()), "");
    initDateTime = StringHelper.replace(initDateTime, "-", ":");
    initDateTime = StringHelper.replace(initDateTime, " ", ":");
    model.addAttribute("init_dateTime", initDateTime);
    return "view/index";
}


@RequestMapping
public String main(Model model){
    // 是否外网用户
    model.addAttribute("isRegPerson", false);
    return "view/index/main";
}


@RequestMapping
public String getLoginUser(Model model){
    SysUser sysUser = sysUserManager.getSysUser();
    String ipAddress = "";
    String dept = "";
    if (sysUser != null) {
        String sql = "select ip_address from sys_log where enter_time=(select max(enter_time) from sys_log where user_id=" + sysUser.getId() + ") and user_id=" + sysUser.getId();
        ipAddress = simpleQueryManager.getStringBySql(sql);
        if (sysUser.getPerson() != null) {
            if (sysUser.getPerson().getCompany() != null) {
                dept = sysUser.getPerson().getCompany().getName();
            }
        }
    }
    String roleName = "";
    for (SysUserRole role : sysUser.getSysUserRoles()) {
        roleName = role.getRole().getRoleName();
    }
    model.addAttribute("realName", sysUser.getDisplayName());
    model.addAttribute("userName", sysUser.getLoginName());
    model.addAttribute("ipAddress", ipAddress);
    model.addAttribute("sysRole", roleName);
    model.addAttribute("dept", dept);
    model.addAttribute("area", sysUser.getArea() == null ? "ALL" : sysUser.getArea().getAreaName());
    // model.addAttribute("nes", sysUser.getArea()==null?"ALL":SysAreaUtil.sysAreaNeIDs(sysUser.getArea()));
    model.addAttribute("nes", UserSessionUtils.getInstance().getDevDomain());
    model.addAttribute("time", DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
    return "welcome";
}


}