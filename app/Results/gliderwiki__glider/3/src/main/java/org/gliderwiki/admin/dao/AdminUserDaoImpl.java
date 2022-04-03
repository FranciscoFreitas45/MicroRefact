package org.gliderwiki.admin.dao;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MailSendUserVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("adminUserDao")
public class AdminUserDaoImpl extends SqlSessionDaoSupportimplements AdminUserDao{


@Override
public int insertProfile(Integer weUserIdx){
    return getSqlSession().update("MemberManage.insertProfile", weUserIdx);
}


@Override
public int insertUser(WeUser weUser){
    return getSqlSession().update("MemberManage.insertUser", weUser);
}


@Override
public List<MailSendUserVo> getUserListMailInfo(WeUser weUser){
    List<MailSendUserVo> mailSendUserVo = null;
    mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserListMailInfo", weUser);
    return mailSendUserVo;
}


@Override
public List<MailSendUserVo> getUserListByGrade(WeProfile domain){
    List<MailSendUserVo> mailSendUserVo = null;
    mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserListByGrade", domain);
    return mailSendUserVo;
}


@Override
public List<MailSendUserVo> getUserAwayList(WeUser weUser,String awayYn){
    Map<String, String> mapper = new HashMap<String, String>();
    mapper.put("weAwayYn", awayYn);
    mapper.put("weAuthYn", weUser.getWe_user_auth_yn());
    List<MailSendUserVo> mailSendUserVo = null;
    mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserAwayList", mapper);
    return mailSendUserVo;
}


@Override
public int getUserMaxIdx(){
    return (Integer) getSqlSession().selectOne("MemberManage.getUserMaxIdx");
}


@Override
public List<MailSendUserVo> getUserListByGroup(GroupUserVo groupUser){
    List<MailSendUserVo> mailSendUserVo = null;
    mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserListByGroup", groupUser);
    return mailSendUserVo;
}


}