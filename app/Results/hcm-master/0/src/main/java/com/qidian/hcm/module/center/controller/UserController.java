package com.qidian.hcm.module.center.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.common.utils.ResultGenerator;
import com.qidian.hcm.module.center.dto.LoginResultDTO;
import com.qidian.hcm.module.center.entity.User;
import com.qidian.hcm.module.center.request;
import com.qidian.hcm.module.center.service.UserService;
import com.qidian.hcm.module.center.service.VerificationService;
import com.qidian.hcm.module.employee.request.InitCompanyAdminRequest;
import com.qidian.hcm.module.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import com.qidian.hcm.Interface.EmployeeService;
@RestController
@RequestMapping("/api/user")
@Api(tags = "集团用户--用户登陆注册相关")
@Slf4j
public class UserController {

@Autowired
 private  UserService userService;

@Autowired
 private  VerificationService verificationService;

@Autowired
 private  EmployeeService employeeService;


@PostMapping("phoneCode/{phone}")
@ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
public Result getVerificationCode(String phone){
    return verificationService.getVerificationCode(phone);
}


@ApiIgnore("内部调用，不公开，用于管理员录入员工数据之后在center.user表中插入一条待激活记录")
@PostMapping(value = "add_not_active_user")
public Result addNotActiveUser(AddNotActiveUserRequest request){
    User user = userService.addNotActiveUser(request);
    return ResultGenerator.genSuccessResult(user.getId());
}


@ApiIgnore("内部调用，不公开，用于更新Employee中的手机号码之后同步更新center.user中的phone")
@PutMapping(value = "phone")
public Result updateUserPhone(UpdateUserPhoneRequest request){
    userService.updateUserPhone(request);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation(value = "用户首次登陆补全基本信息", notes = "用户首次登陆补全基本信息")
@PutMapping("base_info/{id}")
public Result updateBaseInfo(BaseInfoRequest request,Long userId){
    userService.updateBaseInfo(request, userId);
    return ResultGenerator.genSuccessResult();
}


@ApiIgnore("内部调用，不公开，用来初始化数据")
@PostMapping(value = "init")
public Result initCompanyAdminData(InitCompanyAdminRequest request){
    employeeService.initCompanyAdmin(request);
    return ResultGenerator.genSuccessResult();
}


@PostMapping("login/verification")
@ApiOperation(value = "验证码登陆", notes = "验证码登陆")
public Result loginByVerification(SMSLoginRequest request){
    LoginResultDTO loginResultDTO = userService.loginByVerification(request);
    return ResultGenerator.genSuccessResult(loginResultDTO);
}


@DeleteMapping(value = "destroy")
@ApiIgnore("内部调用，不公开，用来删除数据")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public Result destroy(){
    userService.destroy();
    return ResultGenerator.genSuccessResult();
}


@ApiOperation(value = "账号登陆", notes = "账号登陆")
@PostMapping("login/password")
public Result loginByPassword(LoginRequest loginRequest){
    return ResultGenerator.genSuccessResult(userService.loginByPassword(loginRequest));
}


@ApiOperation(value = "用户注册", notes = "租户用户注册")
@PostMapping(value = "register")
@ApiIgnore("内部调用，不公开，用来初始化数据")
public Result register(RegisterRequest request){
    userService.register(request);
    return ResultGenerator.genSuccessResult();
}


}