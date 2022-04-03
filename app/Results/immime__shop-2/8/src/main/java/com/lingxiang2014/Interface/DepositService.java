package com.lingxiang2014.Interface;
public interface DepositService {

   public BigDecimal countBalance(Type type,Date beginDate,Date endDate);
   public BigDecimal count(Member member,Type type,Date beginDate,Date endDate);
}