package com.lingxiang2014.service;
 import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Message;
public interface MessageService extends BaseService<Message, Long>{


public Long count(Member member,Boolean read)
;

public Page<Message> findPage(Member member,Pageable pageable)
;

public Page<Message> findDraftPage(Member sender,Pageable pageable)
;

public void delete(Long id,Member member)
;

}