package com.gs.service.impl;
 import com.gs.bean.MessageSend;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MessageSendDAO;
import com.gs.service.MessageSendService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class MessageSendServiceImpl implements MessageSendService{

@Resource
 private  MessageSendDAO messageSendDAO;


public List<MessageSend> queryByPagerDisable(Pager pager){
    return messageSendDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<MessageSend> list){
    return messageSendDAO.batchInsert(list);
}


public int batchUpdate(List<MessageSend> list){
    return messageSendDAO.batchUpdate(list);
}


public MessageSend query(MessageSend messageSend){
    return messageSendDAO.query(messageSend);
}


public int count(User user){
    return messageSendDAO.count(user);
}


public int insert(MessageSend messageSend){
    return messageSendDAO.insert(messageSend);
}


public int update(MessageSend messageSend){
    return messageSendDAO.update(messageSend);
}


public int active(String id){
    return messageSendDAO.active(id);
}


@Override
public List<MessageSend> queryAll(String status){
    return messageSendDAO.queryAll(status);
}


public List<MessageSend> blurredQuery(Pager pager,MessageSend messageSend){
    return messageSendDAO.blurredQuery(pager, messageSend);
}


public int delete(MessageSend messageSend){
    return messageSendDAO.delete(messageSend);
}


public int batchDelete(List<MessageSend> list){
    return messageSendDAO.batchDelete(list);
}


public List<MessageSend> queryByStatus(String status){
    return messageSendDAO.queryAll(status);
}


public int inactive(String id){
    return messageSendDAO.inactive(id);
}


public int countByBlurred(MessageSend messageSend){
    return 0;
}


public int deleteById(String id){
    return messageSendDAO.deleteById(id);
}


public int countByDisable(User user){
    return messageSendDAO.countByDisable(user);
}


@Override
public int insertRemind(MessageSend messageSend){
    return messageSendDAO.insertRemind(messageSend);
}


public MessageSend queryById(String id){
    return messageSendDAO.queryById(id);
}


public List<MessageSend> queryByPager(Pager pager){
    return messageSendDAO.queryByPager(pager);
}


}