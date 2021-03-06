package com.zis.shiro.controller;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.User;
import com.zis.shiro.dto.CreatePermissionDto;
import com.zis.shiro.dto.GeneralUserPasswordUpdateDTO;
import com.zis.shiro.dto.RegistRoleDto;
import com.zis.shiro.dto.RegistUserDto;
import com.zis.shiro.dto.UpdateUserInfo;
import com.zis.shiro.service.RegistAndUpdateService;
import com.zis.shiro.util.ActionHelpUtil;
@Controller
@RequestMapping(value = "/shiro")
public class RegistAndUpdateController extends ActionHelpUtil{

 private  Logger logger;

@Autowired
 private  RegistAndUpdateService registAndUpdateService;


@RequestMapping(value = "/updateGeneralUserPassword")
public String updateGeneralUserPassword(GeneralUserPasswordUpdateDTO generalUserPasswordUpdateDTO,BindingResult br,ModelMap map){
    if (br.hasErrors()) {
        return "shiro/update/alter-general-user-password";
    }
    String newPassword = generalUserPasswordUpdateDTO.getNewPassword();
    String newPasswordAgain = generalUserPasswordUpdateDTO.getNewPasswordAgain();
    if (!newPassword.equals(newPasswordAgain)) {
        map.put("passwordError", "?????????????????????");
        return "shiro/update/alter-general-user-password";
    }
    String actionMessage = "";
    try {
        actionMessage = this.registAndUpdateService.generalUserPasswordUpdate(generalUserPasswordUpdateDTO);
    } catch (RuntimeException e) {
        map.put("oldPasswordError", e.getMessage());
        return "shiro/update/alter-general-user-password";
    }
    map.put("actionMessage", actionMessage);
    return "shiro/update/alter-general-user-password";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/updateForUser")
public String updateForUser(RegistUserDto registUserDto,BindingResult br,ModelMap map){
    map.put("user", registUserDto);
    map.put("checkedId", registUserDto.getRoleId());
    map.put("checkedCompanyId", registUserDto.getCompanyId());
    map.put("roleList", this.registAndUpdateService.findAllRole());
    map.put("companyList", this.registAndUpdateService.findAllCompany());
    map.put("companyName", registUserDto.getCompanyName());
    map.put("roleName", registUserDto.getRoleName());
    if (br.hasErrors()) {
        return "shiro/update/alter-user";
    }
    String password = registUserDto.getPassword();
    String passwordAgain = registUserDto.getPasswordAgain();
    if (!password.equals(passwordAgain)) {
        map.put("passwordError", "?????????????????????");
        return "shiro/update/alter-user";
    }
    String actionMessage = "";
    try {
        actionMessage = this.registAndUpdateService.registAndUpdateUser(registUserDto);
    } catch (Exception e) {
        map.put("errorAction", e.getMessage());
        logger.error("????????????:" + e.getMessage(), e);
        return "shiro/update/alter-user";
    }
    map.put("actionMessage", "[" + actionMessage + "]" + "????????????");
    return "shiro/update/show-update-list";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/updateForRole")
public String updateRole(RegistRoleDto registRoleDto,BindingResult br,ModelMap map){
    map.put("role", registRoleDto);
    map.put("checkedIds", registRoleDto.getPermissionIds());
    putPermissionToView(map);
    if (br.hasErrors()) {
        return "shiro/update/alter-role";
    }
    String actionMessage = "";
    try {
        actionMessage = this.registAndUpdateService.registOrUpdateRole(registRoleDto);
    } catch (Exception e) {
        map.put("errorAction", e.getMessage());
        logger.error("????????????:" + e.getMessage(), e);
        return "shiro/update/alter-role";
    }
    map.put("actionMessage", "[" + actionMessage + "]" + " ????????????");
    return "shiro/update/show-update-role-list";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/updateWaitUser")
public String updateWaitUser(String userName,String realName,String companyName,ModelMap map,HttpServletRequest request){
    map.put("companyName", companyName);
    Pageable page = WebHelper.buildPageRequest(request);
    Page<UpdateUserInfo> userList = this.registAndUpdateService.findUserInfo(userName, realName, companyName, page);
    if (!userList.getContent().isEmpty()) {
        List<UpdateUserInfo> list = userList.getContent();
        map.put("userList", list);
        map.put("page", page.getPageNumber() + 1);
        setQueryConditionToPage("", "", userName, realName, map);
        if (userList.hasPrevious()) {
            map.put("prePage", page.previousOrFirst().getPageNumber());
        }
        if (userList.hasNext()) {
            map.put("nextPage", page.next().getPageNumber());
        }
        return "shiro/update/show-update-list";
    }
    map.put("notResult", "???????????????,????????????????????????????????????????????????????????????");
    return "shiro/update/show-update-list";
}


public ShiroHttpServletRequest getRequest(){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    return (ShiroHttpServletRequest) request;
}


public void setQueryConditionToPage(String roleName,String roleCode,String userName,String realName,ModelMap map){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(roleName)) {
        condition.append("roleName=" + roleName + "&");
    }
    if (StringUtils.isNotBlank(roleCode)) {
        condition.append("roleCode=" + roleCode + "&");
    }
    if (StringUtils.isNotBlank(userName)) {
        condition.append("userName=" + userName + "&");
    }
    if (StringUtils.isNotBlank(realName)) {
        condition.append("realName=" + realName + "&");
    }
    map.put("queryCondition", condition.toString());
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/deleteUser")
public String deleteUser(Integer id,ModelMap map){
    if (id == null) {
        map.put("errorAction", "userId??????");
        logger.error("userId????????? ??????");
        return "error";
    }
    HttpSession session = getRequest().getSession();
    Integer deleteUserId = (Integer) session.getAttribute("deleteUserId");
    if (id.equals(deleteUserId)) {
        return "redirect:/shiro/updateWaitUser";
    }
    User user = this.registAndUpdateService.findOneUser(id);
    session.setAttribute("deleteUserId", id);
    if (user == null) {
        return "redirect:/shiro/updateWaitUser";
    }
    map.put("actionMessage", "[" + user.getUserName() + "]" + " ????????????");
    this.registAndUpdateService.deleteUser(id);
    return "forward:/shiro/updateWaitUser";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/updateWaitRole")
public String updateWaitRole(String roleName,String roleCode,ModelMap map,HttpServletRequest request){
    Pageable page = WebHelper.buildPageRequest(request);
    Page<Role> roleList = this.registAndUpdateService.findRoleInfo(roleName, roleCode, page);
    if (!roleList.getContent().isEmpty()) {
        map.put("roleList", roleList.getContent());
        map.put("page", page.getPageNumber() + 1);
        setQueryConditionToPage(roleName, roleCode, "", "", map);
        if (roleList.hasPrevious()) {
            map.put("prePage", page.previousOrFirst().getPageNumber());
        }
        if (roleList.hasNext()) {
            map.put("nextPage", page.next().getPageNumber());
        }
        return "shiro/update/show-update-role-list";
    }
    map.put("notResult", "???????????????,??????????????????????????????CODE???????????????");
    return "shiro/update/show-update-role-list";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/deleteRole")
public String deleteRole(Integer id,ModelMap map){
    if (id == null) {
        map.put("errorAction", "userId??????");
        logger.error("userId????????? ??????");
        return "error";
    }
    HttpSession session = getRequest().getSession();
    Integer deleteRoleId = (Integer) session.getAttribute("deleteRoleId");
    if (id.equals(deleteRoleId)) {
        return "redirect:/shiro/updateWaitRole";
    }
    Role role = this.registAndUpdateService.findOneRole(id);
    session.setAttribute("deleteRoleId", id);
    if (role == null) {
        return "redirect:/shiro/updateWaitRole";
    }
    map.put("actionMessage", "[" + role.getRoleName() + "]" + " ????????????");
    this.registAndUpdateService.deleteRole(id);
    return "forward:/shiro/updateWaitRole";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/registRole")
public String registRole(RegistRoleDto registRoleDto,BindingResult br,ModelMap map){
    map.put("checkedIds", registRoleDto.getPermissionIds());
    putPermissionToView(map);
    if (br.hasErrors()) {
        return "shiro/regist/regist-role";
    }
    String actionMessage = "";
    try {
        actionMessage = this.registAndUpdateService.registOrUpdateRole(registRoleDto);
    } catch (Exception e) {
        map.put("errorAction", e.getMessage());
        logger.error("????????????:" + e.getMessage(), e);
        return "shiro/regist/regist-role";
    }
    map.put("actionMessage", "[" + actionMessage + "]" + " ????????????");
    return "shiro/update/show-update-role-list";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/regist")
public String regist(RegistUserDto registUserDto,BindingResult br,ModelMap map){
    map.put("checkedId", registUserDto.getRoleId());
    map.put("checkedCompanyId", registUserDto.getCompanyId());
    map.put("roleList", this.registAndUpdateService.findAllRole());
    map.put("companyList", this.registAndUpdateService.findAllCompany());
    map.put("companyName", registUserDto.getCompanyName());
    map.put("roleName", registUserDto.getRoleName());
    if (br.hasErrors()) {
        return "shiro/regist/regist-user";
    }
    String password = registUserDto.getPassword();
    String passwordAgain = registUserDto.getPasswordAgain();
    if (!password.equals(passwordAgain)) {
        map.put("passwordError", "?????????????????????");
        return "shiro/regist/regist-user";
    }
    String actionMessage = "";
    try {
        actionMessage = this.registAndUpdateService.registAndUpdateUser(registUserDto);
    } catch (Exception e) {
        map.put("errorAction", e.getMessage());
        logger.error("????????????:" + e.getMessage(), e);
        return "shiro/regist/regist-user";
    }
    map.put("actionMessage", "[" + actionMessage + "]" + " ????????????");
    map.put("companyName", registUserDto.getCompanyName());
    return "shiro/update/show-update-list";
}


@RequiresPermissions(value = { "xxxx:createPermission" })
@RequestMapping(value = "/createPermission")
public String createPermission(CreatePermissionDto createPermissionDto,BindingResult br,ModelMap map){
    map.put("groupNames", groupNames());
    if (br.hasErrors()) {
        return "shiro/createPermission";
    }
    try {
        this.registAndUpdateService.savePermission(createPermissionDto);
    } catch (RuntimeException e) {
        map.put("errorAction", e.getMessage());
        return "shiro/createPermission";
    }
    map.put("createPermissionDto", createPermissionDto);
    return "shiro/success-createPermission";
}


}