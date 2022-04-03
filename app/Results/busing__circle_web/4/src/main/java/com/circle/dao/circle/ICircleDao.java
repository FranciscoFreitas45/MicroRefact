package com.circle.dao.circle;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.circle.CircleMember;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.exception.SPTException;
public interface ICircleDao {


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

public Integer addCircleMember(CircleMember member)
;

public boolean AddUserToCircle(String userId,String circleId,String type)
;

public int queryCircleMemberAuditByCircleIdAndUserId(String circleId,String userId)
;

public boolean AddUserToCircleAudit(String userId,String circleId,String type,String status)
;

public int addCicle(Circle circle)
;

public Integer saveCirclePublishInfo(Map<String,Object> paramMap)
;

public Circle queryMyCircleById(String id,int userId)
;

public Circle queryCircleByCircleId(String circleId)
;

public int updateCircle(Circle circle)
;

public List<Circle> queryCircleListCircleId(String circlrIds)
;

public Integer memberExitCircle(Map<String,Object> paramMap)
;

public List<Circle> queryCircleListByPoint(double minX,double minY,double maxX,double maxY)
;

public int updateCircleHistoryStatus(String id,String status,String auditMsg)
;

public List<Circle> queryCirclesByUserId(Object userId)
;

}