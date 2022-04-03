package com.zis.shiro.controller;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.RolePermission;
import com.zis.shiro.bean.User;
import com.zis.shiro.dto.RegistRoleDto;
import com.zis.shiro.dto.RegistUserDto;
import com.zis.shiro.service.RegistAndUpdateService;
import com.zis.shiro.util.ActionHelpUtil;
import com.zis.shop.bean.Company;
@Controller
@RequestMapping(value = "/shiro")
public class HelpController extends ActionHelpUtil{

 private  Logger logger;

@Autowired
 private  RegistAndUpdateService registAndUpdateService;


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "updateRole")
public String updateRole(RegistRoleDto registRoleDto,ModelMap map){
    if (registRoleDto.getId() == null) {
        map.put("actionError", "修改角色ID异常,请联系管理员");
        logger.error("修改角色ID异常,请联系管理员 " + registRoleDto.getId());
        return "error";
    }
    if (registRoleDto.getPermissionIds() == null) {
        registRoleDto.setPermissionIds(new ArrayList<Integer>());
        List<RolePermission> list = this.registAndUpdateService.findRolePermissionByRoleId(registRoleDto.getId());
        for (RolePermission rp : list) {
            registRoleDto.getPermissionIds().add(rp.getPermissionId());
        }
    }
    map.put("role", this.registAndUpdateService.findRoleByRoleId(registRoleDto.getId()));
    map.put("checkedIds", registRoleDto.getPermissionIds());
    putPermissionToView(map);
    return "shiro/update/alter-role";
}


@RequestMapping(value = "/gotoGeneralUserUpdatePassword")
public String gotoGeneralUserUpdatePassword(){
    return "shiro/update/alter-general-user-password";
}


@RequiresPermissions(value = { "xxxxx:gotoCreatePermission" })
@RequestMapping(value = "/gotoCreatePermission")
public String gotoCreatePermission(ModelMap map){
    map.put("groupNames", groupNames());
    return "shiro/createPermission";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/updateUser")
public String updateUser(RegistUserDto registUserDto,ModelMap map){
    if (registUserDto.getId() == null) {
        map.put("actionError", "修改用户ID异常,请联系管理员");
        logger.error("修改用户ID异常,请联系管理员 " + registUserDto.getId());
        return "error";
    }
    // 如果用户名为空则查询
    if (StringUtils.isBlank(registUserDto.getUserName())) {
        User user = this.registAndUpdateService.findOneUser(registUserDto.getId());
        if (user == null) {
            map.put("actionError", "用户ID非法使用");
            return "error";
        }
        Company company = this.registAndUpdateService.findCompanyById(user.getCompanyId());
        registUserDto.setUserName(user.getUserName());
        registUserDto.setPasswordAgain(user.getPassword());
        registUserDto.setPassword(user.getPassword());
        registUserDto.setRoleId(user.getRoleId());
        registUserDto.setRealName(user.getRealName());
        registUserDto.setCompanyId(user.getCompanyId());
        if (company != null) {
            registUserDto.setCompanyName(company.getCompanyName());
        } else {
            registUserDto.setCompanyName("");
        }
    }
    map.put("checkedId", registUserDto.getRoleId());
    map.put("checkedCompanyId", registUserDto.getCompanyId());
    map.put("user", registUserDto);
    map.put("roleList", this.registAndUpdateService.findAllRole());
    map.put("companyList", this.registAndUpdateService.findAllCompany());
    map.put("companyName", registUserDto.getCompanyName());
    return "shiro/update/alter-user";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/gotoRegistUser")
public String gotoRegistUser(ModelMap map,Integer companyId){
    List<Role> roleList = this.registAndUpdateService.findAllRole();
    List<Company> companyList = this.registAndUpdateService.findAllCompany();
    map.put("checkedCompanyId", companyId);
    map.put("roleList", roleList);
    map.put("companyList", companyList);
    return "shiro/regist/regist-user";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/gotoRegistRole")
public String showRegistRole(ModelMap map){
    map.put("registList", this.registAndUpdateService.getGroupPermissions(SHIRO));
    map.put("purchaseList", this.registAndUpdateService.getGroupPermissions(PURCHASE));
    map.put("requirementList", this.registAndUpdateService.getGroupPermissions(REQUIREMENT));
    map.put("bookInfoList", this.registAndUpdateService.getGroupPermissions(BOOK_INFO));
    map.put("toolkitList", this.registAndUpdateService.getGroupPermissions(TOOLKIT));
    map.put("stockList", this.registAndUpdateService.getGroupPermissions(STOCK));
    map.put("dataList", this.registAndUpdateService.getGroupPermissions(DATA));
    map.put("orderList", this.registAndUpdateService.getGroupPermissions(ORDER));
    map.put("shopList", this.registAndUpdateService.getGroupPermissions(SHOP));
    return "shiro/regist/regist-role";
}


}