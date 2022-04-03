package com.circle.dao.msg.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.dao.msg.IMsgDao;
import com.circle.pojo.msg.MessageBean;
import com.circle.pojo.msg.MessageTypeBean;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.Interface.ICommonDao;
import com.DTO.Page;
@Repository
public class MsgDaoImpl implements IMsgDao{

 private  Logger logger;

 public  String ADD_MSG_INFO;

 public  String QUERY_MESSAGETYP_SQL;

 public  String UPDATE_MSG_STATUS;

 public  String QUERY_USER_MESSAGE_LIST;

 public  String QUERY_MESSATE_INFO;

 public  String QUERY_MSG_CNT;

 public  String QUERY_CIRCLE_MSG_BY_USERID_AND_RECIVEID;

@Resource
 private ICommonDao commonDao;


public int updateMsgStatus(Map<String,?> paramMap){
    return commonDao.update(UPDATE_MSG_STATUS, paramMap);
}


@Override
public List<Map<String,Object>> getUserMessage(Page page,int id){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", id);
    paramsMap.put("status", page.getSearchParam().get("status"));
    return commonDao.queryForList(QUERY_USER_MESSAGE_LIST, paramsMap, page);
}


public int queryCircleMsgCount(String sendUserId,String reciveUserId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("sid", sendUserId);
    paramsMap.put("rid", reciveUserId);
    return commonDao.queryForInt(QUERY_CIRCLE_MSG_BY_USERID_AND_RECIVEID, paramsMap);
}


public MessageBean getMessageInfoById(String id){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("id", id);
    return commonDao.queryForObject(QUERY_MESSATE_INFO, paramsMap, MessageBean.class);
}


public int queryMsgCount(String userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    return commonDao.queryForInt(QUERY_MSG_CNT, paramsMap);
}


public void batchAddMsgInfo(List<Map<String,?>> paramListMap){
    logger.debug("[UserDaoImpl.queryUserList] start...");
    commonDao.batchUpdate(ADD_MSG_INFO, paramListMap);
    logger.debug("[UserDaoImpl.queryUserList] end...");
}


@Override
public List<MessageTypeBean> queryMessageTypeBeans(){
    return commonDao.queryForList(QUERY_MESSAGETYP_SQL, MessageTypeBean.class);
}


}