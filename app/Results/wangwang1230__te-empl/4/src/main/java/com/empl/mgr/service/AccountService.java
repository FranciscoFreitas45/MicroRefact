package com.empl.mgr.service;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.empl.mgr.model.TeAccount;
import com.empl.mgr.support.JSONReturn;
public interface AccountService {


public TeAccount findAccountByName(String userName)
;

public JSONReturn addAccountRole(long id,String account,boolean add,String acctName)
;

public JSONReturn findAccountList(int page,String searchValue,String acctName)
;

public JSONReturn login(String name,String pass,HttpServletRequest request)
;

public JSONReturn initPassword(long id,String acctName)
;

public JSONReturn findRole(String acctName)
;

public JSONReturn exit(HttpSession httpSession)
;

public JSONReturn findAccountById(long id)
;

public JSONReturn modifyNickname(long id,String nickname,String acctName)
;

public JSONReturn findAccountInfo(String acctName)
;

public JSONReturn delAccount(long id,String acctName)
;

public JSONReturn mdoifyPass(String password,String acctName)
;

public JSONReturn addAccount(String user,String nick,String pass,String acctName)
;

public JSONReturn findAccountPage(int page,String searchValue)
;

}