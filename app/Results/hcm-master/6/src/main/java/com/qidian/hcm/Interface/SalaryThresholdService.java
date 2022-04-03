package com.qidian.hcm.Interface;
public interface SalaryThresholdService {

   public List<SalaryThresholdResponse> getSalaryThreshold();
   public void updateThreshold(Long id,SalaryThresholdRequest salaryThresholdRequest);
}