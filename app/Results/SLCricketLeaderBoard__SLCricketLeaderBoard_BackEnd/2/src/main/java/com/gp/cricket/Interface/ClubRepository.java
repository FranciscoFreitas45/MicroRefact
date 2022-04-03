package com.gp.cricket.Interface;
public interface ClubRepository {

   public Object existsById(Object Object);
   public Club findClubByClubId(Integer clubId);
}