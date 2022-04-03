package com.ipe.module.core.web.controller;
 import com.ipe.module.core.entity.Resource;
import com.ipe.module.core.entity.Role;
import com.ipe.module.core.service.RoleService;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/role")
public class RoleController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  RoleService roleService;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(Role role,RestRequest rest){
    try {
        role.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        roleService.save(role);
        return success(role);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(Role role,RestRequest rest){
    try {
        role.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        roleService.save(role);
        return success(role);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/addUserRole" })
@ResponseBody
public BodyWrapper addUserRole(String[] urids,String userId){
    try {
        roleService.saveUserRole(urids, userId);
        return success();
    } catch (Exception e) {
        LOG.error("addRole error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/addAuthority" })
@ResponseBody
public BodyWrapper addAuthority(String[] ids,String roleId){
    try {
        roleService.saveAuthority(ids, roleId);
        return success();
    } catch (Exception e) {
        LOG.error("addAuthority error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        roleService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(Role role,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<Role> page = roleService.findAll(null, new PageRequest(startRow, endRow, rest.getSorts()));
        return success(page);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/getAuthority" })
@ResponseBody
public BodyWrapper getAuthority(String roleId){
    try {
        List<Resource> lists = roleService.getAuthoritys(roleId);
        return success(lists);
    } catch (Exception e) {
        LOG.error("getAuthority error", e);
        return failure(e);
    }
}


}