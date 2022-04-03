package org.gliderwiki.web.user.dao;
 import java.util.Map;
import org.gliderwiki.web.domain.WeUser;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("userProfileDao")
public class UserProfileDaoImpl extends SqlSessionDaoSupportimplements UserProfileDao{


@Override
public int updateAwayProfile(Integer weUserIdx){
    return getSqlSession().update("MemberManage.updateAwayProfile", weUserIdx);
}


@Override
public int updateAwayUser(Integer weUserIdx){
    return getSqlSession().update("MemberManage.updateAwayUser", weUserIdx);
}


}