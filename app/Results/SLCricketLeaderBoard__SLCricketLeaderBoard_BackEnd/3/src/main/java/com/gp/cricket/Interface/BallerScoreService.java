package com.gp.cricket.Interface;
public interface BallerScoreService {

   public BallerScore getRecordByPlayerIdMatchType(Integer playerId,String matchTypeString);
   public BallerScore saveRecord(BallerScore ballerScoreRecord);
}