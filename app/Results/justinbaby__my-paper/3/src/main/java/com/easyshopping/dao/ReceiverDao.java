package com.easyshopping.dao;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Receiver;
public interface ReceiverDao extends BaseDao<Receiver, Long>{


public Receiver findDefault(Member member)
;

public Page<Receiver> findPage(Member member,Pageable pageable)
;

}