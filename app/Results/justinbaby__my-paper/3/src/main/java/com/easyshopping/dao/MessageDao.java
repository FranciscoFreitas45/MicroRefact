package com.easyshopping.dao;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Message;
public interface MessageDao extends BaseDao<Message, Long>{


public Long count(Member member,Boolean read)
;

public Page<Message> findPage(Member member,Pageable pageable)
;

public Page<Message> findDraftPage(Member sender,Pageable pageable)
;

public void remove(Long id,Member member)
;

}