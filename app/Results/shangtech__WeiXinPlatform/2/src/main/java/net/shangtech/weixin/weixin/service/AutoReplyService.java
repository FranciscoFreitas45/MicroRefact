package net.shangtech.weixin.weixin.service;
 import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.weixin.dao.AutoReplyDao;
import net.shangtech.weixin.weixin.entity.AutoReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AutoReplyService extends BaseService<AutoReply>{

@Autowired
 private  AutoReplyDao dao;


@Override
public BaseDao<AutoReply> dao(){
    return dao;
}


}