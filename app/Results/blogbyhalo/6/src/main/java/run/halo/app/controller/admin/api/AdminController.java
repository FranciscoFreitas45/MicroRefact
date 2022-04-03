package run.halo.app.controller.admin.api;
 import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.annotation.DisableOnCondition;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.model.dto.EnvironmentDTO;
import run.halo.app.model.dto.LoginPreCheckDTO;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.MFAType;
import run.halo.app.model.params.LoginParam;
import run.halo.app.model.params.ResetPasswordParam;
import run.halo.app.model.properties.PrimaryProperties;
import run.halo.app.model.support.BaseResponse;
import run.halo.app.security.token.AuthToken;
import run.halo.app.service.AdminService;
import run.halo.app.service.OptionService;
import run.halo.app.Interface.OptionService;
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

 private  AdminService adminService;

 private  OptionService optionService;

public AdminController(AdminService adminService, OptionService optionService) {
    this.adminService = adminService;
    this.optionService = optionService;
}
@PutMapping("password/reset")
@ApiOperation("Resets password by verify code")
@CacheLock(autoDelete = false)
@DisableOnCondition
public void resetPassword(ResetPasswordParam param){
    adminService.resetPasswordByCode(param);
}


@GetMapping("environments")
@ApiOperation("Gets environments info")
public EnvironmentDTO getEnvironments(){
    return adminService.getEnvironments();
}


@PostMapping("logout")
@ApiOperation("Logs out (Clear session)")
@CacheLock(autoDelete = false)
public void logout(){
    adminService.clearToken();
}


@GetMapping(value = "halo/logfile")
@ApiOperation("Gets halo log file content")
@DisableOnCondition
public BaseResponse<String> getLogFiles(Long lines){
    return BaseResponse.ok(HttpStatus.OK.getReasonPhrase(), adminService.getLogFiles(lines));
}


@PostMapping("login/precheck")
@ApiOperation("Login")
@CacheLock(autoDelete = false, prefix = "login_precheck")
public LoginPreCheckDTO authPreCheck(LoginParam loginParam){
    final User user = adminService.authenticate(loginParam);
    return new LoginPreCheckDTO(MFAType.useMFA(user.getMfaType()));
}


@PostMapping("login")
@ApiOperation("Login")
@CacheLock(autoDelete = false, prefix = "login_auth")
public AuthToken auth(LoginParam loginParam){
    return adminService.authCodeCheck(loginParam);
}


@PostMapping("password/code")
@ApiOperation("Sends reset password verify code")
@CacheLock(autoDelete = false)
@DisableOnCondition
public void sendResetCode(ResetPasswordParam param){
    adminService.sendResetPasswordCode(param);
}


@PostMapping("refresh/{refreshToken}")
@ApiOperation("Refreshes token")
@CacheLock(autoDelete = false)
public AuthToken refresh(String refreshToken){
    return adminService.refreshToken(refreshToken);
}


@GetMapping(value = "/is_installed")
@ApiOperation("Checks Installation status")
public boolean isInstall(){
    return optionService.getByPropertyOrDefault(PrimaryProperties.IS_INSTALLED, Boolean.class, false);
}


}