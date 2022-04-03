package com.gp.cricket.Interface;
public interface BatmanRecordRepository {

   public List<BatmanRecord> findByUserId(Integer userId);
}