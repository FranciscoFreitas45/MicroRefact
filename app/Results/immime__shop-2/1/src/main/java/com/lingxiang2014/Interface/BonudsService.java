package com.lingxiang2014.Interface;
public interface BonudsService {

   public BigDecimal count(Member member,Type type,Date beginDate,Date endDate);
   public List<Bonuds> findList(Member member,Type type,Date beginDate,Date endDate);
   public List<Bonuds> findToday(Type type,Member member);
   public Object save(Object Object);
}