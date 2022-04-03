package org.gliderwiki.web.login.dao;
 import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
@Repository("loginDao")
public class LoginDaoImpl extends SqlSessionDaoSupportimplements LoginDao{

 private Logger logger;


@Override
public int getCurrentMailIdx(){
    return (Integer) getSqlSession().selectOne("MemberManage.getCurrentMailIdx");
}


@Override
public MemberSessionVo getRowWeUserById(WeUser weUser){
    MemberSessionVo sessionVo = null;
    sessionVo = (MemberSessionVo) getSqlSession().selectOne("MemberManage.getRowWeUserById", weUser);
    return sessionVo;
}


@Override
public int updateUserAuth(WeUser weUser){
    return getSqlSession().update("MemberManage.updateUserAuth", weUser);
}


}