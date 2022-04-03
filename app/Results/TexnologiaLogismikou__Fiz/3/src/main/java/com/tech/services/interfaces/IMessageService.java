package com.tech.services.interfaces;
 import com.tech.models.entities.Message;
import javax.transaction.Transactional;
import java.util.List;
public interface IMessageService {


@Transactional
public Message getMessageById(Long id)
;

@Transactional
public List<Message> getAllMessages()
;

@Transactional
public Long getCountBySenderId(Long userid)
;

@Transactional
public void modifyMessage(Message message)
;

@Transactional
public Long getNextId()
;

@Transactional
public List<Message> getByChatRoom(Long chatroom_id)
;

@Transactional
public Long getCount()
;

@Transactional
public void delete(Message message)
;

@Transactional
public void addMessage(Message message)
;

@Transactional
public List<Message> getBySenderId(Long userid)
;

@Transactional
public Long getCountByChatroomId(Long chatroom_id)
;

}