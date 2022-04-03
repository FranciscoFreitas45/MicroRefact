package sn.controller;
 import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import sn.api.requests.NotificationSettingRequest;
import sn.api.response.ResponseDataMessage;
import sn.api.response.ServiceResponse;
import sn.model.NotificationSettings;
import sn.model.Person;
import sn.model.dto.account.UserRegistrationRequest;
import sn.service.AccountService;
import sn.service.NotificationService;
import sn.Interface.NotificationService;
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

@Autowired
 private  AccountService accountService;

@Autowired
 private  NotificationService notificationService;


@PutMapping("/password/set")
public ResponseEntity<ServiceResponse<ResponseDataMessage>> setPassword(String password){
    if (!Strings.isNotEmpty(password)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("New password null or empty")));
    }
    return accountService.setNewPassword(password) ? ResponseEntity.status(HttpStatus.OK).body(new ServiceResponse<>(new ResponseDataMessage("Person password successfully changed"))) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Service unavailable")));
}


@PutMapping("/email")
public ResponseEntity<ServiceResponse<ResponseDataMessage>> setEmail(String email){
    if (!Strings.isNotEmpty(email)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Email is null or empty")));
    }
    return accountService.changeEmail(email) ? ResponseEntity.status(HttpStatus.OK).body(new ServiceResponse<>(new ResponseDataMessage("Person email successfully changed"))) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Service unavailable")));
}


@PutMapping("/notifications")
public ServiceResponse<ResponseDataMessage> putNotifications(NotificationSettingRequest request){
    Person person = accountService.findCurrentUser();
    return notificationService.saveNotificationSettings(person, request);
}


@PutMapping("/password/recovery")
public ResponseEntity<ServiceResponse<ResponseDataMessage>> recoveryPassword(String email){
    if (!Strings.isNotEmpty(email)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Email is null or empty")));
    }
    return accountService.recoveryPassword(email) ? ResponseEntity.status(HttpStatus.OK).body(new ServiceResponse<>(new ResponseDataMessage("Recovery information was sent to e-mail"))) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Service unavailable")));
}


@PostMapping("/register")
public ResponseEntity<ServiceResponse<ResponseDataMessage>> register(UserRegistrationRequest userRegistrationRequest){
    return accountService.register(userRegistrationRequest) ? ResponseEntity.status(HttpStatus.OK).body(new ServiceResponse<>(new ResponseDataMessage("Registration successful"))) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("Bad request", new ResponseDataMessage("Service unavailable")));
}


}