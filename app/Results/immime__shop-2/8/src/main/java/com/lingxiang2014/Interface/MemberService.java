package com.lingxiang2014.Interface;
public interface MemberService {

   public long count(MemberRank memberRank,Date beginDate,Date endDate);
   public BigDecimal countBalance(Integer type,Date beginDate,Date endDate);
   public Object findAll(Object Object);
   public void update(Member member,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,Admin operator);
}