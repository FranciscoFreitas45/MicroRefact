package com.easyshopping.service;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Deposit;
import com.easyshopping.entity.Member;
public interface DepositService extends BaseService<Deposit, Long>{


public Page<Deposit> findPage(Member member,Pageable pageable)
;

}