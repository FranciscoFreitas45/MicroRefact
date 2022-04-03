package net.shangtech.weixin.weixin.service;
 import java.util.List;
import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.ssh.core.base.Page;
import net.shangtech.ssh.core.base.SqlDao;
import net.shangtech.weixin.weixin.dao.WxMessageDao;
import net.shangtech.weixin.weixin.entity.WxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.shangtech.Interface.WxMessageDao;
@Service
@Transactional
public class WxMessageService extends BaseService<WxMessage>{

@Autowired
 private  WxMessageDao dao;

@Autowired
 private  SqlDao sqlDao;


@Override
public BaseDao<WxMessage> dao(){
    return dao;
}


@Transactional(readOnly = true)
public Page<WxMessage> findMessageByPage(int pageNo,int pageSize,int sysUserId){
    Page<WxMessage> page = new Page<WxMessage>(pageSize);
    page.setPageNo(pageNo);
    page.setQuery("where sysUserId=? and mainId=null order by id desc", sysUserId);
    page = super.find(page);
    for (WxMessage message : page.getResult()) {
        message.setSubMessages(dao.find("where mainId=? order by id", message.getId()));
    }
    return page;
}


public void saveMessages(List<WxMessage> list){
    for (WxMessage message : list) {
        if (message.getId() == null) {
            dao.insert(message);
            continue;
        }
        WxMessage old = dao.find(message.getId());
        if (old == null) {
            dao.insert(message);
            continue;
        }
        old.setTitle(message.getTitle());
        old.setContent(message.getContent());
        old.setImage(message.getImage());
        old.setMainId(null);
        old.setSummary(message.getSummary());
        old.setUrl(message.getUrl());
        dao.update(old);
    }
    // 如果是多图文消息还要给各个子消息设置主消息的ID
    // 不管是修改还是添加,上面已经把mainId都设为空了,所以数据库剩余的mainId为主消息ID的记录就是被删除的,先统一删除,再统一设置mainId
    if (list.size() > 1) {
        WxMessage main = list.remove(0);
        // jdbc跟hibernate放在同一个事务里还不知道行不行
        sqlDao.update("delete from wx_message where main_id=?", main.getId());
        for (WxMessage message : list) {
            message.setMainId(main.getId());
            dao.update(message);
        }
    }
}


}