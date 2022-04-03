package cn.gson.oasys.services.discuss;
 import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gson.oasys.model.dao.discuss.ReplyDao;
import cn.gson.oasys.model.entity.discuss.Reply;
import cn.gson.oasys.Interface.ReplyDao;
@Service
@Transactional
public class ReplyService {

@Autowired
 private  ReplyDao replyDao;


public Reply save(Reply reply){
    return replyDao.save(reply);
}


public void deleteReply(Reply reply){
    replyDao.delete(reply);
}


}