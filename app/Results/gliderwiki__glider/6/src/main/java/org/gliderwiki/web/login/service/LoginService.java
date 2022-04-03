package org.gliderwiki.web.login.service;
 import javax.servlet.http.HttpServletRequest;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.MemberSessionVo;
public interface LoginService {


public int sendMailAuth(WeUser weUser,HttpServletRequest request)
;

public void updateLastVisitDate(int userIdx)
;

public String findUserIdDWR(String userPassword,String userNick)
;

public BaseObjectBean findUserPassword(String userId,String userNick,HttpServletRequest request)
;

public MemberSessionVo getRowWeUserById(String id)
;

public String getEncryptPassword(String passKey,String passVal)
;

public int updateUserAuth(String code)
;

}