package com.gp.cricket.Interface;
public interface FieldingScoreService {

   public FieldingScore getRecordByPlayerIdMatchType(Integer playerId,String matchTypeString);
   public FieldingScore saveFieldingRecord(FieldingScore record);
}