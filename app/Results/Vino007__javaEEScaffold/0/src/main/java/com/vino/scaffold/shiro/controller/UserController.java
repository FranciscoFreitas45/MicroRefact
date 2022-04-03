package com.vino.scaffold.shiro.controller;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.vino.scaffold.controller.base.BaseController;
import com.vino.scaffold.entity.Constants;
import com.vino.scaffold.shiro.entity.Role;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.exception.UserDuplicateException;
import com.vino.scaffold.shiro.service.RoleService;
import com.vino.scaffold.shiro.service.UserExcelService;
import com.vino.scaffold.shiro.service.UserService;
import com.vino.scaffold.utils.Servlets;
import com.vino.scaffold.shiro.Interface.RoleService;
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

@Autowired
 private  UserService userService;

@Autowired
 private  RoleService roleService;

@Autowired
 private  UserExcelService userExcelService;


@RequiresPermissions("user:view")
@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String findUser(Model model,Long id){
    model.addAttribute("user", userService.findOne(id));
    return "user/detail";
}


@RequiresPermissions("user:menu")
@RequestMapping(value = "/all", method = RequestMethod.GET)
public String getALLUsers(Model model,int pageNumber,int pageSize,String sortType){
    Page<User> userPage = userService.findAll(buildPageRequest(pageNumber));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    // model.addAttribute("searchParams", "");
    return "user/list";
}


@RequiresPermissions("user:upload")
@ResponseBody
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public String upload(Model model,MultipartFile file,HttpServletRequest request){
    Page<User> userPage = userService.findAll(buildPageRequest(1));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    if (!file.isEmpty()) {
        // ����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        // ���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص�
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
            List<User> uploadUsers = userExcelService.getFromExcel(new File(realPath + "\\" + file.getOriginalFilename()));
            userService.saveWithCheckDuplicate(uploadUsers);
            log.info("�ϴ��û�:" + Arrays.toString(uploadUsers.toArray()));
        } catch (IOException e) {
            log.error("������ȡ�ļ�����");
            e.printStackTrace();
            return "saveFileError";
        } catch (BiffException e) {
            e.printStackTrace();
            return "fileStreamError";
        } catch (UserDuplicateException e) {
            e.printStackTrace();
            log.warn("�ϴ��ļ����������ݿ��ظ��Ķ���");
            return "entityDuplicate";
        }
    } else {
        return "fileEmpty";
    }
    return "uploadSuccess";
}


@RequiresPermissions("user:create")
@RequestMapping(value = "/add", method = RequestMethod.POST)
public String addUser(Model model,User user,HttpSession session){
    User curUser = (User) session.getAttribute(Constants.CURRENT_USER);
    try {
        userService.saveWithCheckDuplicate(user);
    } catch (UserDuplicateException e) {
        model.addAttribute("userDuplicate", "true");
        e.printStackTrace();
    }
    Page<User> userPage = userService.findAll(buildPageRequest(1));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    return "user/list";
}


@RequiresPermissions("user:view")
@RequestMapping(value = "/profile", method = RequestMethod.GET)
public String getProfile(Model model,HttpSession session){
    /*model.addAttribute("currentUser",session.getAttribute(Constants.CURRENT_USER));*/
    return "user/profile";
}


@RequiresPermissions("user:create")
@RequestMapping(value = "/prepareAdd", method = RequestMethod.GET)
public String prepareAddUser(Model model){
    return "user/add";
}


@RequiresPermissions("user:delete")
@RequestMapping(value = "/delete", method = RequestMethod.POST)
public String deleteUsers(Model model,Long[] deleteIds){
    userService.delete(deleteIds);
    Page<User> userPage = userService.findAll(buildPageRequest(1));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    return "user/list";
}


@RequiresPermissions("user:update")
@RequestMapping(value = "/update", method = RequestMethod.POST)
public String updateUser(Model model,User user){
    userService.update(user);
    Page<User> userPage = userService.findAll(buildPageRequest(1));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    return "user/list";
}


@RequiresPermissions("user:view")
@RequestMapping(value = "/search", method = RequestMethod.GET)
public String getUsersByCondition(Model model,User user,int pageNumber,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    log.info("��������=" + searchParams.toString());
    Page<User> userPage = userService.findUserByCondition(searchParams, buildPageRequest(pageNumber));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
    System.out.println("���ص�ҳ�����������" + Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
    System.out.println(searchParams.toString());
    model.addAttribute("searchParamsMap", searchParams);
    return "user/list";
}


@RequiresPermissions("user:update")
@RequestMapping(value = "/{id}", method = RequestMethod.GET)
public String prepareUpdateUser(Model model,Long id){
    model.addAttribute("user", userService.findOne(id));
    return "user/edit";
}


@RequiresPermissions("user:download")
@RequestMapping(value = "/download", method = RequestMethod.GET)
public ResponseEntity<byte[]> download(Long[] downloadIds,HttpSession session) throws IOException{
    String realPath = session.getServletContext().getRealPath("/WEB-INF/upload");
    String fileName = "userExport" + System.currentTimeMillis() + ".xls";
    userExcelService.saveToExcel(realPath + "\\" + fileName, downloadIds);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDispositionFormData("attachment", fileName);
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    // FileInputStream fin=new FileInputStream(new File(realPath+"\\"+fileName));
    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(realPath + "\\" + fileName)), headers, HttpStatus.CREATED);
}


@RequiresPermissions("user:bind")
@RequestMapping(value = "/bind", method = RequestMethod.POST)
public String bind(Model model,Long userId,Long[] roleIds){
    userService.clearAllUserAndRoleConnection(userId);
    if (roleIds != null) {
        userService.connectUserAndRole(userId, roleIds);
    }
    Page<User> userPage = userService.findAll(buildPageRequest(1));
    model.addAttribute("users", userPage.getContent());
    model.addAttribute("page", userPage);
    return "user/list";
}


@RequiresPermissions("user:upload")
@RequestMapping(value = "/prepareUpload", method = RequestMethod.GET)
public String prepareUpload(){
    return "user/upload";
}


@RequiresPermissions("user:bind")
@RequestMapping(value = "/prepareBind/{id}", method = RequestMethod.GET)
public String prepareBind(Model model,Long id){
    User user = userService.findOne(id);
    model.addAttribute("user", user);
    List<Role> roles = roleService.findAll();
    for (Role r : user.getRoles()) roles.remove(r);
    model.addAttribute("availableRoles", roles);
    return "user/bind";
}


}