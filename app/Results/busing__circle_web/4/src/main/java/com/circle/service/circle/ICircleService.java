package com.circle.service.circle;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.exception.SPTException;
public interface ICircleService {


public Integer saveCircleInfo(Map<String,Object> paramMap)
;

public List<Circle> queryInitMapCircle()
;

public int queryCircleBuyNum(String circleId)
;

public Circle queryJoinCircleById(String id,int userId)
;

public List<Circle> queryMyCircleList(int userId)
;

public Circle queryCircleAndUserByCircleId(Map<String,Object> paramMap)
;

public int queryCircleMemberByCircleIdAndUserId(String circleId,String userId)
;

public List<User> queryUsersByCircleId(Map<String,Object> paramMap)
;

public boolean AddUserToCircle(int userId,String circleId,String type)
;

public int queryCircleMemberAuditByCircleIdAndUserId(String circleId,String userId)
;

public boolean AddUserToCircleAudit(String userId,String circleId,String type,String status)
;

public int addCicle(Circle circle)
;

public Integer saveCirclePublishInfo(Circle circle,Circle _circle,User user)
;

public Circle queryMyCircleById(String id,int userId)
;

public Circle queryCircleByCircleId(String circleId)
;

public List<Circle> queryCircleListCircleId(String circlrIds)
;

public Integer memberExitCircle(Circle cirlce,User user)
;

public List<Circle> queryCircleListByPoint(Double minX,Double minY,Double maxX,Double maxY,Double x,Double y)
;

public List<Circle> queryCirclesByUserId(Object userId)
;

}