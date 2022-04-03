package com.easyshopping.Interface;
public interface DepositService {

   public Page<Deposit> findPage(Member member,Pageable pageable);
}