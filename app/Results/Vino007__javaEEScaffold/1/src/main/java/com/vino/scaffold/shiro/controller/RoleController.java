package com.vino.scaffold.shiro.controller;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vino.scaffold.controller.base.BaseController;
import com.vino.scaffold.entity.Constants;
import com.vino.scaffold.shiro.entity.Resource;
import com.vino.scaffold.shiro.entity.Role;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.exception.RoleDuplicateException;
import com.vino.scaffold.shiro.service.ResourceService;
import com.vino.scaffold.shiro.service.RoleService;
import com.vino.scaffold.utils.Servlets;
import com.vino.scaffold.utils.Tree;
import com.vino.scaffold.utils.TreeUtils;
import com.vino.scaffold.shiro.Interface.ResourceService;
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

@Autowired
 private  RoleService roleService;

@Autowired
 private  ResourceService resourceService;


public void setRoleService(RoleService roleService){
    this.roleService = roleService;
}


@RequiresPermissions("role:menu")
@RequestMapping(value = "/all", method = RequestMethod.GET)
public String getAllRoles(Model model,int pageNumber){
    Page<Role> rolePage = roleService.findAll(buildPageRequest(pageNumber));
    model.addAttribute("roles", rolePage.getContent());
    model.addAttribute("page", rolePage);
    return "role/list";
}


@RequiresPermissions("role:bind")
@ResponseBody
@RequestMapping(value = "/json/getResources/{id}", method = RequestMethod.GET)
public List<Tree> getResourcesByRole(Long roleId){
    List<Resource> allResources = resourceService.findAll();
    Role role = roleService.findOne(roleId);
    Set<Resource> checkedResources = role.getResources();
    List<Resource> unCheckedResources = resourceService.findAll();
    for (Resource res : checkedResources) {
        if (allResources.contains(res))
            unCheckedResources.remove(res);
    }
    return TreeUtils.fomatResourceToTree(unCheckedResources, checkedResources);
}


@RequiresPermissions("role:update")
@RequestMapping(value = "/update", method = RequestMethod.POST)
public String updateRole(Model model,Role role){
    roleService.update(role);
    Page<Role> rolePage = roleService.findAll(buildPageRequest(1));
    model.addAttribute("roles", rolePage.getContent());
    model.addAttribute("page", rolePage);
    return "role/list";
}


@RequiresPermissions("role:create")
@RequestMapping(value = "/prepareAdd", method = RequestMethod.GET)
public String prepareAddUser(Model model){
    return "role/add";
}


@RequiresPermissions("role:view")
@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String findRole(Model model,Long id){
    model.addAttribute("role", roleService.findOne(id));
    return "role/detail";
}


@RequiresPermissions("role:update")
@RequestMapping(value = "/{id}", method = RequestMethod.GET)
public String prepareUpdateRole(Model model,Long id){
    model.addAttribute("role", roleService.findOne(id));
    return "role/edit";
}


public void setResourceService(ResourceService resourceService){
    this.resourceService = resourceService;
}


@RequiresPermissions("role:bind")
@RequestMapping(value = "/bind", method = RequestMethod.POST)
public String bind(Model model,Long roleId,Long[] resourceIds){
    roleService.clearAllRoleAndResourceConnection(roleId);
    if (resourceIds != null) {
        roleService.connectRoleAndResource(roleId, resourceIds);
    }
    Page<Role> rolePage = roleService.findAll(buildPageRequest(1));
    model.addAttribute("roles", rolePage.getContent());
    model.addAttribute("page", rolePage);
    return "role/list";
}


@RequiresPermissions("role:view")
@RequestMapping(value = "/search", method = RequestMethod.GET)
public String getRolesByCondition(Model model,Role role,int pageNumber,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    log.info("��������=" + searchParams.toString());
    Page<Role> rolePage = roleService.findRoleByCondition(searchParams, buildPageRequest(pageNumber));
    model.addAttribute("roles", rolePage.getContent());
    model.addAttribute("page", rolePage);
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
    model.addAttribute("searchParamsMap", searchParams);
    return "role/list";
}


@RequiresPermissions("role:delete")
@RequestMapping(value = "/delete", method = RequestMethod.POST)
public String deleteRoles(Model model,Long[] deleteIds){
    roleService.delete(deleteIds);
    Page<Role> rolePage = roleService.findAll(buildPageRequest(1));
    model.addAttribute("roles", rolePage.getContent());
    model.addAttribute("page", rolePage);
    return "role/list";
}


@RequiresPermissions("role:bind")
@RequestMapping(value = "/prepareBind/{id}", method = RequestMethod.GET)
public String prepareBind(Model model,Long id){
    Role role = roleService.findOne(id);
    model.addAttribute("role", role);
    List<Resource> resources = resourceService.findAll();
    model.addAttribute("availableResources", resources);
    return "role/bind";
}


@RequiresPermissions("role:create")
@RequestMapping(value = "/add", method = RequestMethod.POST)
public String addRole(Model model,Role role,HttpSession session){
    User curUser = (User) session.getAttribute(Constants.CURRENT_USER);
    try {
        roleService.saveWithCheckDuplicate(role, curUser);
    } catch (RoleDuplicateException e) {
        model.addAttribute("roleDuplicate", "true");
        e.printStackTrace();
    }
    Page<Role> rolePage = roleService.findAll(buildPageRequest(1));
    model.addAttribute("roles", rolePage.getContent());
    model.addAttribute("page", rolePage);
    return "role/list";
}


}