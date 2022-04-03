package com.gp.cricket.Interface;
public interface BallerRecordRepository {

   public BallerRecord findByPlayerIdANDMatchId(Integer playerId,Match matchId);
   public List<BallerRecord> findByPlayerIdANDMatchType(Integer playerId,Integer matchType);
}