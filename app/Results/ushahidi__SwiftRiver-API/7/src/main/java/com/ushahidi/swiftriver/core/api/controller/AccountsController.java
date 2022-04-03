package com.ushahidi.swiftriver.core.api.controller;
 import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ushahidi.swiftriver.core.api.dto.ActivateAccountDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateAccountDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateClientDTO;
import com.ushahidi.swiftriver.core.api.dto.FollowerDTO;
import com.ushahidi.swiftriver.core.api.dto.GetAccountDTO;
import com.ushahidi.swiftriver.core.api.dto.GetActivityDTO;
import com.ushahidi.swiftriver.core.api.dto.GetClientDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyAccountDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyClientDTO;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.exception.NotFoundException;
import com.ushahidi.swiftriver.core.api.service.AccountService;
import com.ushahidi.swiftriver.core.model.Account;
@Controller
@RequestMapping("/v1/accounts")
public class AccountsController extends AbstractController{

 final  Logger logger;

@Autowired
 private  AccountService accountService;


@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
@ResponseBody
public void resetPassword(Map<String,String> body){
    // Grab the request parameters
    String resetToken = body.get("token");
    String email = body.get("email");
    String password = body.get("password");
    // Validate parameters
    List<ErrorField> validationErrors = new ArrayList<ErrorField>();
    if (email == null || email.trim().length() == 0) {
        validationErrors.add(new ErrorField("email", "missing"));
    }
    if (password == null || password.trim().length() == 0) {
        validationErrors.add(new ErrorField("password", "missing"));
    }
    if (resetToken == null || resetToken.trim().length() == 0) {
        validationErrors.add(new ErrorField("token", "missing"));
    }
    // Do we have validation errors?
    if (!validationErrors.isEmpty()) {
        BadRequestException exception = new BadRequestException();
        exception.setErrors(validationErrors);
        throw exception;
    }
    accountService.resetPassword(resetToken, email, password);
}


@RequestMapping(value = "/timeline", method = RequestMethod.GET)
@ResponseBody
public List<GetActivityDTO> getTimeline(Principal principal,Integer count,Long lastId,Boolean newer){
    return accountService.getTimeline(count, lastId, newer, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
public GetAccountDTO getAccountById(Long id,Principal principal) throws NotFoundException{
    return accountService.getAccountById(id, principal.getName());
}


@RequestMapping(method = RequestMethod.GET, params = "q")
@ResponseBody
public List<GetAccountDTO> searchAccounts(String query,Principal principal) throws NotFoundException{
    return accountService.searchAccounts(query, principal.getName());
}


@RequestMapping(value = "/{accountId}/apps/{appId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteApp(Long accountId,Long appId,Principal principal){
    accountService.deleteApp(accountId, appId, principal.getName());
}


@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public GetAccountDTO createAccount(CreateAccountDTO body){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    if (body.getName() == null) {
        errors.add(new ErrorField("name", "missing"));
    }
    if (body.getAccountPath() == null) {
        errors.add(new ErrorField("account_path", "missing"));
    }
    if (body.getEmail() == null) {
        errors.add(new ErrorField("email", "missing"));
    }
    if (body.getPassword() == null) {
        errors.add(new ErrorField("password", "missing"));
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return accountService.createAccount(body);
}


@RequestMapping(value = "/{id}/followers", method = RequestMethod.GET)
@ResponseBody
public List<FollowerDTO> getFollowers(Long id,Long accountId){
    return accountService.getFollowers(id, accountId);
}


@RequestMapping(value = "/{accountId}/apps", method = RequestMethod.POST)
@ResponseBody
public GetClientDTO createApp(CreateClientDTO body,Long accountId,Principal principal){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    if (body.getName() == null) {
        errors.add(new ErrorField("name", "missing"));
    }
    if (body.getRedirectUri() == null) {
        errors.add(new ErrorField("redirect_uri", "missing"));
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return accountService.createClient(accountId, body, principal.getName());
}


@RequestMapping(value = "/{id}/following", method = RequestMethod.GET)
@ResponseBody
public Account getFollowing(Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(method = RequestMethod.GET, params = "email")
@ResponseBody
public GetAccountDTO getAccountByEmail(String email,Principal principal) throws NotFoundException{
    return accountService.getAccountByEmail(email, principal.getName());
}


@RequestMapping(value = "/{accountId}/apps/{appId}", method = RequestMethod.PUT)
@ResponseBody
public GetClientDTO modifyApp(ModifyClientDTO body,Long accountId,Long appId,Principal principal){
    return accountService.modifyClient(accountId, appId, body, principal.getName());
}


@RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
@ResponseBody
public void forgotPassword(Map<String,String> body){
    List<ErrorField> validationErrors = new ArrayList<ErrorField>();
    String email = body.get("email");
    if (email == null || email.trim().length() == 0) {
        validationErrors.add(new ErrorField("email", "missing"));
    }
    if (!validationErrors.isEmpty()) {
        BadRequestException exception = new BadRequestException();
        exception.setErrors(validationErrors);
        throw exception;
    }
    accountService.forgotPassword(email);
}


@RequestMapping(method = RequestMethod.GET, params = "account_path")
@ResponseBody
public GetAccountDTO getAccountByName(String accountPath,Principal principal) throws NotFoundException{
    return accountService.getAccountByAccountPath(accountPath, principal.getName());
}


@RequestMapping(value = "/{id}/followers/{accountId}", method = RequestMethod.PUT)
@ResponseBody
public void addFollower(Long id,Long accountId){
    accountService.addFollower(id, accountId);
}


@RequestMapping(value = "/{accountId}/apps", method = RequestMethod.GET)
@ResponseBody
public List<GetClientDTO> getApps(Long accountId,Principal principal){
    return accountService.getClients(accountId, principal.getName());
}


@RequestMapping(value = "{id}/followers/{accountId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteFollower(Long id,Long accountId){
    accountService.deleteFollower(id, accountId);
}


@RequestMapping(value = "/me", method = RequestMethod.GET)
@ResponseBody
public GetAccountDTO getAccount(Principal principal) throws NotFoundException{
    String username = principal.getName();
    return accountService.getAccountByUsername(username);
}


@RequestMapping(value = "/{accountId}", method = RequestMethod.PUT)
@ResponseBody
public GetAccountDTO modifyAccount(ModifyAccountDTO body,Long accountId,Principal principal){
    return accountService.modifyAccount(accountId, body, principal.getName());
}


@RequestMapping(value = "/{accountId}/activities", method = RequestMethod.GET)
@ResponseBody
public List<GetActivityDTO> getActivities(Long accountId,Principal principal){
    return accountService.getActivities(accountId, principal.getName());
}


@RequestMapping(value = "/activate", method = RequestMethod.POST)
@ResponseBody
public void activateAccount(ActivateAccountDTO body){
    accountService.activateAccount(body);
}


}