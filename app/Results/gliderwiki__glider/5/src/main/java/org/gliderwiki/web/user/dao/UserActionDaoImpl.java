package org.gliderwiki.web.user.dao;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
@Repository("userActionDao")
public class UserActionDaoImpl extends SqlSessionDaoSupportimplements UserActionDao{

 private Logger logger;


@Override
public List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain,int type){
    List<WeSpaceJoin> joinList = null;
    // 조회 기간은 1달로 제한
    if (type == 1) {
        String month = DateUtil.getOffsetDate(SystemConst.SEACH_MONTH);
        joinDomain.setWe_ins_date(month);
    } else {
        joinDomain.setWe_ins_date(null);
    }
    joinList = (List<WeSpaceJoin>) getSqlSession().selectList("MemberManage.getUserSpaceJoinList", joinDomain);
    return joinList;
}


@Override
public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList){
    List<WikiLogVo> wikiLogList = null;
    wikiLogList = (List<WikiLogVo>) getSqlSession().selectList("MemberManage.getSpaceInfoByIdx", wikiSpaceIdxList);
    return wikiLogList;
}


@Override
public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo){
    List<WikiLogVo> wikiLogList = null;
    wikiLogList = (List<WikiLogVo>) getSqlSession().selectList("MemberManage.getMyWikiLogAction", memberSessionVo);
    return wikiLogList;
}


}