package com.qidian.hcm.Interface;
public interface SalaryItemRepository {

   public Optional<SalaryItem> findByCode(String code);
}