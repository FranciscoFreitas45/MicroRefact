package com.lingxiang2014.dao;
 import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Message;
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