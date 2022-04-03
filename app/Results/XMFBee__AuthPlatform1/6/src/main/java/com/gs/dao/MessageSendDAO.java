package com.gs.dao;
 import com.gs.bean.MessageSend;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MessageSendDAO extends BaseDAO<String, MessageSend>{


public int countByBlurred(MessageSend messageSend,User user)
;

public int insertRemind(MessageSend messageSend)
;

public List<MessageSend> blurredQuery(Pager pager,MessageSend messageSend)
;

}