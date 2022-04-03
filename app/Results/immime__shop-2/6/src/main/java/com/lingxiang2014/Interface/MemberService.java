package com.lingxiang2014.Interface;
public interface MemberService {

   public Member find(Member parent,Integer index,MemberRank membeRank);
   public void update(Member member,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,Admin operator);
}