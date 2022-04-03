package com.gbcom.system.controller;
 import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.common.SysSyncException;
import com.gbcom.system.daoservice.SysDeptService;
import com.gbcom.system.daoservice.SysPersonDeptService;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysDept;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysPersonDept;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.domain.SysUserRole;
import com.gbcom.system.exts.SysUserSyncItf;
import com.gbcom.system.manager.SimpleQueryManager;
import com.gbcom.system.manager.SysLogManager;
import com.gbcom.system.manager.SysUserManager;
import com.gbcom.system.tree.ZTreeBranch;
import com.gbcom.system.tree.ZTreeNode;
import com.gbcom.system.utils.Constants;
import com.gbcom.system.utils.DateUtil;
import com.gbcom.system.utils.SecurityUtil;
import com.gbcom.system.utils.PrivilegeCode;
import com.gbcom.system.utils.UserSessionUtils;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import com.hc.core.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.binding.corba.wsdl.Array;
import org.hibernate.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.gbcom.Interface.SysUserService;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysLogManager;
import com.gbcom.Interface.SimpleQueryManager;
@Controller
public class SysUserController extends BaseCRUDActionController{

@Autowired
 private SysUserService sysUserService;

@Autowired
 private  SysUserManager sysUserManager;

@Autowired
 private  SysDeptService sysDeptService;

@Autowired
 private  SysPersonDeptService sysPersonDeptService;

@Autowired
 private  SysLogManager sysLogManager;

@Autowired
 private  SimpleQueryManager simpleQueryManager;


@RequestMapping
@UserLog
public String add(Model model){
    SysUser sysUser = new SysUser();
    int roleType = 0;
    for (SysUserRole sysUserRole : sysUserManager.getSysUser().getSysUserRoles()) {
        roleType = sysUserRole.getRole().getRoleType();
    }
    sysUser.setStatus(UserSessionUtils.USER_STATUS_VALID);
    model.addAttribute("bean", sysUser);
    model.addAttribute("isAdd", true);
    model.addAttribute("roleType", roleType);
    // 拥有角色
    model.addAttribute("roles", sysUserManager.getRoleList(sysUser));
    return "view/system/sysUser/input";
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    SysUser sysUser = sysUserService.get(id);
    int roleType = 0;
    for (SysUserRole sysUserRole : sysUserManager.getSysUser().getSysUserRoles()) {
        roleType = sysUserRole.getRole().getRoleType();
    }
    // 处理其他业务逻辑
    model.addAttribute("bean", sysUser);
    model.addAttribute("roleType", roleType);
    // 拥有角色
    model.addAttribute("roles", sysUserManager.getRoleList(sysUser));
    return "view/system/sysUser/input";
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    SysUser sysUser = sysUserService.get(id);
    model.addAttribute("bean", sysUser);
    // 拥有角色
    model.addAttribute("roles", sysUserManager.getRoleList(sysUser));
    return "view/system/sysUser/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    // 判断是否有编辑权限
    model.addAttribute("canEdit", sysUserManager.hasPrivilege(PrivilegeCode.SYS_USER_EDIT));
    return "view/system/sysUser/grid";
}


@RequestMapping
public String contact(Model model){
    List<SysUser> sysUserList = sysUserService.findAll();
    List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(sysUserList.size());
    for (int i = 0; i < sysUserList.size(); i++) {
        HashMap<String, String> userInfo = new HashMap<String, String>();
        SysUser user = sysUserList.get(i);
        String roleName = "";
        for (SysUserRole role : user.getSysUserRoles()) {
            roleName = role.getRole().getRoleName();
        }
        SysPerson person = user.getPerson();
        if (person != null) {
            userInfo.put("moblie", person.getMobile());
            userInfo.put("qq", person.getQqCode());
            userInfo.put("email", person.getEmail());
            userInfo.put("memo", person.getMemo());
            if (person.getDept() != null) {
                userInfo.put("dept", person.getDept().getName());
            } else {
                userInfo.put("dept", "部门");
            }
            if (person.getAvatarUrl() != null && !"".equals(person.getProfilePhotoUrl())) {
                userInfo.put("profile", person.getAvatarUrl());
            } else {
                userInfo.put("profile", "../html/avatars/avatar.png");
            }
            userInfo.put("name", person.getName());
            userInfo.put("address", person.getBornPlace());
        } else {
            String info = "资料待完善";
            userInfo.put("moblie", info);
            userInfo.put("qq", info);
            userInfo.put("email", info);
            userInfo.put("memo", info);
            userInfo.put("dept", info);
            userInfo.put("profile", "../html/avatars/avatar.png");
            userInfo.put("name", info);
            userInfo.put("address", info);
        }
        userInfo.put("role", roleName);
        userInfo.put("id", user.getId().toString());
        userInfo.put("realName", user.getDisplayName());
        list.add(userInfo);
    }
    model.addAttribute("userInfos", list);
    return "view/system/sysUser/contact";
}


@RequestMapping
public void save(HttpServletResponse response,SysUser entity,HttpServletRequest request){
    try {
        SysUser target;
        if (entity.getId() != null) {
            target = sysUserService.get(entity.getId());
            if (target.getLoginName() != null && target.getLoginName().equals("admin")) {
                sendSuccessJSON(response, "不允许修改admin用户的角色");
                return;
            }
            String columns = "loginName,displayName,status,person,area";
            ReflectionUtils.copyBean(entity, target, columns.split(","));
            String status = request.getParameter("status");
            if (status == null) {
                target.setStatus(UserSessionUtils.USER_STATUS_INVALID);
            }
        } else {
            target = entity;
            // 新增用户，加密密码并添加
            String password = request.getParameter("password");
            if (!StringHelper.isEmpty(password)) {
                String pass1 = SecurityUtil.MD5(password.trim());
                // String pass2 = CryptUtil.encrypt(password.trim());
                target.setPassword(pass1);
            } else {
                String pass1 = SecurityUtil.MD5("123456");
                // String pass2 = CryptUtil.encrypt(password.trim());
                target.setPassword(pass1);
            }
            if (target.getStatus() == null) {
                target.setStatus(UserSessionUtils.USER_STATUS_INVALID);
            }
            if (target.getPerson() == null) {
                target.setPerson(sysPersonDeptService.get(1L).getPerson());
            }
            if (sysUserService.findByName(target.getLoginName()) != null) {
                sendFailureJSON(response, "该用户名已经存在，请重新输入");
                return;
            }
        }
        sysUserService.save(target);
        // save roles
        sysUserManager.saveRoles(target, request.getParameterValues("roleId"));
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,HttpSession session){
    try {
        Page<SysUser> pageModel = new Page<SysUser>(page, rows, true);
        /*String hql = "select t from SysUser t" +
                    " left join t.person sp" +
                    " left join sp.sysPersonDepts spd" +
                    " left join spd.dept dept " +
                    " where 1=1 and t.loginName!='super'";
            hql += " order by dept.treeId asc,spd.orderNo asc,sp.name asc";*/
        String hql = "from SysUser t where 1=1 and t.loginName!='super' order by id";
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        String query = queryTranslate.toString();
        pageModel = sysUserService.findByPage(pageModel, query);
        session.setAttribute(Constants.GRID_SQL_KEY, query);
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_MODIFY)
public void reset(HttpServletResponse response,Long id){
    SysUser target = sysUserService.get(id);
    target.setPassword(SecurityUtil.MD5("123456"));
    sysUserService.save(target);
    sendSuccessJSON(response, "重置成功");
}


@RequestMapping
public String treeDataForSelect(String type,String id,String icon,Model model){
    ZTreeBranch treeBranch = new ZTreeBranch();
    treeBranch.setIcons(icon.split(","));
    if (StringUtils.isEmpty(id)) {
        treeBranch.addTreeNode(treeBranch.getRootNode("根节点"));
    } else if (StringUtils.equals(id, "root")) {
        // 单位
        String hql = "from SysDept where parent.id is null order by treeId asc";
        List<SysDept> nodeList = sysDeptService.findByQuery(hql);
        for (SysDept data : nodeList) {
            boolean isLeaf = data.getSysPersonDepts().size() == 0;
            if (isLeaf) {
                isLeaf = data.getSysPersonDepts().size() == 0;
            }
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setIsLeaf(isLeaf);
            treeNode.setName(data.getName());
            if (data.getIsCompany() != null && data.getIsCompany()) {
                treeNode.setIcon("1");
                treeNode.setType("company");
            } else {
                treeNode.setIcon("2");
                treeNode.setType("dept");
            }
            treeBranch.addTreeNode(treeNode);
        }
    } else if (StringUtils.equals(type, "company") || StringUtils.equals(type, "dept")) {
        // 单位/部门
        String hql = "from SysDept where parent.id=" + id + " order by treeId asc";
        List<SysDept> nodeList = sysDeptService.findByQuery(hql);
        for (SysDept data : nodeList) {
            boolean isLeaf = data.getSysPersonDepts().size() == 0;
            if (isLeaf) {
                isLeaf = data.getSysPersonDepts().size() == 0;
            }
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(String.valueOf(data.getId()));
            treeNode.setIsLeaf(isLeaf);
            treeNode.setName(data.getName());
            if (data.getIsCompany() != null && data.getIsCompany()) {
                treeNode.setIcon("1");
                treeNode.setType("company");
            } else {
                treeNode.setIcon("2");
                treeNode.setType("dept");
            }
            treeBranch.addTreeNode(treeNode);
        }
        // 人员
        List<SysPersonDept> personDeptList = sysPersonDeptService.findByQuery("from SysPersonDept where dept.id=" + id + " order by orderNo,person.name asc");
        for (SysPersonDept personDept : personDeptList) {
            SysPerson person = personDept.getPerson();
            Set<SysUser> sysUsers = person.getSysUsers();
            for (SysUser sysUser : sysUsers) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(String.valueOf(sysUser.getId()));
                treeNode.setIsLeaf(true);
                treeNode.setName(sysUser.getDisplayName());
                treeNode.setIcon("3");
                treeNode.setType("sysUser");
                treeBranch.addTreeNode(treeNode);
            }
        }
    }
    String s = treeBranch.toJsonString(false);
    model.addAttribute("msg", s);
    return "common/msg";
}


@RequestMapping
public void sync(HttpServletResponse response,HttpServletRequest request){
    SysUserSyncItf gwifManager = (SysUserSyncItf) SpringUtils.getBean("gwifManager");
    try {
        gwifManager.syncUser();
        sendSuccessJSON(response, "同步成功");
    } catch (SysSyncException e) {
        // sendSuccessJSON(response, "同步失败");
        super.processException(response, e);
    }
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    try {
        // 删除用于日志
        sysLogManager.deleteLog(id);
        sysUserManager.delete(id);
        sendSuccessJSON(response, "删除成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public String info(Model model,Long id){
    SysUser sysUser;
    if (id != null) {
        sysUser = sysUserService.get(id);
    } else {
        sysUser = sysUserManager.getSysUser();
    }
    SysPerson person = sysUser.getPerson();
    String ipAddress = "";
    String dept = "";
    List<SysLog> sysLog = new ArrayList<SysLog>();
    if (sysUser != null) {
        String sql = "select ip_address from sys_log where enter_time=(select max(enter_time) from sys_log where user_id=" + sysUser.getId() + ") and user_id=" + sysUser.getId();
        ipAddress = simpleQueryManager.getStringBySql(sql);
        // 当前时间
        Date dNow = new Date();
        Date dBefore = new Date();
        // 得到日历
        Calendar calendar = Calendar.getInstance();
        // 把当前时间赋给日历
        calendar.setTime(dNow);
        // 设置为前一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        // 得到前一天的时间
        dBefore = calendar.getTime();
        sysLog = sysLogManager.getTodayLoginCount(DateUtil.format(dBefore, "yyyy/MM/dd HH:mm:ss"));
    }
    String roleName = "";
    for (SysUserRole role : sysUser.getSysUserRoles()) {
        roleName = role.getRole().getRoleName();
    }
    if (sysLog.size() > 20) {
        sysLog = sysLog.subList(sysLog.size() - 20, sysLog.size() - 1);
    }
    model.addAttribute("sysLog", sysLog);
    model.addAttribute("realName", sysUser.getDisplayName());
    model.addAttribute("userName", sysUser.getLoginName());
    model.addAttribute("ipAddress", ipAddress);
    model.addAttribute("sysRole", roleName);
    model.addAttribute("time", DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
    model.addAttribute("moblie", person.getMobile());
    model.addAttribute("qq", person.getQqCode());
    model.addAttribute("email", person.getEmail());
    model.addAttribute("memo", person.getMemo());
    if (person.getProfilePhotoUrl() != null && !"".equals(person.getProfilePhotoUrl())) {
        model.addAttribute("profile", person.getProfilePhotoUrl());
    } else {
        model.addAttribute("profile", "../html/avatars/profile.jpg");
    }
    model.addAttribute("name", person.getName());
    model.addAttribute("address", person.getBornPlace());
    return "view/system/sysUser/info";
}


}