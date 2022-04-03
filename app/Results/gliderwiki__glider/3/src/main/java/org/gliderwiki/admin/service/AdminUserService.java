package org.gliderwiki.admin.service;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MailSendUserVo;
public interface AdminUserService {


public int insertProfile(Integer weUserIdx)
;

@SuppressWarnings("rawtypes")
public int insertUser(Map<Integer,Map> map)
;

public List<MailSendUserVo> getUserListMailInfo(WeUser weUser)
;

public List<MailSendUserVo> getUserListByGrade(Integer weGrade)
;

public List<MailSendUserVo> getUserAwayList(WeUser weUser,String awayYn)
;

public int getUserMaxIdx()
;

public List<MailSendUserVo> getUserListByGroup(Integer weGroupIdx)
;

}