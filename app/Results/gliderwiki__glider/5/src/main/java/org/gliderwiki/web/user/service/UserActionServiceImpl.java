package org.gliderwiki.web.user.service;
 import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.JoinStatus;
import org.gliderwiki.web.domain.JoinType;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.user.dao.UserActionDao;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gliderwiki.Interface.EntityService;
@Service("userActionService")
@RemoteProxy(name = "UserActionService")
public class UserActionServiceImpl implements UserActionService{

 private Logger logger;

@Autowired
 private  UserActionDao userActionDao;

@Autowired
 private  EntityService entityService;


@Override
public List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain,int type){
    return userActionDao.getUserSpaceJoinList(joinDomain, type);
}


@Override
@RemoteMethod
public String cancelJoinToSpaceDWR(Integer we_user_idx,Integer we_space_join_idx,Integer we_space_idx,String joinStatus){
    WeSpaceJoin domain = new WeSpaceJoin();
    domain.setWe_space_join_idx(we_space_join_idx);
    domain.setWe_user_idx(we_user_idx);
    if (joinStatus.equals("CANCEL")) {
        domain.setWe_join_status(JoinStatus.CANCEL);
        domain.setWe_join_type(JoinType.REQUEST);
    } else if (joinStatus.equals("APPROVE")) {
        domain.setWe_join_status(JoinStatus.APPROVE);
        domain.setWe_join_type(JoinType.INVITE);
    } else if (joinStatus.equals("REJECT")) {
        domain.setWe_join_status(JoinStatus.REJECT);
        domain.setWe_join_type(JoinType.INVITE);
    }
    domain.setWe_ins_date(DateUtil.getTodayTime());
    domain.setWe_space_idx(we_space_idx);
    logger.debug("###Join domain : " + domain.toString());
    int result = entityService.updateEntity(domain);
    JSONObject jsonObj = null;
    BaseObjectBean resultBean = new BaseObjectBean();
    logger.debug("result : " + result);
    if (result == 1) {
        resultBean.setRtnMsg("?????? ???????????????.");
        resultBean.setRtnResult(we_space_idx);
        // ?????? ??????
        resultBean.setRtnStatus(joinStatus);
    } else {
        resultBean.setRtnMsg("????????? ?????????????????????. ?????? ?????? ?????????");
        resultBean.setRtnResult(0);
        // ?????? ??????
        resultBean.setRtnStatus(joinStatus);
    }
    jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
    return jsonObj.toString();
}


@Override
public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList){
    return userActionDao.getSpaceInfoByIdx(wikiSpaceIdxList);
}


@Override
public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo){
    return userActionDao.getMyWikiLogAction(memberSessionVo);
}


}