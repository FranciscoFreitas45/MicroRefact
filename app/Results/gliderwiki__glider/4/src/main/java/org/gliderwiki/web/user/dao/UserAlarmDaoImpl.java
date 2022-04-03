package org.gliderwiki.web.user.dao;
 import java.util.List;
import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
@Repository("userAlarmDao")
public class UserAlarmDaoImpl extends SqlSessionDaoSupportimplements UserAlarmDao{

 private Logger logger;


@Override
public List<AlarmInfoVo> getUserAlarmList(MemberSessionVo memberSessionVo){
    List<AlarmInfoVo> metaList = (List<AlarmInfoVo>) getSqlSession().selectList("MemberManage.getUserAlarmList", memberSessionVo);
    return metaList;
}


@Override
public List<MetaInfoVo> getUserMetaInfoList(MemberSessionVo memberSessionVo){
    List<MetaInfoVo> metaList = (List<MetaInfoVo>) getSqlSession().selectList("MemberManage.getUserMetaInfoList", memberSessionVo);
    return metaList;
}


}