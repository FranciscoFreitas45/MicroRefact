package org.gliderwiki.web.user.service;
 import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeUserAlarm;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.user.dao.UserAlarmDao;
import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;
import org.gliderwiki.web.vo.UserAlarmVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gliderwiki.Interface.EntityService;
@Service("userAlarmService")
@RemoteProxy(name = "UserAlarmService")
public class UserAlarmServiceImpl implements UserAlarmService{

 private Logger logger;

@Autowired
 private  UserAlarmDao userAlarmDao;

@Autowired
 private  EntityService entityService;


@Override
@RemoteMethod
public String addCheckUserAlarmDWR(String arrValue,int userIdx){
    logger.debug("##arrValue : " + arrValue);
    logger.debug("##userIdx : " + userIdx);
    // 사용자 알람 내역이 있는지 카운트 한다.
    WeUserAlarm weUserAlarm = new WeUserAlarm();
    weUserAlarm.setWe_user_idx(userIdx);
    weUserAlarm.setWe_use_yn("Y");
    String[] metaIdx = arrValue.split(",");
    logger.debug("metaIdx : " + metaIdx);
    int rows = entityService.getCountEntity(weUserAlarm);
    logger.debug("##rows : " + rows);
    // TODOLIST 가져온 알람 인덱스를 저장한다.
    UserAlarmVo userAlarmVo = new UserAlarmVo();
    int result = 0;
    JSONObject jsonObj = null;
    BaseObjectBean resultBean = new BaseObjectBean();
    if (rows > 0) {
        // 건수가 있을 경우 delete, insert 수행
        try {
            result = entityService.deleteEntity(weUserAlarm);
            logger.debug("deleteResult : " + result);
            // 로우와 delete 값이 같아야 한다.
            if (rows == result) {
                for (int index = 0; index < metaIdx.length; index++) {
                    WeUserAlarm insertAlarm = new WeUserAlarm();
                    insertAlarm.setWe_user_idx(userIdx);
                    insertAlarm.setWe_use_yn("Y");
                    insertAlarm.setWe_meta_idx(Integer.parseInt(metaIdx[index]));
                    insertAlarm.setWe_ins_date(DateUtil.getTodayTime());
                    insertAlarm.setWe_ins_user(userIdx + "");
                    result = entityService.insertEntity(insertAlarm);
                }
            }
        } catch (Exception e) {
            logger.error("***WE_USER_ALARM Delete Error : " + e.getMessage());
            resultBean.setRtnMsg(e.getCause().toString());
            resultBean.setRtnResult(0);
            // 서버 에러
            resultBean.setRtnStatus(SystemConst.CALL_FAIL);
            jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
            return jsonObj.toString();
        }
    } else {
        // 없으면 새로 저장
        for (int index = 0; index < metaIdx.length; index++) {
            WeUserAlarm insertAlarm = new WeUserAlarm();
            insertAlarm.setWe_user_idx(userIdx);
            insertAlarm.setWe_use_yn("Y");
            insertAlarm.setWe_meta_idx(Integer.parseInt(metaIdx[index]));
            insertAlarm.setWe_ins_date(DateUtil.getTodayTime());
            insertAlarm.setWe_ins_user(userIdx + "");
            result = entityService.insertEntity(insertAlarm);
        }
    }
    logger.debug("result : " + result);
    if (result == 1) {
        resultBean.setRtnMsg("저장 되었습니다.");
        resultBean.setRtnResult(1);
        // 서버 에러
        resultBean.setRtnStatus(SystemConst.CALL_SUCCESS);
    } else {
        resultBean.setRtnMsg("에러가 발생하였습니다. 다시 시도 하세요");
        resultBean.setRtnResult(0);
        // 서버 에러
        resultBean.setRtnStatus(SystemConst.CALL_FAIL);
    }
    jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
    return jsonObj.toString();
}


@Override
public List<AlarmInfoVo> getUserAlarmList(MemberSessionVo memberSessionVo){
    return userAlarmDao.getUserAlarmList(memberSessionVo);
}


@Override
public List<MetaInfoVo> getUserMetaInfoList(MemberSessionVo memberSessionVo){
    return userAlarmDao.getUserMetaInfoList(memberSessionVo);
}


}