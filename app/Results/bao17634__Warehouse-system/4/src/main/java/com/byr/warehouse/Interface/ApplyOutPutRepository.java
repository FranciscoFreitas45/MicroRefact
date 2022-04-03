package com.byr.warehouse.Interface;
public interface ApplyOutPutRepository {

   public List<ApplyOutPut> findAllByOutCode(String outCode);
}