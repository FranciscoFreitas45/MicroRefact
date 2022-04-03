package org.gliderwiki.admin.dao;
 import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MailSendUserVo;
public interface AdminUserDao {


public int insertProfile(Integer weUserIdx)
;

public int insertUser(WeUser weUser)
;

public List<MailSendUserVo> getUserListMailInfo(WeUser weUser)
;

public List<MailSendUserVo> getUserListByGrade(WeProfile domain)
;

public List<MailSendUserVo> getUserAwayList(WeUser weUser,String awayYn)
;

public int getUserMaxIdx()
;

public List<MailSendUserVo> getUserListByGroup(GroupUserVo groupUser)
;

}