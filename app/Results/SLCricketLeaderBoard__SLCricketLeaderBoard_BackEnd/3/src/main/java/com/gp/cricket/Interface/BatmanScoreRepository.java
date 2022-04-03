package com.gp.cricket.Interface;
public interface BatmanScoreRepository {

   public BatmanScore getRecordByPlayerIdMatchType(Integer playerId,String matchType);
   public Object save(Object Object);
}