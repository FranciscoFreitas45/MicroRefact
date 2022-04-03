package org.gliderwiki.web.login.dao;
 import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MemberSessionVo;
public interface LoginDao {


public int getCurrentMailIdx()
;

public MemberSessionVo getRowWeUserById(WeUser weUser)
;

public int updateUserAuth(WeUser weUser)
;

}