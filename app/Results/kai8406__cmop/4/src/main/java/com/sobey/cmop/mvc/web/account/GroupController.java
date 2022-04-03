package com.sobey.cmop.mvc.web.account;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.Permission;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/account/group")
public class GroupController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@ModelAttribute("allPermissions")
public Permission[] allGroups(){
    return Permission.values();
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "account/groupForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Group group,String[] permissionArray,RedirectAttributes redirectAttributes){
    group.setPermissionList(getPermissionList(permissionArray));
    comm.accountService.saveGroup(group);
    redirectAttributes.addFlashAttribute("message", "创建权限 " + group.getName() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,String name,String[] permissionArray,RedirectAttributes redirectAttributes){
    Group group = comm.accountService.getGroup(id);
    group.setName(name);
    group.setPermissionList(getPermissionList(permissionArray));
    comm.accountService.saveGroup(group);
    redirectAttributes.addFlashAttribute("message", "修改权限 " + group.getName() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("group", comm.accountService.getGroup(id));
    model.addAttribute("permissions", comm.accountService.getPermissionByGroupId(id));
    return "account/groupForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.accountService.getGroupPageable(searchParams, pageNumber, pageSize));
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "account/groupList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    boolean flag = comm.accountService.deleteGroup(id);
    redirectAttributes.addFlashAttribute("message", flag ? "删除权限成功" : "不能删除默认权限组");
    return REDIRECT_SUCCESS_URL;
}


public List<String> getPermissionList(String[] permissionArray){
    return new ArrayList<String>(Arrays.asList(permissionArray));
}


}