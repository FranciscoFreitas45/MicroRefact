package com.Interface;
public interface ICircleService {

   public List<Circle> queryCirclesByUserId(Object userId);
   public int addCicle(Circle circle);
   public Circle queryCircleByCircleId(String circleId);
   public Integer memberExitCircle(Circle cirlce,User user);
   public Circle queryCircleAndUserByCircleId(Map<String,Object> paramMap);
   public List<User> queryUsersByCircleId(Map<String,Object> paramMap);
   public Integer saveCircleInfo(Map<String,Object> paramMap);
   public Integer saveCirclePublishInfo(Circle circle,Circle _circle,User user);
}