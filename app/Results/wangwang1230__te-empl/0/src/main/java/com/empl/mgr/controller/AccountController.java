package com.empl.mgr.controller;
 import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.service.AccountService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.Interface.AccountService;
@Scope
@Controller
@RequestMapping(value = "account")
public class AccountController extends AbstractController{

@Autowired
 private  AccountService accountService;


@ResponseBody
@RequestMapping(value = "findAccountById")
@SecureValid(code = "04001", desc = "修改昵称", type = MethodType.FIND)
public JSONReturn findAccountById(long id){
    return accountService.findAccountById(id);
}


@ResponseBody
@RequestMapping(value = "modifyNickname")
@SecureValid(code = "04001", desc = "修改昵称", type = MethodType.MODIFY)
public JSONReturn modifyNickname(long id,String nickname,HttpSession httpSession){
    return accountService.modifyNickname(id, nickname, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "addAccountRole")
@SecureValid(code = "04001", desc = "修改角色与帐号之间的关联", type = MethodType.MODIFY)
public JSONReturn addAccountRole(long id,String account,boolean add,HttpSession httpSession){
    return accountService.addAccountRole(id, account, add, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findAccountInfo")
public JSONReturn findAccountInfo(HttpSession httpSession){
    return accountService.findAccountInfo(acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findAccountList")
@SecureValid(code = "04001", desc = "获取账户列表", type = MethodType.FIND)
public JSONReturn findAccountList(int page,String searchValue,HttpSession httpSession){
    return accountService.findAccountList(page, searchValue, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "delAccount")
@SecureValid(code = "04001", desc = "删除账户信息", type = MethodType.DELETE)
public JSONReturn delAccount(long id,HttpSession httpSession){
    return accountService.delAccount(id, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "addAccount")
@SecureValid(code = "04001", desc = "添加账户信息", type = MethodType.ADD)
public JSONReturn addAccount(String user,String nick,String pass,HttpSession httpSession){
    return accountService.addAccount(user, nick, pass, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "initPassword")
@SecureValid(code = "04001", desc = "重置密码", type = MethodType.MODIFY)
public JSONReturn initPassword(long id,HttpSession httpSession){
    return accountService.initPassword(id, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findRole")
@SecureValid(code = "04001", desc = "获取所有角色", type = MethodType.MODIFY)
public JSONReturn findRole(String acctName){
    return accountService.findRole(acctName);
}


@ResponseBody
@RequestMapping(value = "findAccountPage")
@SecureValid(code = "04001", desc = "获取账户列表分页", type = MethodType.FIND)
public JSONReturn findAccountPage(int page,String searchValue){
    return accountService.findAccountPage(page, searchValue);
}


}