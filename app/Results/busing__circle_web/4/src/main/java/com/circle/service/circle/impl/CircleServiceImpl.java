package com.circle.service.circle.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.circle.constant.SystemDict;
import com.circle.dao.circle.ICircleDao;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.circle.CircleMember;
import com.circle.pojo.msg.MessageBean;
import com.circle.pojo.user.User;
import com.circle.service.circle.ICircleService;
import com.circle.utils.FormatUtil;
import com.circle.utils.MapUtil;
import com.circle.utils.msg.MsgQueue;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.ProUtil;
import com.xwtec.xwserver.util.StringUtil;
import com.DTO.MessageBean;
@Service("circleService")
public class CircleServiceImpl implements ICircleService{

@Resource
 private  ICircleDao circleDao;


@Override
public Integer saveCircleInfo(Map<String,Object> paramMap){
    return circleDao.saveCircleInfo(paramMap);
}


@Override
public List<Circle> queryInitMapCircle(){
    List<Circle> circle_list = circleDao.queryInitMapCircle();
    for (Circle circle : circle_list) {
        circle.setImagePath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + circle.getHeadPath());
    }
    return circle_list;
}


@Override
public int queryCircleBuyNum(String circleId){
    return circleDao.queryCircleBuyNum(circleId);
}


@Override
public Circle queryJoinCircleById(String id,int userId){
    Circle circle = circleDao.queryJoinCircleById(id, userId);
    circle.setHeadPath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + circle.getHeadPath());
    return circle;
}


public List<Circle> queryMyCircleList(int userId){
    List<Circle> circleList = circleDao.queryMyCircleList(userId);
    for (Circle circle : circleList) {
        circle.setHeadPath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + circle.getHeadPath());
    }
    return circleList;
}


@Override
public Circle queryCircleAndUserByCircleId(Map<String,Object> paramMap){
    return circleDao.queryCircleAndUserByCircleId(paramMap);
}


public int queryCircleMemberByCircleIdAndUserId(String circleId,String userId){
    return circleDao.queryCircleMemberByCircleIdAndUserId(circleId, userId);
}


@Override
public List<User> queryUsersByCircleId(Map<String,Object> paramMap){
    List<User> list = circleDao.queryUsersByCircleId(paramMap);
    User user = null;
    for (int i = 0; list != null && i < list.size(); i++) {
        user = list.get(i);
        user.setImagePath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + user.getHeadImage());
    }
    return list;
}


public boolean AddUserToCircle(int userId,String circleId,String type){
    return circleDao.AddUserToCircle(userId + "", circleId, type);
}


public int queryCircleMemberAuditByCircleIdAndUserId(String circleId,String userId){
    return circleDao.queryCircleMemberAuditByCircleIdAndUserId(circleId, userId);
}


public boolean AddUserToCircleAudit(String userId,String circleId,String type,String status){
    return circleDao.AddUserToCircleAudit(userId, circleId, type, status);
}


@Override
public int addCicle(Circle circle){
    int res = 0;
    try {
        circleDao.addCicle(circle);
        CircleMember member = new CircleMember();
        member.setCircleId(Integer.parseInt(circle.getId()));
        member.setIdentityType(1002);
        member.setUserId(circle.getCreateUserId());
        int result = circleDao.addCircleMember(member);
        if (result == 0)
            return 0;
        // 发送消息
        MessageBean msg = new MessageBean();
        msg.setTypeId("1");
        msg.setMsgContent("温馨提示：您已成功创建了" + circle.getName() + "农场,我们将尽快为您审核,请耐心等待!");
        msg.setSendUserId("1");
        msg.setReciveUserId(circle.getCreateUser());
        msg.setParams(circle.getId());
        MsgQueue.GROUP_QUEUE.add(msg);
        res = 1;
    } catch (SPTException ex) {
        ex.printStackTrace();
        throw ex;
    }
    return res;
}


@Override
public Integer saveCirclePublishInfo(Circle circle,Circle _circle,User user){
    int res = 0;
    try {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("circleId", circle.getId());
        paramMap.put("type", _circle.getJoinType());
        paramMap.put("intro", _circle.getDescription());
        paramMap.put("issueTime", circle.getIssueTime());
        paramMap.put("issueAddress", circle.getIssueAddress());
        paramMap.put("endTime", _circle.getEndTime());
        paramMap.put("createUserId", _circle.getCreateUserId());
        paramMap.put("createTime", _circle.getCreateTime());
        paramMap.put("endTime", _circle.getEndTime());
        paramMap.put("status", SystemDict.CIRCLE_HISTORY_STATUS_AUDIT);
        paramMap.put("auditMsg", "");
        int result = circleDao.saveCirclePublishInfo(paramMap);
        if (result == 0)
            return 0;
        // 发送消息
        // MessageBean msg = new MessageBean();
        // msg.setTypeId("1");
        // msg.setMsgContent("温馨提示：您已成功发布了" + _circle.getName() + "农场的发货时间和地点,我们将尽快为您审核,请耐心等待!");
        // msg.setSendUserId("1");
        // msg.setReciveUserId(user.getId() + "");
        // msg.setParams(circle.getId());
        // MsgQueue.GROUP_QUEUE.add(msg);
        res = 1;
    } catch (SPTException ex) {
        ex.printStackTrace();
        throw ex;
    }
    return res;
}


public Circle queryMyCircleById(String id,int userId){
    Circle circle = circleDao.queryMyCircleById(id, userId);
    circle.setHeadPath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + circle.getHeadPath());
    return circle;
}


public Circle queryCircleByCircleId(String circleId){
    Circle cirlce = circleDao.queryCircleByCircleId(circleId);
    if (cirlce != null && !StringUtil.isEmpty(cirlce.getWeixinImage())) {
        cirlce.setWeixinImage(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + cirlce.getWeixinImage());
    }
    return cirlce;
}


public List<Circle> queryCircleListCircleId(String circlrIds){
    return circleDao.queryCircleListCircleId(circlrIds);
}


public Integer memberExitCircle(Circle circle,User user){
    int res = 0;
    try {
        // 设置参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("circleId", circle.getId());
        paramMap.put("userId", user.getId());
        int result = circleDao.memberExitCircle(paramMap);
        if (result == 0)
            return 0;
        // 发送消息
        MessageBean msg = new MessageBean();
        msg.setTypeId("1");
        msg.setMsgContent("温馨提示：" + user.getName() + "退出了" + circle.getName() + "农场!");
        msg.setSendUserId(user.getId() + "");
        msg.setReciveUserId(circle.getCreateUserId() + "");
        msg.setParams(circle.getId());
        MsgQueue.GROUP_QUEUE.add(msg);
        res = 1;
    } catch (SPTException ex) {
        ex.printStackTrace();
        throw ex;
    }
    return res;
}


public List<Circle> queryCircleListByPoint(Double minX,Double minY,Double maxX,Double maxY,Double x,Double y){
    List<Circle> circleList = circleDao.queryCircleListByPoint(minX, minY, maxX, maxY);
    for (Circle circle : circleList) {
        double dis = MapUtil.getDistanceFromXtoY(y, x, FormatUtil.parseObject2Double(circle.getLatitude()), FormatUtil.parseObject2Double(circle.getLongitude()));
        circle.setHeadPath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + circle.getHeadPath());
        circle.setDistance(MapUtil.getMapDistance(dis));
    }
    return circleList;
}


public List<Circle> queryCirclesByUserId(Object userId){
    List<Circle> list = circleDao.queryCirclesByUserId(userId);
    Circle circle = null;
    for (int i = 0; list != null && i < list.size(); i++) {
        circle = list.get(i);
        circle.setImagePath(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + circle.getHeadPath());
    }
    return list;
}


}