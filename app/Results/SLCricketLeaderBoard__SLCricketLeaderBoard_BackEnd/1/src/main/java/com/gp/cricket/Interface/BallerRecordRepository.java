package com.gp.cricket.Interface;
public interface BallerRecordRepository {

   public List<BallerRecord> findByUserId(Integer userId);
}