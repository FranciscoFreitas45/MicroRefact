package com.empl.mgr.service.impl;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.empl.mgr.constant.AccountDeleteState;
import com.empl.mgr.constant.AccountInitPassword;
import com.empl.mgr.constant.AccountState;
import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.constant.SessionKey;
import com.empl.mgr.constant.TimeFormatConstant;
import com.empl.mgr.dao.AccountDao;
import com.empl.mgr.dao.AccountRoleDao;
import com.empl.mgr.dao.RoleDao;
import com.empl.mgr.dto.AccountListDto;
import com.empl.mgr.dto.RoleDto;
import com.empl.mgr.field.TeAccountField;
import com.empl.mgr.field.TeRoleField;
import com.empl.mgr.model.TeAccount;
import com.empl.mgr.model.TeAccountRole;
import com.empl.mgr.model.TeRole;
import com.empl.mgr.service.AccountService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;
import com.empl.mgr.utils.DateTimeUtil;
import com.empl.mgr.utils.EncryptUtil;
import com.empl.mgr.utils.PageUtils;
import com.empl.mgr.Interface.RoleDao;
import com.empl.mgr.Interface.AccountRoleDao;
import com.empl.mgr.DTO.JSONReturn;
import com.empl.mgr.DTO.TeRole;
@Scope
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{

@Autowired
 private  AccountDao accountDao;

@Autowired
 private  RoleDao roleDao;

@Autowired
 private  AccountRoleDao accountRoleDao;


public TeAccount findAccountByName(String userName){
    // TODO Auto-generated method stub
    if (StringUtils.isEmpty(userName))
        return null;
    return accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, userName);
}


@Transactional
public JSONReturn addAccountRole(long id,String account,boolean add,String acctName){
    // TODO Auto-generated method stub
    TeRole teRole = roleDao.findUniqueByProperty(TeRoleField.ROLE_ID, id);
    if (CompareUtil.isEmpty(teRole))
        return JSONReturn.buildFailure("????????????, ??????????????????!");
    if (!add) {
        accountRoleDao.delByAcctNameAndRoleLabel(account, teRole.getRoleLabel());
        return JSONReturn.buildSuccessWithEmptyBody();
    }
    if (CollectionUtils.isNotEmpty(accountRoleDao.findByAcctNameAndRoleLabel(account, teRole.getRoleLabel()))) {
        return JSONReturn.buildFailure("????????????, ????????????!");
    }
    accountRoleDao.save(new TeAccountRole(account, teRole.getRoleLabel()));
    return JSONReturn.buildSuccessWithEmptyBody();
}


public JSONReturn findAccountList(int page,String searchValue,String acctName){
    // TODO Auto-generated method stub
    List<AccountListDto> dtoList = accountDao.findAccountList(page, searchValue);
    if (CollectionUtils.isEmpty(dtoList))
        return JSONReturn.buildFailure("??????????????????!");
    for (AccountListDto dto : dtoList) dto.setTime(DateTimeUtil.conversionTime(dto.getDate(), TimeFormatConstant.YYYY_MM_DD));
    return JSONReturn.buildSuccess(dtoList);
}


public JSONReturn login(String name,String pass,HttpServletRequest request){
    // TODO Auto-generated method stub
    TeAccount account = accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, name);
    if (CompareUtil.isEmpty(account) || account.getAcctDeleteState())
        return JSONReturn.buildFailure("????????????, ???????????????");
    if (!account.getAcctPassword().equals(EncryptUtil.encodeMD5String(pass)))
        return JSONReturn.buildFailure("????????????, ????????????????????????!");
    request.getSession().setAttribute(SessionKey.MODULEACCTNAME, account.getAcctName());
    return JSONReturn.buildSuccess("????????????!");
}


@Transactional
public JSONReturn initPassword(long id,String acctName){
    // TODO Auto-generated method stub
    TeAccount account = accountDao.findUniqueByProperty(TeAccountField.ACCT_ID, id);
    if (CompareUtil.isEmpty(account) || account.getAcctDeleteState())
        return JSONReturn.buildFailure("????????????, ??????????????????!");
    account.setAcctPassword(EncryptUtil.encodeMD5String(AccountInitPassword.ACCOUNT_INIT_PASSWORD));
    return JSONReturn.buildSuccess("????????????, ??????????????????" + AccountInitPassword.ACCOUNT_INIT_PASSWORD + "!");
}


public JSONReturn findRole(String acctName){
    // TODO Auto-generated method stub
    List<TeRole> roleList = roleDao.findAll();
    if (CollectionUtils.isEmpty(roleList))
        return JSONReturn.buildFailure("????????????????????????!");
    List<RoleDto> dtoList = new ArrayList<RoleDto>();
    for (TeRole ro : roleList) {
        RoleDto dto = new RoleDto();
        dto.setId(ro.getRoleId());
        dto.setRoleName(ro.getRoleName());
        dto.setOpt(CollectionUtils.isNotEmpty(accountRoleDao.findByAcctNameAndRoleLabel(acctName, ro.getRoleLabel())));
        dto.setAcctName(acctName);
        dtoList.add(dto);
    }
    return JSONReturn.buildSuccess(dtoList);
}


public JSONReturn exit(HttpSession httpSession){
    // TODO Auto-generated method stub
    String acctName = (String) httpSession.getAttribute(SessionKey.MODULEACCTNAME);
    if (StringUtils.isEmpty(acctName))
        return JSONReturn.buildFailure("????????????!");
    httpSession.removeAttribute(SessionKey.MODULEACCTNAME);
    return JSONReturn.buildSuccessWithEmptyBody();
}


public JSONReturn findAccountById(long id){
    // TODO Auto-generated method stub
    TeAccount acct = accountDao.findUniqueByProperty(TeAccountField.ACCT_ID, id);
    if (CompareUtil.isEmpty(acct) || acct.getAcctDeleteState())
        return JSONReturn.buildFailure("????????????, ??????????????????");
    return JSONReturn.buildSuccess(new AccountListDto(acct.getAcctId(), acct.getAcctName(), acct.getAcctNickname(), acct.getCreateTime(), acct.getCreator(), acct.getAcctSuper()));
}


@Transactional
public JSONReturn modifyNickname(long id,String nickname,String acctName){
    // TODO Auto-generated method stub
    TeAccount account = accountDao.findUniqueByProperty(TeAccountField.ACCT_ID, id);
    if (CompareUtil.isEmpty(account) || account.getAcctDeleteState())
        return JSONReturn.buildFailure("????????????, ??????????????????!");
    account.setAcctNickname(nickname);
    return JSONReturn.buildSuccess("????????????!");
}


public JSONReturn findAccountInfo(String acctName){
    // TODO Auto-generated method stub
    TeAccount account = accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, acctName);
    if (CompareUtil.isEmpty(account) || account.getAcctDeleteState())
        return JSONReturn.buildFailure("????????????????????????");
    account.setAcctPassword("");
    return JSONReturn.buildSuccess(account);
}


@Transactional
public JSONReturn delAccount(long id,String acctName){
    // TODO Auto-generated method stub
    TeAccount account = accountDao.findUniqueByProperty(TeAccountField.ACCT_ID, id);
    if (CompareUtil.isEmpty(account) || account.getAcctDeleteState())
        return JSONReturn.buildFailure("????????????, ??????????????????!");
    if (account.getAcctSuper())
        return JSONReturn.buildFailure("????????????!");
    // account.setAcctDeleteState(AccountDeleteState.IS_DELETE); // ??????????????????????????????
    // ????????????????????????
    accountDao.delete(account);
    return JSONReturn.buildSuccess("????????????!");
}


@Transactional
public JSONReturn mdoifyPass(String password,String acctName){
    // TODO Auto-generated method stub
    TeAccount acct = accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, acctName);
    if (CompareUtil.isEmpty(acct))
        return JSONReturn.buildFailure("??????????????????, ???????????????!");
    acct.setAcctPassword(EncryptUtil.encodeMD5String(password));
    return JSONReturn.buildSuccess("??????????????????!");
}


@Transactional
public JSONReturn addAccount(String user,String nick,String pass,String acctName){
    // TODO Auto-generated method stub
    if (StringUtils.isEmpty(user) || StringUtils.isEmpty(nick) || StringUtils.isEmpty(pass))
        return JSONReturn.buildFailure("????????????!");
    TeAccount account = accountDao.findUniqueByProperty(TeAccountField.ACCT_NAME, user);
    if (CompareUtil.isNotEmpty(account))
        return JSONReturn.buildFailure("????????????, ???????????????!");
    account = new TeAccount();
    account.setAcctName(user);
    account.setAcctNickname(nick);
    account.setAcctPassword(EncryptUtil.encodeMD5String(pass));
    account.setAcctState(AccountState.ACCOUNT_GENERAL);
    account.setAcctSuper(false);
    account.setCreateTime(DateTimeUtil.getCurrentTime());
    account.setCreator(acctName);
    account.setAcctDeleteState(AccountDeleteState.NO_DELETE);
    accountDao.save(account);
    return JSONReturn.buildSuccess("????????????!");
}


public JSONReturn findAccountPage(int page,String searchValue){
    // TODO Auto-generated method stub
    return JSONReturn.buildSuccess(PageUtils.calculatePage(page, accountDao.findAccountPage(searchValue), PageConstant.PAGE_LIST));
}


}