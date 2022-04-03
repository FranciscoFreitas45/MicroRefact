package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.MessageDao;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Message;
import com.lingxiang2014.service.MessageService;
@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, Long>implements MessageService{

@Resource(name = "messageDaoImpl")
 private  MessageDao messageDao;


@Resource(name = "messageDaoImpl")
public void setBaseDao(MessageDao messageDao){
    super.setBaseDao(messageDao);
}


@Transactional(readOnly = true)
public Long count(Member member,Boolean read){
    return messageDao.count(member, read);
}


@Transactional(readOnly = true)
public Page<Message> findPage(Member member,Pageable pageable){
    return messageDao.findPage(member, pageable);
}


@Transactional(readOnly = true)
public Page<Message> findDraftPage(Member sender,Pageable pageable){
    return messageDao.findDraftPage(sender, pageable);
}


public void delete(Long id,Member member){
    messageDao.remove(id, member);
}


}