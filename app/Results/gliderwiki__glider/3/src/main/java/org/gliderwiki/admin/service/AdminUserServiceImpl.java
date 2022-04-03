package org.gliderwiki.admin.service;
 import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.AdminUserDao;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MailSendUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.DTO.WeProfile;
@Service("adminUserService")
@RemoteProxy(name = "AdminUserService")
public class AdminUserServiceImpl implements AdminUserService{

 private Logger logger;

@Inject
 private  PlatformTransactionManager tx;

@Autowired
 private  EntityService entityService;

@Autowired
 private  AdminUserDao adminUserDao;


@Override
public int insertProfile(Integer weUserIdx){
    return adminUserDao.insertProfile(weUserIdx);
}


@Override
@SuppressWarnings("rawtypes")
public int insertUser(Map<Integer,Map> maps){
    int result = 0;
    int maxUserIdx = 0;
    TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
    try {
        // 기본테이블 리셋
        logger.debug("UserProfileService :::: insertUser(Map <Integer, Map> map)");
        logger.debug("UserProfileService :::: insertUser ::: map " + maps.size());
        // 가장 큰 번호를 알아 온다.
        maxUserIdx = getUserMaxIdx();
        logger.debug("%%%%%%% maxUserIdx : " + maxUserIdx);
        // 회원 등록일괄처리를 한다.
        for (int i = 1; i < maps.size(); i++) {
            Map<Integer, String> map = maps.get(i);
            logger.debug("###### map : " + map.get(0) + " : " + map.get(1) + " : " + map.get(2) + " : " + map.get(3));
            WeUser weUser = new WeUser();
            weUser.setWe_user_id(map.get(1));
            weUser.setWe_user_name(map.get(2));
            weUser.setWe_user_nick(map.get(3));
            result += adminUserDao.insertUser(weUser);
        }
        // 가장 큰 번호 이후로 등록된 회원의 프로필을 등록한다.
        insertProfile(maxUserIdx);
        tx.commit(status);
    } catch (Exception e) {
        result = -1;
        logger.error("***회원 일괄 처리중  Error " + e.getCause());
        tx.rollback(status);
        throw new DBHandleException("회원일괄 처리 중 에러가 발생하였습니다. 관리자에게 문의하세요.", null, result);
    }
    return result;
}


@Override
public List<MailSendUserVo> getUserListMailInfo(WeUser weUser){
    return adminUserDao.getUserListMailInfo(weUser);
}


@Override
@RemoteMethod
public List<MailSendUserVo> getUserListByGrade(Integer weGrade){
    WeProfile domain = new WeProfile();
    domain.setWe_grade(weGrade);
    domain.setWe_away_yn("N");
    return adminUserDao.getUserListByGrade(domain);
}


@Override
public List<MailSendUserVo> getUserAwayList(WeUser weUser,String awayYn){
    return adminUserDao.getUserAwayList(weUser, awayYn);
}


@Override
public int getUserMaxIdx(){
    return adminUserDao.getUserMaxIdx();
}


@Override
@RemoteMethod
public List<MailSendUserVo> getUserListByGroup(Integer weGroupIdx){
    GroupUserVo groupUser = new GroupUserVo();
    groupUser.setWeGroupIdx(weGroupIdx);
    return adminUserDao.getUserListByGroup(groupUser);
}


}