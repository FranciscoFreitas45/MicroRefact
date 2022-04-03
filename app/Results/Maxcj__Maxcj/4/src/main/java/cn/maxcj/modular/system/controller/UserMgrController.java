package cn.maxcj.modular.system.controller;
 import cn.maxcj.config.properties.GunsProperties;
import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.constant.dictmap.UserDict;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.constant.state.ManagerStatus;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.core.shiro.ShiroUser;
import cn.maxcj.modular.system.factory.UserFactory;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.transfer.UserDto;
import cn.maxcj.modular.system.warpper.UserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import cn.maxcj.Interface.GunsProperties;
import cn.maxcj.DTO.ShiroUser;
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController{

 private  String PREFIX;

@Autowired
 private  GunsProperties gunsProperties;

@Autowired
 private  IUserService userService;


@RequestMapping("/add")
@BussinessLog(value = "添加人员", key = "account", dict = UserDict.class)
@ResponseBody
public ResponseData add(UserDto user,BindingResult result){
    if (result.hasErrors()) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 判断账号是否重复
    User theUser = userService.getByAccount(user.getAccount());
    if (theUser != null) {
        throw new ServiceException(BizExceptionEnum.USER_ALREADY_REG);
    }
    // 完善账号信息
    user.setSalt(ShiroKit.getRandomSalt(5));
    user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
    user.setStatus(ManagerStatus.OK.getCode());
    user.setCreatetime(new Date());
    user.setRoleid("21");
    user.setDeptid(38);
    this.userService.insert(UserFactory.createUser(user));
    return SUCCESS_TIP;
}


@RequestMapping("/user_info")
public String userInfo(Model model){
    Integer userId = ShiroKit.getUser().getId();
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    User user = this.userService.selectById(userId);
    model.addAttribute(user);
    model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
    model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
    LogObjectHolder.me().set(user);
    return PREFIX + "user_view.html";
}


@RequestMapping("/myclub")
@ResponseBody
public Object getMyClubUser(String name,String beginTime,String endTime){
    List<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, ShiroKit.getUser().deptId);
    return new UserWarpper(users).wrap();
}


@RequestMapping("/user_edit/{userId}")
public String userEdit(Integer userId,Model model){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // assertAuth(userId);
    User user = this.userService.selectById(userId);
    model.addAttribute(user);
    model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
    model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
    LogObjectHolder.me().set(user);
    return PREFIX + "user_edit.html";
}


@RequestMapping("/edit")
@BussinessLog(value = "修改人员", key = "account", dict = UserDict.class)
@ResponseBody
public ResponseData edit(UserDto user,BindingResult result){
    if (result.hasErrors()) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    User oldUser = userService.selectById(user.getId());
    if (ShiroKit.hasRole(Const.ADMIN_NAME) || (userService.isSheLian(ShiroKit.getUser().getId()) == 24)) {
        this.userService.updateById(UserFactory.editUser(user, oldUser));
        return SUCCESS_TIP;
    } else {
        // assertAuth(user.getId());
        ShiroUser shiroUser = ShiroKit.getUser();
        if (shiroUser.getId().equals(user.getId())) {
            this.userService.updateById(UserFactory.editUser(user, oldUser));
            return SUCCESS_TIP;
        } else {
            throw new ServiceException(BizExceptionEnum.NO_PERMITION);
        }
    }
}


@RequestMapping(method = RequestMethod.POST, path = "/upload")
@ResponseBody
public String upload(MultipartFile picture){
    String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
    try {
        String fileSavePath = gunsProperties.getFileUploadPath();
        picture.transferTo(new File(fileSavePath + pictureName));
    } catch (Exception e) {
        throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
    }
    return pictureName;
}


@RequestMapping("")
public String index(){
    return PREFIX + "user.html";
}


@RequestMapping("/unfreeze")
@BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
// @Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData unfreeze(Integer userId){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // assertAuth(userId);
    this.userService.setStatus(userId, ManagerStatus.OK.getCode());
    return SUCCESS_TIP;
}


@RequestMapping("/user_add")
public String addView(){
    return PREFIX + "user_add.html";
}


@RequestMapping("/list")
@ResponseBody
public Object list(String name,String beginTime,String endTime,Integer deptid){
    if (ShiroKit.isAdmin() || (userService.isSheLian(ShiroKit.getUser().getId()) == 24)) {
        List<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptid);
        return new UserWarpper(users).wrap();
    } else {
        DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
        List<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptid);
        return new UserWarpper(users).wrap();
    }
}


@RequestMapping("/delete")
@BussinessLog(value = "删除人员", key = "userId", dict = UserDict.class)
// @Permission
@ResponseBody
public ResponseData delete(Integer userId){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 不能删除超级管理员
    if (userId.equals(Const.ADMIN_ID)) {
        throw new ServiceException(BizExceptionEnum.CANT_DELETE_ADMIN);
    }
    // assertAuth(userId);
    this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
    return SUCCESS_TIP;
}


@RequestMapping("/role_assign/{userId}")
public String roleAssign(Integer userId,Model model){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    User user = this.userService.selectOne(new EntityWrapper<User>().eq("id", userId));
    model.addAttribute("userId", userId);
    model.addAttribute("userAccount", user.getAccount());
    return PREFIX + "user_roleassign.html";
}


@BussinessLog(value = "修改密码", key = "account", dict = UserDict.class)
@RequestMapping("/changePwd")
@ResponseBody
public Object changePwd(String oldPwd,String newPwd,String rePwd){
    if (!newPwd.equals(rePwd)) {
        throw new ServiceException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
    }
    Integer userId = ShiroKit.getUser().getId();
    User user = userService.selectById(userId);
    String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
    if (user.getPassword().equals(oldMd5)) {
        String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
        user.setPassword(newMd5);
        user.updateById();
        return SUCCESS_TIP;
    } else {
        throw new ServiceException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
    }
}


@RequestMapping("/view/{userId}")
@ResponseBody
public User view(Integer userId){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // assertAuth(userId);
    return this.userService.selectById(userId);
}


@RequestMapping("/freeze")
@BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
// @Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData freeze(Integer userId){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 不能冻结超级管理员
    if (userId.equals(Const.ADMIN_ID)) {
        throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
    }
    // assertAuth(userId);
    this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
    return SUCCESS_TIP;
}


@RequestMapping("/my_club")
public String myclub(){
    return PREFIX + "my_club_user.html";
}


public void assertAuth(Integer userId){
    if (ShiroKit.isAdmin()) {
        return;
    }
    List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
    User user = this.userService.selectById(userId);
    Integer deptid = user.getDeptid();
    if (deptDataScope.contains(deptid)) {
        return;
    } else {
        throw new ServiceException(BizExceptionEnum.NO_PERMITION);
    }
}


@RequestMapping("/setRole")
@BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
// @Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData setRole(Integer userId,String roleIds){
    if (ToolUtil.isOneEmpty(userId, roleIds)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // 不能修改超级管理员
    if (userId.equals(Const.ADMIN_ID)) {
        throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN);
    }
    // assertAuth(userId);
    this.userService.setRoles(userId, roleIds);
    return SUCCESS_TIP;
}


@RequestMapping("/reset")
@BussinessLog(value = "重置用户密码", key = "userId", dict = UserDict.class)
// @Permission(Const.ADMIN_NAME)
@ResponseBody
public ResponseData reset(Integer userId){
    if (ToolUtil.isEmpty(userId)) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    // assertAuth(userId);
    User user = this.userService.selectById(userId);
    user.setSalt(ShiroKit.getRandomSalt(5));
    user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
    this.userService.updateById(user);
    return SUCCESS_TIP;
}


@RequestMapping("/user_chpwd")
public String chPwd(){
    return PREFIX + "user_chpwd.html";
}


}