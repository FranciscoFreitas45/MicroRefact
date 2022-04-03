package com.gp.cricket.Interface;
public interface BatmanRecordRepository {

   public List<BatmanRecord> findByPlayerIdANDMatchType(Integer playerId,Integer matchType);
}