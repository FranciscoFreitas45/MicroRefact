package com.Interface;
public interface ICircleService {

   public List<Circle> queryCircleListCircleId(String circlrIds);
   public int queryCircleMemberByCircleIdAndUserId(String circleId,String userId);
}