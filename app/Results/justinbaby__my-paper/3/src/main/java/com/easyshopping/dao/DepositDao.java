package com.easyshopping.dao;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Deposit;
import com.easyshopping.entity.Member;
public interface DepositDao extends BaseDao<Deposit, Long>{


public Page<Deposit> findPage(Member member,Pageable pageable)
;

}