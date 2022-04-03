package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.manager.SimpleQueryManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.utils.KMP;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.security.user.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gbcom.Interface.SimpleQueryManager;
@Controller
public class SysOnlineUserController extends BaseCRUDActionController<SysUser>{

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private SessionRegistry sessionRegistry;

@Autowired
 private  SimpleQueryManager simpleQueryManager;


@RequestMapping
@UserLog
public String view(Model model,String username){
    List countUsers = sessionRegistry.getAllPrincipals();
    String userName = "";
    String realName = "";
    String loginTime = "";
    String ipAddress = "";
    String dept = "";
    String onlineTimeMinute = "";
    for (Object ob : countUsers) {
        BaseUser baseUser = (BaseUser) ob;
        if (baseUser.getUsername().equals(username)) {
            userName = baseUser.getUsername();
            realName = baseUser.getRealName();
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            loginTime = sdt.format(baseUser.getLoginTime());
            SysUser sysUser = sysUserManager.getSysUser(baseUser.getLoginName());
            if (sysUser != null) {
                String sql = "select ip_address from sys_log where enter_time=(select max(enter_time) from sys_log where user_id=" + sysUser.getId() + ") and user_id=" + sysUser.getId();
                ipAddress = simpleQueryManager.getStringBySql(sql);
                if (sysUser.getPerson() != null) {
                    if (sysUser.getPerson().getCompany() != null) {
                        dept = sysUser.getPerson().getCompany().getName();
                    }
                }
            }
            onlineTimeMinute = (System.currentTimeMillis() - baseUser.getLoginTime().getTime()) / 60000 + "";
        }
    }
    model.addAttribute("userName", userName);
    model.addAttribute("realName", realName);
    model.addAttribute("loginTime", loginTime);
    model.addAttribute("ipAddress", ipAddress);
    model.addAttribute("dept", dept);
    model.addAttribute("onlineTimeMinute", onlineTimeMinute);
    return "view/system/sysOnlineUser/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    return "view/system/sysOnlineUser/grid";
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,HttpSession session,String userName,String realName){
    try {
        Page<SysUser> pageModel = new Page<SysUser>(page, rows, true);
        List countUsers = sessionRegistry.getAllPrincipals();
        List<Map> list = new ArrayList<Map>();
        Set<String> userNames = new HashSet<String>();
        int i = 1;
        // 将遍历后面的用户名与前面比较
        for (Object ob : countUsers) {
            Map<String, Object> cellMap = new HashMap<String, Object>();
            BaseUser baseUser = (BaseUser) ob;
            if (!userNames.contains(baseUser.getUsername())) {
                cellMap.put("id", i++);
                cellMap.put("userName", baseUser.getUsername());
                cellMap.put("realName", baseUser.getRealName());
                SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cellMap.put("loginTime", sdt.format(baseUser.getLoginTime()));
                SysUser sysUser = sysUserManager.getSysUser(baseUser.getLoginName());
                if (sysUser != null) {
                    String sql = "select ip_address from sys_log where enter_time=(select max(enter_time) from sys_log where user_id=" + sysUser.getId() + ") and user_id=" + sysUser.getId();
                    String ipAddress = simpleQueryManager.getStringBySql(sql);
                    cellMap.put("ipAddress", ipAddress);
                    cellMap.put("dept", "");
                    if (sysUser.getPerson() != null) {
                        if (sysUser.getPerson().getCompany() != null) {
                            String dept = sysUser.getPerson().getCompany().getName();
                            cellMap.put("dept", dept);
                        }
                    }
                }
                cellMap.put("onlineTimeMinute", (System.currentTimeMillis() - baseUser.getLoginTime().getTime()) / 60000);
                boolean boo = true;
                if ((userName != null && !userName.equals(""))) {
                    userName = new String(userName.getBytes("iso8859-1"), "UTF-8");
                    if (!KMP.kmp(userName.toLowerCase(), baseUser.getUsername().toLowerCase())) {
                        boo = false;
                    }
                }
                if ((realName != null && !realName.equals(""))) {
                    realName = new String(realName.getBytes("iso8859-1"), "UTF-8");
                    if (!KMP.kmp(realName.toLowerCase(), baseUser.getRealName().toLowerCase())) {
                        boo = false;
                    }
                }
                if (boo) {
                    userNames.add(baseUser.getUsername());
                    list.add(cellMap);
                }
            }
        }
        pageModel.setRecords(list.size());
        // 输出显示
        String json = GridJq.toJSON(list, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


}